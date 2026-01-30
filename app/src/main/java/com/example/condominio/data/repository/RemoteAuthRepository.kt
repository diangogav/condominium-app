package com.example.condominio.data.repository

import com.example.condominio.data.local.TokenManager
import com.example.condominio.data.model.User
import com.example.condominio.data.model.UserProfile
import com.example.condominio.data.remote.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteAuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : AuthRepository {
    
    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: Flow<User?> = _currentUser.asStateFlow()
    
    init {
        // Load user on init if token exists
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            val token = tokenManager.getToken()
            if (token != null) {
                // Token exists, fetch current user
                fetchCurrentUser()
            }
        }
    }
    
    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            android.util.Log.d("RemoteAuthRepo", "Attempting login for: $email")
            val response = apiService.login(
                mapOf(
                    "email" to email,
                    "password" to password
                )
            )
            
            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()!!
                android.util.Log.d("RemoteAuthRepo", "Login successful, token: ${loginResponse.token.accessToken.take(20)}...")
                tokenManager.saveToken(loginResponse.token.accessToken)
                
                val user = loginResponse.user.toDomain()
                _currentUser.value = user
                
                Result.success(user)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = "Login failed (${response.code()}): ${errorBody ?: "Unknown error"}"
                android.util.Log.e("RemoteAuthRepo", errorMsg)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            android.util.Log.e("RemoteAuthRepo", "Exception during login", e)
            Result.failure(Exception("Login error: ${e.message}"))
        }
    }
    
    override suspend fun register(
        name: String,
        email: String,
        unit: String,
        building: String, // This is actually building_id now
        password: String
    ): Result<User> {
        return try {
            val response = apiService.register(
                mapOf(
                    "name" to name,
                    "email" to email,
                    "unit" to unit,
                    "building_id" to building, // Backend expects building_id
                    "password" to password
                )
            )
            
            if (response.isSuccessful && response.body() != null) {
                // Backend returns user profile but no token
                // Auto-login to get the token
                android.util.Log.d("RemoteAuthRepo", "Registration successful. Auto-logging in...")
                return login(email, password)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Registration failed"
                android.util.Log.e("RemoteAuthRepo", "Registration failed: $errorMsg")
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            android.util.Log.e("RemoteAuthRepo", "Exception during registration", e)
            Result.failure(e)
        }
    }

    
    override suspend fun logout() {
        tokenManager.clearToken()
        _currentUser.value = null
    }
    
    override suspend fun updateUser(user: User): Result<Unit> {
        return try {
            val updates = com.example.condominio.data.model.UpdateUserRequest(
                name = user.name,
                unit = user.apartmentUnit
            )
            
            val response = apiService.updateUser(updates)
            
            if (response.isSuccessful && response.body() != null) {
                _currentUser.value = response.body()!!.toDomain()
                Result.success(Unit)
            } else {
                Result.failure(Exception("Update failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun changePassword(currentPassword: String, newPassword: String): Result<Unit> {
        // This endpoint doesn't exist in the current API spec
        // You may need to add it to the backend or handle it differently
        return Result.failure(Exception("Password change not implemented in API"))
    }
    
    suspend fun fetchCurrentUser(): Result<User> {
        return try {
            val response = apiService.getCurrentUser()
            
            if (response.isSuccessful && response.body() != null) {
                val user = response.body()!!.toDomain()
                _currentUser.value = user
                Result.success(user)
            } else {
                Result.failure(Exception("Failed to fetch user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

// Extension function to convert API UserProfile to domain User
private fun UserProfile.toDomain() = User(
    id = id,
    name = name,
    email = email,
    apartmentUnit = unit,
    building = buildingName ?: buildingId // Use building name if available, otherwise ID
)

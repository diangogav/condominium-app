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
                
                var user = loginResponse.user.toDomain()
                user = enrichUserWithUnit(user)
                _currentUser.value = user
                
                Result.success(user)
            } else {
                val errorBody = response.errorBody()?.string() ?: ""
                if (response.code() == 401 && errorBody.contains("pending", ignoreCase = true)) {
                    android.util.Log.w("RemoteAuthRepo", "User account is pending approval")
                    Result.failure(com.example.condominio.data.model.UserPendingException("Your account is pending approval"))
                } else {
                    val errorMsg = "Login failed (${response.code()}): ${if (errorBody.isNotEmpty()) errorBody else "Unknown error"}"
                    android.util.Log.e("RemoteAuthRepo", errorMsg)
                    Result.failure(Exception(errorMsg))
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("RemoteAuthRepo", "Exception during login", e)
            Result.failure(Exception("Login error: ${e.message}"))
        }
    }
    
    override suspend fun register(
        name: String,
        email: String,
        unitId: String,
        building: String, // This is building_id
        password: String
    ): Result<User> {
        return try {
            val response = apiService.register(
                mapOf(
                    "name" to name,
                    "email" to email,
                    "unit_id" to unitId, // Backend might expect one of these
                    "unit" to unitId,    // Fallback key
                    "building_id" to building,
                    "password" to password
                )
            )
            
            if (response.isSuccessful && response.body() != null) {
                val registerResponse = response.body()!!
                
                // Auto-login logic
                android.util.Log.d("RemoteAuthRepo", "Registration successful. Saving token and user...")
                tokenManager.saveToken(registerResponse.accessToken)
                
                val user = registerResponse.user.toDomain()
                _currentUser.value = user
                
                Result.success(user)
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

    override suspend fun getUnits(buildingId: String): Result<List<com.example.condominio.data.model.UnitDto>> {
        return try {
            val response = apiService.getBuildingUnits(buildingId)
            if (response.isSuccessful && response.body() != null) {
                // Return sorted list of UnitDto objects
                val units = response.body()!!.sortedBy { it.name }
                Result.success(units)
            } else {
                Result.failure(Exception("Failed to fetch units"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUnit(unitId: String): Result<com.example.condominio.data.model.UnitDto> {
        return try {
            val response = apiService.getUnitDetails(unitId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch unit details"))
            }
        } catch (e: Exception) {
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
    
    override suspend fun fetchCurrentUser(): Result<User> {
        return try {
            val response = apiService.getCurrentUser()
            
            if (response.isSuccessful && response.body() != null) {
                val profile = response.body()!!
                var user = profile.toDomain()
                user = enrichUserWithUnit(user)
                
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
// Extension function to convert API UserProfile to domain User
private fun UserProfile.toDomain(): User {
    var apartment = unitName ?: ""
    
    if (apartment.isEmpty() && unit != null) {
        if (unit.isJsonPrimitive) {
            apartment = unit.asString
        } else if (unit.isJsonObject) {
            val unitObj = unit.asJsonObject
            apartment = if (unitObj.has("name")) {
                unitObj.get("name").asString
            } else if (unitObj.has("id")) {
                unitObj.get("id").asString
            } else {
                ""
            }
        }
    }

    return User(
        id = id ?: "",
        name = name ?: "Unknown",
        email = email ?: "",
        apartmentUnit = apartment,
        building = buildingName ?: "Unknown Building", // Use name for display
        buildingId = buildingId ?: "" // Store ID for logic
    )
}

private suspend fun RemoteAuthRepository.enrichUserWithUnit(user: User): User {
    var enrichedUser = user
    // If apartmentUnit looks like a UUID, try to fetch its name
    if (enrichedUser.apartmentUnit.length == 36 && enrichedUser.apartmentUnit.contains("-")) {
        android.util.Log.d("RemoteAuthRepo", "Apartment unit looks like a UUID, fetching details...")
        getUnit(enrichedUser.apartmentUnit).onSuccess { unitDto ->
            enrichedUser = enrichedUser.copy(apartmentUnit = unitDto.name)
            android.util.Log.d("RemoteAuthRepo", "Unit name enriched: ${unitDto.name}")
        }.onFailure {
            android.util.Log.e("RemoteAuthRepo", "Failed to enrich unit name: ${it.message}")
        }
    }
    return enrichedUser
}

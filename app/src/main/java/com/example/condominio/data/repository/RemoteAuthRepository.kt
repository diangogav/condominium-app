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
    
    override fun setCurrentUnit(unit: com.example.condominio.data.model.UserUnit) {
        _currentUser.value = _currentUser.value?.copy(currentUnit = unit)
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
                android.util.Log.d("RemoteAuthRepo", "Login successful, token: ${loginResponse.accessToken.take(20)}...")
                tokenManager.saveToken(loginResponse.accessToken)
                
                var user = loginResponse.user.toDomain()
                user = enrichUserWithUnits(user)
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
    
    override suspend fun register(name: String, email: String, unitId: String, building: String, password: String): Result<User> {
        return try {
            val response = apiService.register(
                mapOf(
                    "name" to name,
                    "email" to email,
                    "unit_id" to unitId,
                    "building_id" to building,
                    "password" to password
                )
            )
            
            if (response.isSuccessful && response.body() != null) {
                val registerResponse = response.body()!!
                tokenManager.saveToken(registerResponse.accessToken)
                
                var user = registerResponse.user.toDomain()
                user = enrichUserWithUnits(user)
                _currentUser.value = user
                
                Result.success(user)
            } else {
                val errorMsg = "Registration failed: ${response.errorBody()?.string() ?: "Unknown error"}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun fetchCurrentUser(): Result<User> {
        return try {
            val response = apiService.getCurrentUser()
            
            if (response.isSuccessful && response.body() != null) {
                val profile = response.body()!!
                var user = profile.toDomain()
                user = enrichUserWithUnits(user)
                
                _currentUser.value = user
                Result.success(user)
            } else {
                Result.failure(Exception("Failed to fetch user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUnits(buildingId: String): Result<List<com.example.condominio.data.model.UnitDto>> {
        return try {
            val response = apiService.getBuildingUnits(buildingId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch units for building $buildingId"))
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
                Result.failure(Exception("Failed to fetch unit details for $unitId"))
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
            val request = com.example.condominio.data.model.UpdateUserRequest(
                name = user.name
                // phone = ... if available in User model
            )
            val response = apiService.updateUser(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to update user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun changePassword(currentPassword: String, newPassword: String): Result<Unit> {
        // changePassword NOT IMPLEMENTED in ApiService yet
        return Result.failure(Exception("Change password not implemented in backend API yet"))
    }

    private suspend fun enrichUserWithUnits(user: User): User {
        // Fetch user units
        val unitsResponse = try {
             apiService.getUserUnits(user.id)
        } catch (e: Exception) {
             null
        }

        if (unitsResponse?.isSuccessful == true && unitsResponse.body() != null) {
            val userUnitDtos = unitsResponse.body()!!
            val userUnits = mutableListOf<com.example.condominio.data.model.UserUnit>()

            for (dto in userUnitDtos) {
                // Fetch unit details to get name
                val unitDetailsResp = try { apiService.getUnitDetails(dto.unitId) } catch(e: Exception) { null }
                val unitName = unitDetailsResp?.body()?.name ?: "Unknown Unit"
                val buildingId = unitDetailsResp?.body()?.buildingId ?: dto.buildingId
                
                // Fetch building name if possible
                val buildingResp = try { apiService.getBuilding(buildingId) } catch(e: Exception) { null }
                val buildingName = buildingResp?.body()?.name ?: "Unknown Building"

                userUnits.add(com.example.condominio.data.model.UserUnit(
                    unitId = dto.unitId,
                    buildingId = buildingId,
                    unitName = unitName,
                    buildingName = buildingName,
                    role = dto.role,
                    isPrimary = dto.isPrimary
                ))
            }
            
            // Determine current unit
            // 1. If currently selected unit is in the new list, keep it.
            // 2. Else use primary.
            // 3. Else use first.
            val current = _currentUser.value?.currentUnit
            val newCurrent = if (current != null && userUnits.any { it.unitId == current.unitId }) {
                userUnits.find { it.unitId == current.unitId }
            } else {
                userUnits.find { it.isPrimary } ?: userUnits.firstOrNull()
            }

            return user.copy(units = userUnits, currentUnit = newCurrent)
        } else {
            // Fallback to legacy single unit enrichment if new endpoint fails or returns empty
             // Use legacy enrich logic but map to new structure
             val legacyUser = enrichUserWithLegacyUnit(user)
             return legacyUser
        }
    }

    private suspend fun enrichUserWithLegacyUnit(user: User): User {
        var enrichedUser = user
        // Actually user.toDomain() sets apartmentUnit name if possible using existing data.
        // But we want the ID. user.toDomain() puts ID in buildingId? No.
        
        // Let's re-examine toDomain helper below.
        // It tries to extract unit info from the 'unit' JSON element.
        
        // If we have a unit ID string (which might be in apartmentUnit if it was a UUID), fetch details.
        
        // Simple fallback: If units list is empty, try to create one dummy unit from existing fields
        if (user.units.isEmpty()) {
             // We can't do much without an ID for the unit.
             // If UserProfile returned a unit object, we might have it.
        }
        return enrichedUser
    }
}

// Extension function to convert API UserProfile to domain User
private fun UserProfile.toDomain(): User {
    // Attempt to extract unit ID and name
    var unitId = ""
    var unitName = this.unitName ?: ""
    
    if (unit != null) {
        if (unit.isJsonPrimitive) {
            val s = unit.asString
            // If it looks like UUID, it's ID. Else name.
            if (s.length == 36 && s.contains("-")) unitId = s else unitName = s
        } else if (unit.isJsonObject) {
            val unitObj = unit.asJsonObject
            unitId = if (unitObj.has("id")) unitObj.get("id").asString else ""
            unitName = if (unitObj.has("name")) unitObj.get("name").asString else unitName
        }
    }

    val tempUnit = if (unitId.isNotEmpty()) {
         com.example.condominio.data.model.UserUnit(
             unitId = unitId,
             buildingId = buildingId ?: "",
             unitName = unitName.ifEmpty { "Unit $unitId" },
             buildingName = buildingName ?: "Building",
             role = "resident",
             isPrimary = true
         )
    } else null

    return User(
        id = id ?: "",
        name = name ?: "Unknown",
        email = email ?: "",
        units = if (tempUnit != null) listOf(tempUnit) else emptyList(),
        currentUnit = tempUnit
    )
}

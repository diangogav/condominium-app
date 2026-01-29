package com.example.condominio.data.repository

import com.example.condominio.data.local.dao.UserDao
import com.example.condominio.data.local.entity.toEntity
import com.example.condominio.data.local.entity.toDomain
import com.example.condominio.data.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomAuthRepository @Inject constructor(
    private val userDao: UserDao
) : AuthRepository {

    override val currentUser: Flow<User?> = userDao.getUser().map { it?.toDomain() }

    override suspend fun login(email: String, password: String): Result<User> {
        delay(1000) // Simulate network delay
        
        // Check if user already exists
        val existingUser = userDao.getUser().firstOrNull()
        
        // Simple validation (in real app, this would be a backend call)
        if (email.isNotBlank() && password.length >= 6) {
            // If user exists, return it; otherwise create new one
            val user = existingUser?.toDomain() ?: User(
                id = "user_${System.currentTimeMillis()}",
                name = "Demo User",
                email = email,
                apartmentUnit = "4B"
            )
            
            // Only insert if it's a new user
            if (existingUser == null) {
                userDao.insertUser(user.toEntity())
            }
            
            return Result.success(user)
        }
        return Result.failure(Exception("Invalid credentials"))
    }

    override suspend fun register(name: String, email: String, unit: String, password: String): Result<User> {
        delay(1000)
        
        if (name.isBlank() || email.isBlank() || unit.isBlank() || password.length < 6) {
            return Result.failure(Exception("Invalid input"))
        }
        
        // Delete any existing user before creating new one (for demo purposes)
        val existingUser = userDao.getUser().firstOrNull()
        existingUser?.let {
            userDao.deleteUser(it)
        }
        
        val user = User(
            id = "user_${System.currentTimeMillis()}",
            name = name,
            email = email,
            apartmentUnit = unit
        )
        userDao.insertUser(user.toEntity())
        return Result.success(user)
    }

    override suspend fun logout() {
        // In a real app, you might clear the user session
        // For now, we'll keep the user in the database
    }

    override suspend fun updateUser(user: User): Result<Unit> {
        return try {
            delay(500)
            userDao.insertUser(user.toEntity()) // Insert/Update
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun changePassword(currentPassword: String, newPassword: String): Result<Unit> {
        delay(500)
        
        // In a real app, you would verify currentPassword against stored password
        // For this demo, we'll just validate the new password
        return if (newPassword.length >= 6) {
            // Password would be updated in backend/database
            Result.success(Unit)
        } else {
            Result.failure(Exception("Password must be at least 6 characters"))
        }
    }
}

package com.example.condominio.data.repository

import com.example.condominio.data.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface AuthRepository {
    val currentUser: Flow<User?>
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(name: String, email: String, unit: String, building: String, password: String): Result<User>
    suspend fun logout()
    suspend fun updateUser(user: User): Result<Unit>
    suspend fun changePassword(currentPassword: String, newPassword: String): Result<Unit>
}

@Singleton
class MockAuthRepository @Inject constructor() : AuthRepository {
    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser = _currentUser.asStateFlow()

    override suspend fun login(email: String, password: String): Result<User> {
        delay(1000) // Simulate network delay
        return if (email.isNotEmpty() && password.length >= 6) {
            val user = User("1", "John Doe", email, "4B")
            _currentUser.value = user
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun register(name: String, email: String, unit: String, building: String, password: String): Result<User> {
        delay(1000)
        val user = User("1", name, email, unit, building)
        _currentUser.value = user
        return Result.success(user)
    }

    override suspend fun logout() {
        _currentUser.value = null
    }

    override suspend fun updateUser(user: User): Result<Unit> {
        delay(500)
        _currentUser.value = user
        return Result.success(Unit)
    }

    override suspend fun changePassword(currentPassword: String, newPassword: String): Result<Unit> {
        delay(500)
        return if (newPassword.length >= 6) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Password must be at least 6 characters"))
        }
    }
}

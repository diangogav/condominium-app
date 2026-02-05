package com.example.condominio.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.local.AppDatabase
import com.example.condominio.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val database: AppDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onLoginClick() {
        if (_uiState.value.email.isBlank() || _uiState.value.password.isBlank()) {
            _uiState.update { it.copy(error = "Please fill in all fields") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.login(_uiState.value.email, _uiState.value.password)
            _uiState.update { it.copy(isLoading = false) }
            
            result.onSuccess { user ->
                // Check if user has multiple units
                val hasMultiple = user.units.size > 1
                // If single unit, auto-select it (if not already set by repo to first)
                if (!hasMultiple && user.units.isNotEmpty()) {
                    authRepository.setCurrentUnit(user.units.first())
                }
                
                _uiState.update { it.copy(isSuccess = true, hasMultipleUnits = hasMultiple) }
            }.onFailure { error ->
                if (error is com.example.condominio.data.model.UserPendingException) {
                    _uiState.update { it.copy(isPending = true) }
                } else {
                    _uiState.update { it.copy(error = error.message) }
                }
            }
        }
    }
    
    fun onClearDatabaseClick() {
        viewModelScope.launch {
            database.clearAllData()
            _uiState.update { it.copy(databaseCleared = true) }
            // Reset the flag after a short delay
            kotlinx.coroutines.delay(2000)
            _uiState.update { it.copy(databaseCleared = false) }
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val isPending: Boolean = false,
    val databaseCleared: Boolean = false,
    val hasMultipleUnits: Boolean = false
)

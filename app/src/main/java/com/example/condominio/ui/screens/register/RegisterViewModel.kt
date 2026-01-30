package com.example.condominio.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.model.Building
import com.example.condominio.data.repository.AuthRepository
import com.example.condominio.data.repository.BuildingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val buildingRepository: BuildingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBuildings()
    }

    private fun loadBuildings() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingBuildings = true) }
            val result = buildingRepository.getBuildings()
            result.onSuccess { buildings ->
                _uiState.update { 
                    it.copy(
                        buildings = buildings,
                        isLoadingBuildings = false
                    ) 
                }
            }.onFailure {
                _uiState.update { 
                    it.copy(
                        isLoadingBuildings = false,
                        error = "Failed to load buildings"
                    ) 
                }
            }
        }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onUnitChange(unit: String) {
        _uiState.update { it.copy(unit = unit) }
    }

    fun onBuildingChange(buildingId: String) {
        _uiState.update { it.copy(selectedBuildingId = buildingId) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onRegisterClick() {
        if (_uiState.value.email.isBlank() || _uiState.value.password.isBlank() || 
            _uiState.value.name.isBlank() || _uiState.value.unit.isBlank() ||
            _uiState.value.selectedBuildingId.isBlank()) {
            _uiState.update { it.copy(error = "Please fill in all fields") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.register(
                _uiState.value.name, 
                _uiState.value.email, 
                _uiState.value.unit,
                _uiState.value.selectedBuildingId, // Send building ID
                _uiState.value.password
            )
            _uiState.update { it.copy(isLoading = false) }
            
            result.onSuccess {
                _uiState.update { it.copy(isSuccess = true) }
            }.onFailure { error ->
                _uiState.update { it.copy(error = error.message) }
            }
        }
    }
}

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val unit: String = "",
    val selectedBuildingId: String = "",
    val buildings: List<Building> = emptyList(),
    val isLoadingBuildings: Boolean = false,
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

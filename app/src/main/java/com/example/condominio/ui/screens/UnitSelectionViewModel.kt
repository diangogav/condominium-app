package com.example.condominio.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.model.UserUnit
import com.example.condominio.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnitSelectionViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UnitSelectionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUnits()
    }

    private fun loadUnits() {
        viewModelScope.launch {
            authRepository.fetchCurrentUser().onSuccess { user ->
                _uiState.update { 
                    it.copy(
                        units = user.units, 
                        isLoading = false,
                        userName = user.name
                    ) 
                }
            }.onFailure {
                _uiState.update { state -> state.copy(isLoading = false) }
            }
        }
    }

    fun onUnitSelected(unit: UserUnit) {
        authRepository.setCurrentUnit(unit)
        _uiState.update { it.copy(unitSelected = true) }
    }
}

data class UnitSelectionUiState(
    val units: List<UserUnit> = emptyList(),
    val isLoading: Boolean = true,
    val userName: String = "",
    val unitSelected: Boolean = false
)

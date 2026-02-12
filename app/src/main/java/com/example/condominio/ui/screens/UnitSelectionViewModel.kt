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
                val unitItems = user.units.map { SelectionItem.UnitItem(it) }
                val roleItems = user.buildingRoles.map { SelectionItem.RoleItem(it) }
                
                // Merge and remove duplicates (building + unit might overlap, but they are different items)
                // Actually, if I have a unit in building A and I am BOARD in building A, 
                // I should probably see both options or handle them distinctly.
                // The prompt says "union of IDs", but we need names.
                
                _uiState.update { 
                    it.copy(
                        items = unitItems + roleItems, 
                        isLoading = false,
                        userName = user.name
                    ) 
                }
            }.onFailure {
                _uiState.update { state -> state.copy(isLoading = false) }
            }
        }
    }

    fun onItemSelected(item: SelectionItem) {
        when (item) {
            is SelectionItem.UnitItem -> {
                authRepository.setCurrentUnit(item.unit)
            }
            is SelectionItem.RoleItem -> {
                // If we select a building role, we might need a "Virtual Unit" 
                // or a different way to track active building.
                // For now, let's create a placeholder UserUnit to satisfy current repository logic
                val virtualUnit = UserUnit(
                    unitId = "ADMIN-${item.role.buildingId}",
                    buildingId = item.role.buildingId,
                    unitName = "Administración",
                    buildingName = "Edificio ${item.role.buildingId.take(4)}", // Idealmente buscar nombre
                    isPrimary = false
                )
                authRepository.setCurrentUnit(virtualUnit)
            }
        }
        _uiState.update { it.copy(unitSelected = true) }
    }
}

sealed class SelectionItem {
    data class UnitItem(val unit: UserUnit) : SelectionItem()
    data class RoleItem(val role: com.example.condominio.data.model.BuildingRole) : SelectionItem()
}

data class UnitSelectionUiState(
    val items: List<SelectionItem> = emptyList(),
    val isLoading: Boolean = true,
    val userName: String = "",
    val unitSelected: Boolean = false
)

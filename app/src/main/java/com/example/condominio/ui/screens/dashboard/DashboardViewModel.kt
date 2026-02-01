package com.example.condominio.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.SolvencyStatus
import com.example.condominio.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val paymentRepository: com.example.condominio.data.repository.PaymentRepository,
    private val buildingRepository: com.example.condominio.data.repository.BuildingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Fetch latest user data
            authRepository.fetchCurrentUser()
            
            // Collect user details
            launch {
                authRepository.currentUser.collect { user ->
                    user?.let {
                        var buildingName = it.building
                        
                        // Fetch building name if missing or "Unknown Building" and we have an ID
                        if ((buildingName.isEmpty() || buildingName == "Unknown Building" || buildingName == "Unknown") && it.buildingId.isNotEmpty()) {
                            try {
                                val result = buildingRepository.getBuilding(it.buildingId)
                                result.onSuccess { building ->
                                    buildingName = building.name
                                }
                            } catch (e: Exception) {
                                // Keep original name on error
                            }
                        }

                        var apartmentUnit = it.apartmentUnit
                        
                        // If apartmentUnit looks like a UUID, fetch details
                        if (apartmentUnit.length == 36 && apartmentUnit.contains("-")) {
                            authRepository.getUnit(apartmentUnit).onSuccess { unitDto ->
                                apartmentUnit = unitDto.name
                            }
                        }

                        _uiState.update { state ->
                            state.copy(
                                userName = it.name,
                                userBuilding = buildingName,
                                userApartment = apartmentUnit
                            )
                        }
                    }
                }
            }
            
            // Fetch dashboard summary from API using PaymentRepository
            launch {
                val result = paymentRepository.getPaymentSummary()
                result.onSuccess { summary ->
                    _uiState.update {
                        it.copy(
                            solvencyStatus = summary.solvencyStatus,
                            recentPayments = summary.recentTransactions,
                            pendingPeriods = summary.pendingPeriods,
                            paidPeriods = summary.paidPeriods,
                            // Only update unit name from summary if it's not a UUID
                            userApartment = if (summary.unitName.isNotEmpty() && !summary.unitName.matches(Regex("^[0-9a-fA-F-]{36}$"))) summary.unitName else it.userApartment,
                            isLoading = false
                        )
                    }
                }.onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }
    
    fun refresh() {
        loadData()
    }
}

data class DashboardUiState(
    val userName: String = "",
    val userBuilding: String = "",
    val userApartment: String = "",
    val solvencyStatus: SolvencyStatus = SolvencyStatus.PENDING,
    val recentPayments: List<Payment> = emptyList(),
    val pendingPeriods: List<String> = emptyList(),
    val paidPeriods: List<String> = emptyList(),
    val isLoading: Boolean = false
)

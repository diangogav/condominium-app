package com.example.condominio.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.SolvencyStatus
import com.example.condominio.data.repository.AuthRepository
import com.example.condominio.data.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Collect user details
            launch {
                authRepository.currentUser.collect { user ->
                    user?.let {
                        _uiState.update { state ->
                            state.copy(
                                userName = it.name,
                                userBuilding = it.building,
                                userApartment = it.apartmentUnit
                            )
                        }
                    }
                }
            }
            
            // Fetch dashboard summary from API
            launch {
                val result = dashboardRepository.getDashboardSummary()
                result.onSuccess { summary ->
                    // Map API solvency status string to enum
                    val status = when (summary.solvencyStatus.uppercase()) {
                        "SOLVENT" -> SolvencyStatus.SOLVENT
                        "OVERDUE" -> SolvencyStatus.PENDING // Map OVERDUE to PENDING for now
                        else -> SolvencyStatus.PENDING
                    }
                    
                    _uiState.update {
                        it.copy(
                            solvencyStatus = status,
                            recentPayments = summary.recentTransactions,
                            pendingPeriods = summary.pendingPeriods,
                            paidPeriods = summary.paidPeriods,
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

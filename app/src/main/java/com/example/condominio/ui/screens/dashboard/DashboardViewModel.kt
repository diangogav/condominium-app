package com.example.condominio.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.SolvencyStatus
import com.example.condominio.data.repository.AuthRepository
import com.example.condominio.data.repository.RoomPaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val paymentRepository: RoomPaymentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Collect User
            launch {
                authRepository.currentUser.collect { user ->
                    _uiState.update { it.copy(userName = user?.name ?: "Resident") }
                }
            }

            // Collect Payments reactively - this will auto-update when new payments are added
            launch {
                paymentRepository.getPaymentsFlow().collect { payments ->
                    // Calculate solvency based on 5-day grace period
                    val calendar = java.util.Calendar.getInstance()
                    val currentDay = calendar.get(java.util.Calendar.DAY_OF_MONTH)
                    val currentMonth = calendar.get(java.util.Calendar.MONTH) // 0-indexed (0 = Jan)
                    val currentYear = calendar.get(java.util.Calendar.YEAR)
                    
                    // Determine target month to have paid
                    // If day <= 5, we expect payment up to previous month
                    // If day > 5, we expect payment up to current month
                    val targetMonthIndex = if (currentDay <= 5) currentMonth - 1 else currentMonth
                    
                    val status = if (targetMonthIndex < 0) {
                        // Early January (<= Jan 5th) - Consider solvent at start of year
                        SolvencyStatus.SOLVENT
                    } else {
                        // Get all paid periods from non-rejected payments
                        val paidPeriods = payments
                            .filter { it.status != com.example.condominio.data.model.PaymentStatus.REJECTED }
                            .flatMap { it.paidPeriods }
                            .toSet()
                        
                        // Check if target month is paid
                        val targetPeriodId = String.format(java.util.Locale.US, "%d-%02d", currentYear, targetMonthIndex + 1)
                        if (paidPeriods.contains(targetPeriodId)) {
                            SolvencyStatus.SOLVENT
                        } else {
                            SolvencyStatus.PENDING
                        }
                    }
                    
                    _uiState.update { 
                        it.copy(
                            recentPayments = payments.take(5),
                            solvencyStatus = status, 
                            isLoading = false
                        ) 
                    }
                }
            }
        }
    }
}

data class DashboardUiState(
    val userName: String = "",
    val solvencyStatus: SolvencyStatus = SolvencyStatus.PENDING,
    val recentPayments: List<Payment> = emptyList(),
    val isLoading: Boolean = false
)

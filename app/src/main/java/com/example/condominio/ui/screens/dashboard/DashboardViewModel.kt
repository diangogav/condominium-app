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
                    val status = if (payments.isNotEmpty()) SolvencyStatus.SOLVENT else SolvencyStatus.PENDING
                    
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

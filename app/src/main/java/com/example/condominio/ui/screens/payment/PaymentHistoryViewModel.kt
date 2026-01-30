package com.example.condominio.ui.screens.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.model.Payment
import com.example.condominio.data.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentHistoryViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPayments()
    }

    fun loadPayments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                val payments = paymentRepository.getPayments()
                _uiState.update { 
                    it.copy(
                        payments = payments,
                        isLoading = false
                    ) 
                }
            } catch (e: Exception) {
                // Handle error
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}

data class PaymentHistoryUiState(
    val payments: List<Payment> = emptyList(),
    val isLoading: Boolean = false
)

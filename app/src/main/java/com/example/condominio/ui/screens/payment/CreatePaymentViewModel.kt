package com.example.condominio.ui.screens.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreatePaymentViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreatePaymentUiState())
    val uiState = _uiState.asStateFlow()

    fun onAmountChange(amount: String) {
        _uiState.update { it.copy(amount = amount) }
    }

    fun onDateChange(date: Date) {
        _uiState.update { it.copy(date = date) }
    }

    fun onDescriptionChange(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun onMethodChange(method: PaymentMethod) {
        _uiState.update { it.copy(method = method) }
    }

    fun onBankChange(bank: String) {
        _uiState.update { it.copy(bank = bank) }
    }

    fun onReferenceChange(reference: String) {
        _uiState.update { it.copy(reference = reference) }
    }

    fun onPhoneChange(phone: String) {
        _uiState.update { it.copy(phone = phone) }
    }

    fun onProofUrlChange(url: String) {
        _uiState.update { it.copy(proofUrl = url) }
    }

    fun onPeriodToggle(period: String) {
        _uiState.update { state -> 
            val newPeriods = if (state.selectedPeriods.contains(period)) {
                state.selectedPeriods - period
            } else {
                state.selectedPeriods + period
            }
            state.copy(selectedPeriods = newPeriods)
        }
    }

    fun onSubmitClick() {
        val state = _uiState.value
        val amount = state.amount.toDoubleOrNull()
        
        if (amount == null || amount <= 0) {
            _uiState.update { it.copy(error = "Invalid amount") }
            return
        }

        if (state.description.isBlank()) {
            _uiState.update { it.copy(error = "Description is required") }
            return
        }
        
        if (state.selectedPeriods.isEmpty()) {
            _uiState.update { it.copy(error = "Please select at least one month") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = paymentRepository.createPayment(
                amount = amount,
                date = state.date,
                description = state.description,
                method = state.method,
                reference = state.reference.ifBlank { null },
                bank = state.bank.ifBlank { null },
                phone = state.phone.ifBlank { null },
                proofUrl = state.proofUrl,
                paidPeriods = state.selectedPeriods
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

data class CreatePaymentUiState(
    val amount: String = "",
    val date: Date = Date(),
    val description: String = "",
    val method: PaymentMethod = PaymentMethod.PAGO_MOVIL,
    val bank: String = "",
    val reference: String = "",
    val phone: String = "",
    val proofUrl: String? = null,
    val selectedPeriods: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

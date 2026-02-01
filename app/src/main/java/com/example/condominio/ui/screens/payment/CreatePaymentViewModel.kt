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

import com.example.condominio.data.repository.AuthRepository
import kotlinx.coroutines.flow.first

@HiltViewModel
class CreatePaymentViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val authRepository: AuthRepository
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

    fun addPeriod(period: String) {
        _uiState.update { state ->
            if (!state.selectedPeriods.contains(period)) {
                state.copy(selectedPeriods = state.selectedPeriods + period)
            } else {
                state
            }
        }
    }

    fun removePeriod(period: String) {
        _uiState.update { state ->
            state.copy(selectedPeriods = state.selectedPeriods - period)
        }
    }


    fun onYearChange(year: Int) {
        _uiState.update { it.copy(selectedYear = year) }
        // Automatically toggle the period when year changes? 
        // Based on original code, selecting a year triggered a toggle. 
        // But maybe we should just update the selector and let the user click a button or something?
        // The original code was:
        // selectedYear = year
        // val periodId = ...
        // viewModel.onPeriodToggle(periodId)
        // Let's replicate this behavior to preserve UX, but it's a bit odd. 
        // Actually, usually dropdowns just select the value. 
        // The user issue is that the dropdown VALUE doesn't change. 
        // So first let's just update the state.
        // We will invoke onPeriodToggle from the UI callback if needed, 
        // OR calculate period here.
        // Let's just update state here. The UI can call onPeriodToggle separately if that's the desired UX,
        // or we can couple them.
        // Given the user report "always remains the first element", moving state to VM fixes the display.
        // If I look at the UI code again, the dropdown item onClick did TWO things: updating `selectedYear` and calling `onPeriodToggle`.
        // So I will expose `onYearChange` and `onMonthChange` and letting the UI call `onPeriodToggle` if that's what it wants, 
        // or better: `onYearChange` updates the year, and we can have a separate `addPeriod` action?
        // No, the UI pattern there implies selecting a year/month adds it to the list.
        // I'll stick to simple state updates for now and let the UI drive the multi-step logic or refactor later.
    }

    fun onMonthChange(month: Int) {
        _uiState.update { it.copy(selectedMonth = month) }
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
            
            // Get current user to access buildingId
            val currentUser = authRepository.currentUser.first()
            val buildingId = currentUser?.buildingId
            
            if (buildingId.isNullOrEmpty()) {
                _uiState.update { it.copy(isLoading = false, error = "User building not found. Please re-login.") }
                return@launch
            }

            val result = paymentRepository.createPayment(
                amount = amount,
                date = state.date,
                description = state.description,
                method = state.method,
                reference = state.reference.ifBlank { null },
                bank = state.bank.ifBlank { null },
                phone = state.phone.ifBlank { null },
                proofUrl = state.proofUrl,
                paidPeriods = state.selectedPeriods,
                buildingId = buildingId // Pass buildingId from user session
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
    val selectedYear: Int = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR),
    val selectedMonth: Int = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

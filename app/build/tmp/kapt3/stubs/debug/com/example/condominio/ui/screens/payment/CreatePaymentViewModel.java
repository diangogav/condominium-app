package com.example.condominio.ui.screens.payment;

import androidx.lifecycle.ViewModel;
import com.example.condominio.data.model.PaymentMethod;
import com.example.condominio.data.repository.PaymentRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.Date;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u000fJ\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u000fJ\u000e\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u000fJ\u000e\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u000fJ\u0006\u0010\"\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006#"}, d2 = {"Lcom/example/condominio/ui/screens/payment/CreatePaymentViewModel;", "Landroidx/lifecycle/ViewModel;", "paymentRepository", "Lcom/example/condominio/data/repository/PaymentRepository;", "(Lcom/example/condominio/data/repository/PaymentRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/condominio/ui/screens/payment/CreatePaymentUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onAmountChange", "", "amount", "", "onBankChange", "bank", "onDateChange", "date", "Ljava/util/Date;", "onDescriptionChange", "description", "onMethodChange", "method", "Lcom/example/condominio/data/model/PaymentMethod;", "onPeriodToggle", "period", "onPhoneChange", "phone", "onProofUrlChange", "url", "onReferenceChange", "reference", "onSubmitClick", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CreatePaymentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.repository.PaymentRepository paymentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.condominio.ui.screens.payment.CreatePaymentUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.condominio.ui.screens.payment.CreatePaymentUiState> uiState = null;
    
    @javax.inject.Inject()
    public CreatePaymentViewModel(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.repository.PaymentRepository paymentRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.condominio.ui.screens.payment.CreatePaymentUiState> getUiState() {
        return null;
    }
    
    public final void onAmountChange(@org.jetbrains.annotations.NotNull()
    java.lang.String amount) {
    }
    
    public final void onDateChange(@org.jetbrains.annotations.NotNull()
    java.util.Date date) {
    }
    
    public final void onDescriptionChange(@org.jetbrains.annotations.NotNull()
    java.lang.String description) {
    }
    
    public final void onMethodChange(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.model.PaymentMethod method) {
    }
    
    public final void onBankChange(@org.jetbrains.annotations.NotNull()
    java.lang.String bank) {
    }
    
    public final void onReferenceChange(@org.jetbrains.annotations.NotNull()
    java.lang.String reference) {
    }
    
    public final void onPhoneChange(@org.jetbrains.annotations.NotNull()
    java.lang.String phone) {
    }
    
    public final void onProofUrlChange(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void onPeriodToggle(@org.jetbrains.annotations.NotNull()
    java.lang.String period) {
    }
    
    public final void onSubmitClick() {
    }
}
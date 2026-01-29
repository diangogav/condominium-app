package com.example.condominio.ui.screens.payment;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.example.condominio.data.model.Payment;
import com.example.condominio.data.repository.PaymentRepository;
import com.example.condominio.data.utils.PdfService;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.io.File;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0016"}, d2 = {"Lcom/example/condominio/ui/screens/payment/PaymentDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "paymentRepository", "Lcom/example/condominio/data/repository/PaymentRepository;", "pdfService", "Lcom/example/condominio/data/utils/PdfService;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/example/condominio/data/repository/PaymentRepository;Lcom/example/condominio/data/utils/PdfService;Landroidx/lifecycle/SavedStateHandle;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/condominio/ui/screens/payment/PaymentDetailUiState;", "paymentId", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadPayment", "", "onDownloadReceiptClick", "onPdfShown", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PaymentDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.repository.PaymentRepository paymentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.utils.PdfService pdfService = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.condominio.ui.screens.payment.PaymentDetailUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.condominio.ui.screens.payment.PaymentDetailUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String paymentId = null;
    
    @javax.inject.Inject()
    public PaymentDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.repository.PaymentRepository paymentRepository, @org.jetbrains.annotations.NotNull()
    com.example.condominio.data.utils.PdfService pdfService, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.condominio.ui.screens.payment.PaymentDetailUiState> getUiState() {
        return null;
    }
    
    private final void loadPayment() {
    }
    
    public final void onDownloadReceiptClick() {
    }
    
    public final void onPdfShown() {
    }
}
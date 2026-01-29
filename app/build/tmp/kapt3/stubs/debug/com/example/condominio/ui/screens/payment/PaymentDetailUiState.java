package com.example.condominio.ui.screens.payment;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.example.condominio.data.model.Payment;
import com.example.condominio.data.repository.PaymentRepository;
import com.example.condominio.data.utils.PdfService;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.io.File;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J+\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/example/condominio/ui/screens/payment/PaymentDetailUiState;", "", "payment", "Lcom/example/condominio/data/model/Payment;", "isLoading", "", "pdfFile", "Ljava/io/File;", "(Lcom/example/condominio/data/model/Payment;ZLjava/io/File;)V", "()Z", "getPayment", "()Lcom/example/condominio/data/model/Payment;", "getPdfFile", "()Ljava/io/File;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class PaymentDetailUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.example.condominio.data.model.Payment payment = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.io.File pdfFile = null;
    
    public PaymentDetailUiState(@org.jetbrains.annotations.Nullable()
    com.example.condominio.data.model.Payment payment, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.io.File pdfFile) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.condominio.data.model.Payment getPayment() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File getPdfFile() {
        return null;
    }
    
    public PaymentDetailUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.condominio.data.model.Payment component1() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.condominio.ui.screens.payment.PaymentDetailUiState copy(@org.jetbrains.annotations.Nullable()
    com.example.condominio.data.model.Payment payment, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.io.File pdfFile) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}
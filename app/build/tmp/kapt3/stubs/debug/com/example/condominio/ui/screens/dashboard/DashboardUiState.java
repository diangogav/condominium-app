package com.example.condominio.ui.screens.dashboard;

import androidx.lifecycle.ViewModel;
import com.example.condominio.data.model.Payment;
import com.example.condominio.data.model.SolvencyStatus;
import com.example.condominio.data.repository.AuthRepository;
import com.example.condominio.data.repository.DashboardRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\t\u0010\"\u001a\u00020\u000eH\u00c6\u0003Jk\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010$\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\'H\u00d6\u0001J\t\u0010(\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0010R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018\u00a8\u0006)"}, d2 = {"Lcom/example/condominio/ui/screens/dashboard/DashboardUiState;", "", "userName", "", "userBuilding", "userApartment", "solvencyStatus", "Lcom/example/condominio/data/model/SolvencyStatus;", "recentPayments", "", "Lcom/example/condominio/data/model/Payment;", "pendingPeriods", "paidPeriods", "isLoading", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/condominio/data/model/SolvencyStatus;Ljava/util/List;Ljava/util/List;Ljava/util/List;Z)V", "()Z", "getPaidPeriods", "()Ljava/util/List;", "getPendingPeriods", "getRecentPayments", "getSolvencyStatus", "()Lcom/example/condominio/data/model/SolvencyStatus;", "getUserApartment", "()Ljava/lang/String;", "getUserBuilding", "getUserName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class DashboardUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userBuilding = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userApartment = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.model.SolvencyStatus solvencyStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.condominio.data.model.Payment> recentPayments = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> pendingPeriods = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> paidPeriods = null;
    private final boolean isLoading = false;
    
    public DashboardUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String userBuilding, @org.jetbrains.annotations.NotNull()
    java.lang.String userApartment, @org.jetbrains.annotations.NotNull()
    com.example.condominio.data.model.SolvencyStatus solvencyStatus, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.condominio.data.model.Payment> recentPayments, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> pendingPeriods, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> paidPeriods, boolean isLoading) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserBuilding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserApartment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.condominio.data.model.SolvencyStatus getSolvencyStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.condominio.data.model.Payment> getRecentPayments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPendingPeriods() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPaidPeriods() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public DashboardUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.condominio.data.model.SolvencyStatus component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.condominio.data.model.Payment> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.condominio.ui.screens.dashboard.DashboardUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String userBuilding, @org.jetbrains.annotations.NotNull()
    java.lang.String userApartment, @org.jetbrains.annotations.NotNull()
    com.example.condominio.data.model.SolvencyStatus solvencyStatus, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.condominio.data.model.Payment> recentPayments, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> pendingPeriods, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> paidPeriods, boolean isLoading) {
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
package com.example.condominio.data.model;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0006H\u00c6\u0003J?\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0006H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/example/condominio/data/model/DashboardSummaryDto;", "", "solvencyStatus", "", "lastPaymentDate", "pendingPeriods", "", "recentTransactions", "Lcom/example/condominio/data/model/PaymentDto;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getLastPaymentDate", "()Ljava/lang/String;", "getPendingPeriods", "()Ljava/util/List;", "getRecentTransactions", "getSolvencyStatus", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class DashboardSummaryDto {
    @com.google.gson.annotations.SerializedName(value = "solvency_status")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String solvencyStatus = null;
    @com.google.gson.annotations.SerializedName(value = "last_payment_date")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastPaymentDate = null;
    @com.google.gson.annotations.SerializedName(value = "pending_periods")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> pendingPeriods = null;
    @com.google.gson.annotations.SerializedName(value = "recent_transactions")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.condominio.data.model.PaymentDto> recentTransactions = null;
    
    public DashboardSummaryDto(@org.jetbrains.annotations.NotNull()
    java.lang.String solvencyStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String lastPaymentDate, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> pendingPeriods, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.condominio.data.model.PaymentDto> recentTransactions) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSolvencyStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastPaymentDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPendingPeriods() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.condominio.data.model.PaymentDto> getRecentTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.condominio.data.model.PaymentDto> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.condominio.data.model.DashboardSummaryDto copy(@org.jetbrains.annotations.NotNull()
    java.lang.String solvencyStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String lastPaymentDate, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> pendingPeriods, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.condominio.data.model.PaymentDto> recentTransactions) {
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
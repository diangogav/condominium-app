package com.example.condominio.data.repository;

import com.example.condominio.data.local.dao.PaymentDao;
import com.example.condominio.data.model.Payment;
import com.example.condominio.data.model.PaymentMethod;
import com.example.condominio.data.model.PaymentStatus;
import kotlinx.coroutines.flow.Flow;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004Jr\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015H\u0096@\u00a2\u0006\u0002\u0010\u001cJ\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00150\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001f"}, d2 = {"Lcom/example/condominio/data/repository/RoomPaymentRepository;", "Lcom/example/condominio/data/repository/PaymentRepository;", "paymentDao", "Lcom/example/condominio/data/local/dao/PaymentDao;", "(Lcom/example/condominio/data/local/dao/PaymentDao;)V", "createPayment", "Lkotlin/Result;", "Lcom/example/condominio/data/model/Payment;", "amount", "", "date", "Ljava/util/Date;", "description", "", "method", "Lcom/example/condominio/data/model/PaymentMethod;", "reference", "bank", "phone", "proofUrl", "paidPeriods", "", "createPayment-LiYkppA", "(DLjava/util/Date;Ljava/lang/String;Lcom/example/condominio/data/model/PaymentMethod;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPayment", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPayments", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPaymentsFlow", "Lkotlinx/coroutines/flow/Flow;", "app_debug"})
public final class RoomPaymentRepository implements com.example.condominio.data.repository.PaymentRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.local.dao.PaymentDao paymentDao = null;
    
    @javax.inject.Inject()
    public RoomPaymentRepository(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.local.dao.PaymentDao paymentDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPayments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.condominio.data.model.Payment>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.condominio.data.model.Payment>> getPaymentsFlow() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPayment(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.condominio.data.model.Payment> $completion) {
        return null;
    }
}
package com.example.condominio.data.local;

import com.example.condominio.data.local.dao.PaymentDao;
import com.example.condominio.data.local.entity.PaymentEntity;
import com.example.condominio.data.model.PaymentMethod;
import com.example.condominio.data.model.PaymentStatus;
import kotlinx.coroutines.Dispatchers;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/condominio/data/local/DatabaseSeeder;", "", "paymentDao", "Lcom/example/condominio/data/local/dao/PaymentDao;", "(Lcom/example/condominio/data/local/dao/PaymentDao;)V", "seedIfEmpty", "", "seedPayments", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DatabaseSeeder {
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.local.dao.PaymentDao paymentDao = null;
    
    @javax.inject.Inject()
    public DatabaseSeeder(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.local.dao.PaymentDao paymentDao) {
        super();
    }
    
    public final void seedIfEmpty() {
    }
    
    private final java.lang.Object seedPayments(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}
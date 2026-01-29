package com.example.condominio.data.repository;

import com.example.condominio.data.local.dao.UserDao;
import com.example.condominio.data.local.dao.PaymentDao;
import com.example.condominio.data.local.entity.*;
import com.example.condominio.data.model.User;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J,\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J,\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0013J\u000e\u0010\u0018\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0002JD\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\"J\u000e\u0010#\u001a\u00020\u000eH\u0082@\u00a2\u0006\u0002\u0010\u0019J$\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010%\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b&\u0010\'R\u001c\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006("}, d2 = {"Lcom/example/condominio/data/repository/RoomAuthRepository;", "Lcom/example/condominio/data/repository/AuthRepository;", "userDao", "Lcom/example/condominio/data/local/dao/UserDao;", "paymentDao", "Lcom/example/condominio/data/local/dao/PaymentDao;", "(Lcom/example/condominio/data/local/dao/UserDao;Lcom/example/condominio/data/local/dao/PaymentDao;)V", "currentUser", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/condominio/data/model/User;", "getCurrentUser", "()Lkotlinx/coroutines/flow/Flow;", "changePassword", "Lkotlin/Result;", "", "currentPassword", "", "newPassword", "changePassword-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "email", "password", "login-0E7RQCE", "logout", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "monthName", "month", "", "register", "name", "unit", "building", "register-hUnOzRk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedDemoPayments", "updateUser", "user", "updateUser-gIAlu-s", "(Lcom/example/condominio/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RoomAuthRepository implements com.example.condominio.data.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.local.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.local.dao.PaymentDao paymentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.example.condominio.data.model.User> currentUser = null;
    
    @javax.inject.Inject()
    public RoomAuthRepository(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.local.dao.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.example.condominio.data.local.dao.PaymentDao paymentDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.example.condominio.data.model.User> getCurrentUser() {
        return null;
    }
    
    private final java.lang.Object seedDemoPayments(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.String monthName(int month) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}
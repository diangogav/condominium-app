package com.example.condominio.data.repository;

import com.example.condominio.data.local.TokenManager;
import com.example.condominio.data.model.User;
import com.example.condominio.data.model.UserProfile;
import com.example.condominio.data.remote.ApiService;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J,\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u000fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J,\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001c\u0010\u0015J\u000e\u0010\u001d\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0018JD\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\"\u0010#J$\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010%\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b&\u0010\'R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006("}, d2 = {"Lcom/example/condominio/data/repository/RemoteAuthRepository;", "Lcom/example/condominio/data/repository/AuthRepository;", "apiService", "Lcom/example/condominio/data/remote/ApiService;", "tokenManager", "Lcom/example/condominio/data/local/TokenManager;", "(Lcom/example/condominio/data/remote/ApiService;Lcom/example/condominio/data/local/TokenManager;)V", "_currentUser", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/condominio/data/model/User;", "currentUser", "Lkotlinx/coroutines/flow/Flow;", "getCurrentUser", "()Lkotlinx/coroutines/flow/Flow;", "changePassword", "Lkotlin/Result;", "", "currentPassword", "", "newPassword", "changePassword-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCurrentUser", "fetchCurrentUser-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "email", "password", "login-0E7RQCE", "logout", "register", "name", "unit", "building", "register-hUnOzRk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUser", "user", "updateUser-gIAlu-s", "(Lcom/example/condominio/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RemoteAuthRepository implements com.example.condominio.data.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.remote.ApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.local.TokenManager tokenManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.condominio.data.model.User> _currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.example.condominio.data.model.User> currentUser = null;
    
    @javax.inject.Inject()
    public RemoteAuthRepository(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.example.condominio.data.local.TokenManager tokenManager) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.example.condominio.data.model.User> getCurrentUser() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}
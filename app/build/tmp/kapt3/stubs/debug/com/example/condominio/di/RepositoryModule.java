package com.example.condominio.di;

import android.content.Context;
import androidx.room.Room;
import com.example.condominio.data.local.AppDatabase;
import com.example.condominio.data.local.dao.PaymentDao;
import com.example.condominio.data.local.dao.UserDao;
import com.example.condominio.data.repository.AuthRepository;
import com.example.condominio.data.repository.PaymentRepository;
import com.example.condominio.data.repository.RoomAuthRepository;
import com.example.condominio.data.repository.RoomPaymentRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/example/condominio/di/RepositoryModule;", "", "()V", "bindAuthRepository", "Lcom/example/condominio/data/repository/AuthRepository;", "authRepository", "Lcom/example/condominio/data/repository/RoomAuthRepository;", "bindPaymentRepository", "Lcom/example/condominio/data/repository/PaymentRepository;", "paymentRepository", "Lcom/example/condominio/data/repository/RoomPaymentRepository;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.condominio.data.repository.AuthRepository bindAuthRepository(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.repository.RoomAuthRepository authRepository);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.condominio.data.repository.PaymentRepository bindPaymentRepository(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.repository.RoomPaymentRepository paymentRepository);
}
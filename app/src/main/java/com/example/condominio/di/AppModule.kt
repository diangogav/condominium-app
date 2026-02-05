package com.example.condominio.di

import android.content.Context
import androidx.room.Room
import com.example.condominio.data.local.AppDatabase
import com.example.condominio.data.local.dao.PaymentDao
import com.example.condominio.data.local.dao.UserDao
import com.example.condominio.data.repository.AuthRepository
import com.example.condominio.data.repository.PaymentRepository
import com.example.condominio.data.repository.RemoteAuthRepository
import com.example.condominio.data.repository.RemotePaymentRepository
import com.example.condominio.data.repository.PettyCashRepository
import com.example.condominio.data.repository.PettyCashRepositoryImpl
import com.example.condominio.data.repository.BuildingRepository
import com.example.condominio.data.repository.RemoteBuildingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "condominio_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun providePaymentDao(database: AppDatabase): PaymentDao {
        return database.paymentDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepository: RemoteAuthRepository
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindPaymentRepository(
        paymentRepository: RemotePaymentRepository
    ): PaymentRepository
    
    @Binds
    @Singleton
    abstract fun bindBuildingRepository(
        buildingRepository: RemoteBuildingRepository
    ): BuildingRepository

    @Binds
    @Singleton
    abstract fun bindPettyCashRepository(
        pettyCashRepository: PettyCashRepositoryImpl
    ): PettyCashRepository
}

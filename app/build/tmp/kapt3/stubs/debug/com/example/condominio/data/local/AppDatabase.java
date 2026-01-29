package com.example.condominio.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.condominio.data.local.dao.PaymentDao;
import com.example.condominio.data.local.dao.UserDao;
import com.example.condominio.data.local.entity.PaymentEntity;
import com.example.condominio.data.local.entity.UserEntity;
import kotlinx.coroutines.Dispatchers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/example/condominio/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "clearAllData", "", "paymentDao", "Lcom/example/condominio/data/local/dao/PaymentDao;", "userDao", "Lcom/example/condominio/data/local/dao/UserDao;", "app_debug"})
@androidx.room.Database(entities = {com.example.condominio.data.local.entity.UserEntity.class, com.example.condominio.data.local.entity.PaymentEntity.class}, version = 3, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.condominio.data.local.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.condominio.data.local.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.condominio.data.local.dao.PaymentDao paymentDao();
    
    /**
     * Clear all data from the database (for demo purposes)
     */
    public final void clearAllData() {
    }
}
package com.example.condominio.data.remote;

import com.example.condominio.data.model.*;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001Jb\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00150\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00150\u00032\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00a7@\u00a2\u0006\u0002\u0010\u001fJ*\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00032\u0014\b\u0001\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120#H\u00a7@\u00a2\u0006\u0002\u0010$J*\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00032\u0014\b\u0001\u0010\'\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120#H\u00a7@\u00a2\u0006\u0002\u0010$J\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0001\u0010)\u001a\u00020*H\u00a7@\u00a2\u0006\u0002\u0010+\u00a8\u0006,"}, d2 = {"Lcom/example/condominio/data/remote/ApiService;", "", "createPayment", "Lretrofit2/Response;", "Lcom/example/condominio/data/model/PaymentDto;", "amount", "Lokhttp3/RequestBody;", "date", "method", "reference", "bank", "period", "proofImage", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBuilding", "Lcom/example/condominio/data/model/Building;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBuildings", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentUser", "Lcom/example/condominio/data/model/UserProfile;", "getDashboardSummary", "Lcom/example/condominio/data/model/DashboardSummaryDto;", "getPayment", "getPayments", "year", "", "(Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/example/condominio/data/model/LoginResponse;", "credentials", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/example/condominio/data/model/RegisterResponse;", "request", "updateUser", "updates", "Lcom/example/condominio/data/model/UpdateUserRequest;", "(Lcom/example/condominio/data/model/UpdateUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.RegisterResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> credentials, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.LoginResponse>> $completion);
    
    @retrofit2.http.GET(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.UserProfile>> $completion);
    
    @retrofit2.http.PATCH(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateUser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.condominio.data.model.UpdateUserRequest updates, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.UserProfile>> $completion);
    
    @retrofit2.http.GET(value = "buildings/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBuildings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.condominio.data.model.Building>>> $completion);
    
    @retrofit2.http.GET(value = "buildings/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBuilding(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.Building>> $completion);
    
    @retrofit2.http.GET(value = "payments/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPayments(@retrofit2.http.Query(value = "year")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer year, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.condominio.data.model.PaymentDto>>> $completion);
    
    @retrofit2.http.GET(value = "payments/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPayment(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.PaymentDto>> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "payments/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPayment(@retrofit2.http.Part(value = "amount")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody amount, @retrofit2.http.Part(value = "date")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody date, @retrofit2.http.Part(value = "method")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody method, @retrofit2.http.Part(value = "reference")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody reference, @retrofit2.http.Part(value = "bank")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody bank, @retrofit2.http.Part(value = "period")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody period, @retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    okhttp3.MultipartBody.Part proofImage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.PaymentDto>> $completion);
    
    @retrofit2.http.GET(value = "dashboard/summary")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDashboardSummary(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.condominio.data.model.DashboardSummaryDto>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}
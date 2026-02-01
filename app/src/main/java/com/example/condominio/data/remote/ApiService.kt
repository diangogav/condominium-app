package com.example.condominio.data.remote

import com.example.condominio.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    // Auth endpoints
    @POST("auth/register")
    suspend fun register(
        @Body request: Map<String, String>
    ): Response<RegisterResponse>
    
    @POST("auth/login")
    suspend fun login(
        @Body credentials: Map<String, String>
    ): Response<LoginResponse>
    
    // User endpoints
    @GET("users/me")
    suspend fun getCurrentUser(): Response<UserProfile>
    
    @PATCH("users/me")
    suspend fun updateUser(
        @Body updates: UpdateUserRequest
    ): Response<UserProfile>
    
    // Building endpoints
    @GET("buildings")
    suspend fun getBuildings(): Response<List<Building>>
    
    @GET("buildings/{id}")
    suspend fun getBuilding(
        @Path("id") id: String
    ): Response<Building>

    @GET("buildings/{id}/units")
    suspend fun getBuildingUnits(
        @Path("id") id: String
    ): Response<List<UnitDto>>

    @GET("buildings/units/{id}")
    suspend fun getUnitDetails(
        @Path("id") id: String
    ): Response<UnitDto>
    
    // Payment endpoints
    @GET("payments/summary")
    suspend fun getPaymentSummary(): Response<PaymentSummaryDto>

    @GET("payments")
    suspend fun getPayments(
        @Query("year") year: Int? = null
    ): Response<List<PaymentDto>>
    
    @GET("payments/{id}")
    suspend fun getPayment(
        @Path("id") id: String
    ): Response<PaymentDto>
    
    @Multipart
    @POST("payments")
    suspend fun createPayment(
        @Part("amount") amount: RequestBody,
        @Part("date") date: RequestBody,
        @Part("method") method: RequestBody,
        @Part("reference") reference: RequestBody? = null,
        @Part("bank") bank: RequestBody? = null,
        @Part periods: List<MultipartBody.Part>,
        @Part("building_id") buildingId: RequestBody? = null,
        @Part proof_image: MultipartBody.Part? = null
    ): Response<PaymentDto>
}

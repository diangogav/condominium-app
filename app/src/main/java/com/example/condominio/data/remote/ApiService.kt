package com.example.condominio.data.remote

import com.example.condominio.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Auth endpoints
    @POST("auth/register")
    suspend fun register(@Body request: Map<String, String>): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body credentials: Map<String, String>): Response<LoginResponse>

    // User endpoints
    @GET("users/me") suspend fun getCurrentUser(): Response<UserProfile>

    @PATCH("users/me")
    suspend fun updateUser(@Body updates: UpdateUserRequest): Response<UserProfile>

    @GET("users/{id}/units")
    suspend fun getUserUnits(@Path("id") id: String): Response<List<UserUnitDto>>

    // Building endpoints
    @GET("buildings") suspend fun getBuildings(): Response<List<Building>>

    @GET("buildings/{id}") suspend fun getBuilding(@Path("id") id: String): Response<Building>

    @GET("buildings/{id}/units")
    suspend fun getBuildingUnits(@Path("id") id: String): Response<List<UnitDto>>

    @GET("buildings/units/{id}")
    suspend fun getUnitDetails(@Path("id") id: String): Response<UnitDto>

    // Payment endpoints
    @GET("payments/summary") suspend fun getPaymentSummary(): Response<PaymentSummaryDto>

    @GET("payments")
    suspend fun getPayments(
            @Query("unit_id") unitId: String? = null,
            @Query("year") year: Int? = null
    ): Response<List<PaymentDto>>

    @GET("payments/{id}") suspend fun getPayment(@Path("id") id: String): Response<PaymentDto>

    // Multipart Payment Creation
    @Multipart
    @POST("payments")
    suspend fun createPaymentMultipart(
            @Part("amount") amount: RequestBody,
            @Part("unit_id") unitId: RequestBody,
            @Part("building_id") buildingId: RequestBody?,
            @Part("method") method: RequestBody,
            @Part("date") date: RequestBody,
            @Part("notes") notes: RequestBody?,
            @Part("reference") reference: RequestBody?,
            @Part("bank") bank: RequestBody?,
            @Part("allocations") allocations: RequestBody?, // JSON string
            @Part("periods")
            periods: RequestBody?, // JSON string or comma-separated? Backend likely expects
            // array-like or JSON
            @Part proof_image: MultipartBody.Part? = null
    ): Response<PaymentDto>

    // New JSON-based Create Payment with Allocations
    @POST("payments")
    suspend fun createPayment(@Body request: CreatePaymentRequest): Response<PaymentDto>

    // Billing
    @GET("billing/units/{unit_id}/balance")
    suspend fun getBalance(@Path("unit_id") unitId: String): Response<BalanceDto>

    @GET("billing/units/{unit_id}/invoices")
    suspend fun getInvoices(
            @Path("unit_id") unitId: String,
            @Query("status") status: String? = null
    ): Response<List<InvoiceDto>>

    @GET("billing/invoices/{id}") suspend fun getInvoice(@Path("id") id: String): Response<InvoiceDto>

    @GET("billing/invoices/{id}/payments")
    suspend fun getInvoicePayments(@Path("id") id: String): Response<List<PaymentDto>>

    // Petty Cash
    @GET("petty-cash/balance/{buildingId}")
    suspend fun getPettyCashBalance(
            @Path("buildingId") buildingId: String
    ): Response<PettyCashBalanceDto>

    @GET("petty-cash/history/{buildingId}")
    suspend fun getPettyCashHistory(
            @Path("buildingId") buildingId: String,
            @Query("type") type: String? = null,
            @Query("category") category: String? = null,
            @Query("page") page: Int = 1,
            @Query("limit") limit: Int = 10
    ): Response<List<PettyCashTransactionDto>>

    @POST("petty-cash/income")
    suspend fun registerPettyCashIncome(
            @Body request: RegisterIncomeRequest
    ): Response<PettyCashTransactionDto>

    @Multipart
    @POST("petty-cash/expense")
    suspend fun registerPettyCashExpense(
            @Part("building_id") buildingId: RequestBody,
            @Part("amount") amount: RequestBody,
            @Part("description") description: RequestBody,
            @Part("category") category: RequestBody,
            @Part evidence_image: MultipartBody.Part? = null
    ): Response<PettyCashTransactionDto>
}

package com.example.condominio.data.repository

import com.example.condominio.data.model.*
import com.example.condominio.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemotePaymentRepository @Inject constructor(
    private val apiService: ApiService,
    private val gson: com.google.gson.Gson
) : PaymentRepository {
    
    override suspend fun getPayments(unitId: String?): List<Payment> {
        return try {
            val response = apiService.getPayments(unitId = unitId)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.map { it.toDomain() }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            android.util.Log.e("RemotePaymentRepo", "Error fetching payments", e)
            emptyList()
        }
    }
    
    fun getPaymentsStream(): Flow<List<Payment>> = flow {
        try {
            val response = apiService.getPayments()
            if (response.isSuccessful && response.body() != null) {
                emit(response.body()!!.map { it.toDomain() })
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            android.util.Log.e("RemotePaymentRepo", "Error fetching payments stream", e)
            emit(emptyList())
        }
    }
    
    override suspend fun getPayment(id: String): Payment? {
        return try {
            val response = apiService.getPayment(id)
            if (response.isSuccessful) {
                response.body()?.toDomain()
            } else {
                null
            }
        } catch (e: Exception) {
            android.util.Log.e("RemotePaymentRepo", "Error fetching payment $id", e)
            null
        }
    }

    override suspend fun getPaymentSummary(): Result<com.example.condominio.data.model.PaymentSummary> {
        return try {
            val response = apiService.getPaymentSummary()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Failed to get summary"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            android.util.Log.e("RemotePaymentRepo", "Error fetching summary", e)
            Result.failure(e)
        }
    }
    
    override suspend fun createPayment(
        amount: Double,
        date: Date,
        description: String,
        method: PaymentMethod,
        unitId: String,
        allocations: List<com.example.condominio.data.model.PaymentAllocation>,
        reference: String?,
        bank: String?,
        phone: String?,
        proofUrl: String?,
        paidPeriods: List<String>,
        buildingId: String?
    ): Result<Payment> {
        return try {
            val allocationDtos = allocations.map { 
                com.example.condominio.data.model.AllocationDto(
                    invoiceId = it.invoiceId,
                    amount = it.amount
                )
            }
            
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date)
            
            val response = if (!proofUrl.isNullOrEmpty()) {
                // Multipart Flow
                val file = File(proofUrl)
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val proofPart = MultipartBody.Part.createFormData("proof_image", file.name, requestFile)
                
                apiService.createPaymentMultipart(
                    amount = amount.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                    unitId = unitId.toRequestBody("text/plain".toMediaTypeOrNull()),
                    buildingId = buildingId?.toRequestBody("text/plain".toMediaTypeOrNull()),
                    method = method.name.toRequestBody("text/plain".toMediaTypeOrNull()),
                    date = dateStr.toRequestBody("text/plain".toMediaTypeOrNull()),
                    notes = description.toRequestBody("text/plain".toMediaTypeOrNull()),
                    reference = reference?.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bank = bank?.toRequestBody("text/plain".toMediaTypeOrNull()),
                    allocations = gson.toJson(allocationDtos).toRequestBody("application/json".toMediaTypeOrNull()),
                    periods = gson.toJson(paidPeriods).toRequestBody("application/json".toMediaTypeOrNull()),
                    proof_image = proofPart
                )
            } else {
                // JSON Flow
                val request = com.example.condominio.data.model.CreatePaymentRequest(
                    unitId = unitId,
                    buildingId = buildingId,
                    amount = amount,
                    amountCurrency = "USD",
                    date = dateStr,
                    method = method.name,
                    reference = reference,
                    bank = bank,
                    notes = description,
                    allocations = allocationDtos,
                    proofUrl = null,
                    periods = paidPeriods
                )
                apiService.createPayment(request)
            }
            
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                 val errorMsg = response.errorBody()?.string() ?: "Payment creation failed"
                 Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBalance(unitId: String): Result<com.example.condominio.data.model.Balance> {
        return try {
            val response = apiService.getBalance(unitId)
            if (response.isSuccessful && response.body() != null) {
                // Map BalanceDto to Balance
                val dto = response.body()!!
                val balance = com.example.condominio.data.model.Balance(
                    unitId = dto.unit,
                    totalDebt = dto.totalDebt,
                    pendingInvoices = dto.details.map { it.toDomain() }
                )
                Result.success(balance)
            } else {
                Result.failure(Exception("Failed to fetch balance"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getInvoices(unitId: String, status: String?): Result<List<Invoice>> {
        return try {
            val response = apiService.getInvoices(unitId, status)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.map { it.toDomain() })
            } else {
                Result.failure(Exception("Failed to fetch invoices"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getInvoice(id: String): Result<Invoice> {
        return try {
            val response = apiService.getInvoice(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(Exception("Failed to fetch invoice details"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getInvoicePayments(invoiceId: String): Result<List<Payment>> {
        return try {
            val response = apiService.getInvoicePayments(invoiceId)
            if (response.isSuccessful && response.body() != null) {
                val payments = response.body()!!.map { dto ->
                    val domainPayment = dto.toDomain()
                    // If the DTO has an allocated amount for this specific invoice, ensure it's in the allocations list
                    if (dto.allocatedAmount != null && dto.allocatedAmount > 0) {
                        val specificAllocation = com.example.condominio.data.model.PaymentAllocation(
                            invoiceId = invoiceId,
                            amount = dto.allocatedAmount
                        )
                        // Prepend or add to existing allocations
                        domainPayment.copy(allocations = listOf(specificAllocation) + domainPayment.allocations)
                    } else {
                        domainPayment
                    }
                }
                Result.success(payments)
            } else {
                Result.failure(Exception("Failed to fetch invoice payments"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

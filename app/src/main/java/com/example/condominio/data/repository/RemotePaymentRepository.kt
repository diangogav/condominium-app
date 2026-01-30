package com.example.condominio.data.repository

import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.model.toDomain
import com.example.condominio.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemotePaymentRepository @Inject constructor(
    private val apiService: ApiService
) : PaymentRepository {
    
    override suspend fun getPayments(): List<Payment> {
        return try {
            val response = apiService.getPayments()
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
    
    override suspend fun createPayment(
        amount: Double,
        date: Date,
        description: String,
        method: PaymentMethod,
        reference: String?,
        bank: String?,
        phone: String?,
        proofUrl: String?,
        paidPeriods: List<String>
    ): Result<Payment> {
        return try {
            // Convert date to YYYY-MM-DD format
            val dateString = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(date)
            
            // Create request bodies
            val amountBody = amount.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val dateBody = dateString.toRequestBody("text/plain".toMediaTypeOrNull())
            val methodBody = method.name.toRequestBody("text/plain".toMediaTypeOrNull())
            val referenceBody = reference?.toRequestBody("text/plain".toMediaTypeOrNull())
            val bankBody = bank?.toRequestBody("text/plain".toMediaTypeOrNull())
            
            // Period from paidPeriods (take first if available)
            val periodBody = paidPeriods.firstOrNull()?.toRequestBody("text/plain".toMediaTypeOrNull())
            
            // Handle proof image if proofUrl is a file path
            val proofPart = if (proofUrl != null && proofUrl.isNotEmpty()) {
                try {
                    val file = File(proofUrl)
                    if (file.exists()) {
                        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                        MultipartBody.Part.createFormData("proof_image", file.name, requestFile)
                    } else {
                        android.util.Log.w("RemotePaymentRepo", "Proof file does not exist: $proofUrl")
                        null
                    }
                } catch (e: Exception) {
                    android.util.Log.e("RemotePaymentRepo", "Error creating proof part", e)
                    null
                }
            } else {
                null
            }

            val response = apiService.createPayment(
                amount = amountBody,
                date = dateBody,
                method = methodBody,
                reference = referenceBody,
                bank = bankBody,
                period = periodBody,
                proofImage = proofPart
            )
            
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = "Payment creation failed (${response.code()}): ${errorBody ?: "Unknown error"}"
                android.util.Log.e("RemotePaymentRepo", errorMsg)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            android.util.Log.e("RemotePaymentRepo", "Exception creating payment", e)
            Result.failure(Exception("Error: ${e.message}"))
        }
    }
}

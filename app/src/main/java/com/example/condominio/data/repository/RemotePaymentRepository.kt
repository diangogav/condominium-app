package com.example.condominio.data.repository

import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.model.toDomain
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
        reference: String?,
        bank: String?,
        phone: String?,
        proofUrl: String?,
        paidPeriods: List<String>,
        buildingId: String?
    ): Result<Payment> {
        return try {
            val amountPart = RequestBody.create("text/plain".toMediaTypeOrNull(), amount.toString())
            val datePart = RequestBody.create("text/plain".toMediaTypeOrNull(), SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date))
            val methodPart = RequestBody.create("text/plain".toMediaTypeOrNull(), method.name)
            val referencePart = reference?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }
            val bankPart = bank?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }
            val buildingIdPart = buildingId?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }

            // Create list of parts for periods
            // For an array field in Retrofit Multipart, we usually send them as multiple parts with the same name "periods[]" or just "periods" depending on backend.
            // User request said: "periods (Array de Strings)".
            // Standard convention for array in FormData is key="periods" or key="periods[]".
            // Let's assume "periods" allows multiple values or "periods[]". I'll try "periods" first with multiple parts, 
            // OR construct a single part if it expects a JSON array string? No, "Array of Strings" usually means multiple fields.
            // Wait, the prompt said: "periods (Array de Strings)".
            // If I use @Part periods: List<MultipartBody.Part>, Retrofit sends them.
            // Let's create the list.
            val periodParts = paidPeriods.map { period ->
                MultipartBody.Part.createFormData("periods", period) 
                // Note: If backend expects "periods[]", change name to "periods[]". 
                // Standard Express/Multer often handles "periods" if it's defined as array, or "periods[]".
                // I'll stick to "periods" based on the name change key.
            }

            var imagePart: MultipartBody.Part? = null
            if (proofUrl != null) {
                val file = File(proofUrl)
                if (file.exists()) {
                    val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                    imagePart = MultipartBody.Part.createFormData("proof_image", file.name, requestFile)
                }
            }
            
            val response = apiService.createPayment(
                amount = amountPart,
                date = datePart,
                method = methodPart,
                reference = referencePart,
                bank = bankPart,
                periods = periodParts,
                buildingId = buildingIdPart,
                proof_image = imagePart
            )
            
            if (response.isSuccessful && response.body() != null) {
                val payment = response.body()!!
                Result.success(payment.toDomain())
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

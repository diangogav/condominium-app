package com.example.condominio.data.model

import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.model.PaymentStatus
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * DTO (Data Transfer Object) for Payment API responses.
 * This matches the exact structure returned by the backend with snake_case fields.
 */
data class PaymentDto(
    val id: String,
    @SerializedName("user_id")
    val userId: String,
    val amount: Double,
    @SerializedName("payment_date")
    val paymentDate: String,  // ISO date string "YYYY-MM-DD"
    val method: String,  // "PAGO_MOVIL", "TRANSFER", "CASH"
    val reference: String? = null,
    val bank: String? = null,
    @SerializedName("proof_url")
    val proofUrl: String? = null,
    val status: String,  // "PENDING", "APPROVED", "REJECTED"
    val period: String? = null, // Kept for backward compatibility if needed, or remove if backend removed it completely.
    // Backend says "Now: periods (Array of Strings)". It didn't explicitly say period is gone, but implied.
    // Safest is to add periods.
    val periods: List<String>? = null,
    val notes: String? = null,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

/**
 * Extension function to convert PaymentDto to domain Payment model
 */
fun PaymentDto.toDomain(): Payment {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    
    val date = try {
        // Try parsing full ISO format first, then simple format
        if (paymentDate.contains("T")) {
             dateFormat.parse(paymentDate) ?: java.util.Date()
        } else {
             simpleDateFormat.parse(paymentDate) ?: java.util.Date()
        }
    } catch (e: Exception) {
        try {
            simpleDateFormat.parse(paymentDate) ?: java.util.Date()
        } catch (e2: Exception) {
            java.util.Date()
        }
    }
    
    val paymentMethod = try {
        PaymentMethod.valueOf(method)
    } catch (e: Exception) {
        PaymentMethod.EFECTIVO
    }
    
    val paymentStatus = try {
        when (status) {
            "PENDING" -> PaymentStatus.PENDING
            "APPROVED", "VERIFIED" -> PaymentStatus.APPROVED
            "REJECTED" -> PaymentStatus.REJECTED
            else -> PaymentStatus.PENDING
        }
    } catch (e: Exception) {
        PaymentStatus.PENDING
    }
    
    // Parse createdAt timestamp
    val createdAtDate = try {
        dateFormat.parse(createdAt)
    } catch (e: Exception) {
        null
    }
    
    return Payment(
        id = id,
        amount = amount,
        date = date,
        status = paymentStatus,
        description = notes ?: (if (!periods.isNullOrEmpty()) periods.joinToString(", ") else period ?: "Pago de condominio"),
        method = paymentMethod,
        reference = reference,
        bank = bank,
        phone = null,
        proofUrl = proofUrl,
        paidPeriods = periods ?: period?.let { listOf(it) } ?: emptyList(),
        createdAt = createdAtDate
    )
}

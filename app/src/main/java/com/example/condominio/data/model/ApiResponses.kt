package com.example.condominio.data.model

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int
)

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    val user: UserProfile
)

// Register returns just the user profile, no token
// User needs to login after registration
// Update RegisterResponse to match new backend (returns AuthSession)
data class RegisterResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("user")
    val user: UserProfile
)

data class UserProfile(
    @SerializedName(value = "id", alternate = ["_id", "userId", "user_id"])
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val role: String? = null,
    val status: String? = null,
    val units: List<UserUnitDto>? = null,
    // Legacy support
    @SerializedName(value = "unit", alternate = ["apartment_unit", "apartment", "unit_id"])
    val unit: com.google.gson.JsonElement? = null,
    @SerializedName("unit_name")
    val unitName: String? = null,
    @SerializedName("building_id")
    val buildingId: String? = null,
    @SerializedName("building_name")
    val buildingName: String? = null,
    val phone: String? = null,
    val settings: Map<String, Any>? = null
)

data class UserUnitDto(
    @SerializedName("unit_id")
    val unitId: String,
    @SerializedName("building_id")
    val buildingId: String,
    @SerializedName("building_role")
    val role: String, // "owner", "resident"
    @SerializedName("is_primary")
    val isPrimary: Boolean
)

data class PaymentSummaryDto(
    @SerializedName("solvency_status")
    val solvencyStatus: String,
    @SerializedName("last_payment_date")
    val lastPaymentDate: String?,
    @SerializedName("pending_periods")
    val pendingPeriods: List<String>,
    @SerializedName("paid_periods")
    val paidPeriods: List<String>,
    @SerializedName("recent_transactions")
    val recentTransactions: List<PaymentDto>,
    @SerializedName(value = "unit", alternate = ["apartment_unit"])
    val unitObject: UnitDto? = null,
    @SerializedName("unit_id")
    val unitId: String? = null,
    @SerializedName("total_debt")
    val totalDebt: Double? = 0.0
)

fun PaymentSummaryDto.toDomain(): PaymentSummary {
    val solvency = try {
        if (solvencyStatus == "SOLVENT") SolvencyStatus.SOLVENT else SolvencyStatus.PENDING
    } catch (e: Exception) {
        SolvencyStatus.PENDING
    }

    val lastPayment = lastPaymentDate?.let {
        try {
            java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).parse(it)
        } catch (e: Exception) {
            null
        }
    }

    return PaymentSummary(
        solvencyStatus = solvency,
        lastPaymentDate = lastPayment,
        pendingPeriods = pendingPeriods,
        paidPeriods = paidPeriods,
        recentTransactions = recentTransactions.map { it.toDomain() },
        unitName = unitObject?.name ?: unitObject?.id ?: unitId ?: "",
        totalDebt = totalDebt ?: 0.0
    )
}

data class BalanceDto(
    val unit: String,
    val totalDebt: Double,
    @SerializedName("pendingInvoices")
    val pendingInvoicesCount: Int,
    val details: List<InvoiceDto>
)

data class InvoiceDto(
    @SerializedName(value = "id", alternate = ["invoiceId"])
    val id: String,
    val period: String,
    val amount: Double,
    @SerializedName("paid_amount")
    val paid: Double? = 0.0,
    val remaining: Double? = 0.0,
    val status: String,
    val description: String? = null,
    @SerializedName("due_date")
    val dueDate: String? = null,
    @SerializedName("unit_id")
    val unitId: String? = null
)

fun InvoiceDto.toDomain(): Invoice {
    val invoiceStatus = try {
        when (status.uppercase()) {
            "PENDING" -> InvoiceStatus.PENDING
            "PARTIALLY_PAID" -> InvoiceStatus.PENDING // Backend removed this, strictly treat as PENDING
            "PAID" -> InvoiceStatus.PAID
            "CANCELLED" -> InvoiceStatus.CANCELLED
            "OVERDUE" -> InvoiceStatus.OVERDUE
            else -> InvoiceStatus.PENDING
        }
    } catch (e: Exception) {
        InvoiceStatus.PENDING
    }
    
    val date = try {
        dueDate?.let { java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).parse(it) }
    } catch (e: Exception) {
        null
    }

    return Invoice(
        id = id,
        period = period,
        amount = amount,
        paid = paid ?: 0.0,
        remaining = remaining ?: (amount - (paid ?: 0.0)),
        status = invoiceStatus,
        description = description,
        dueDate = date
    )
}

data class PaymentDto(
    @SerializedName(value = "id", alternate = ["_id"])
    val id: String,
    @SerializedName("payment_id")
    val paymentId: String? = null,
    val amount: Double,
    val date: String? = null, // Made nullable to handle partial responses
    val method: String? = null,
    val status: String? = null,
    val description: String? = null,
    val reference: String? = null,
    val bank: String? = null,
    @SerializedName("unit_id")
    val unitId: String? = null,
    @SerializedName("building_id")
    val buildingId: String? = null,
    @SerializedName("proof_url")
    val proofUrl: String? = null,
    val periods: List<String>? = null,
    val allocations: List<AllocationDto>? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    val notes: String? = null,
    @SerializedName("allocated_amount")
    val allocatedAmount: Double? = null,
    @SerializedName("allocation_id")
    val allocationId: String? = null,
    @SerializedName("allocated_at")
    val allocatedAt: String? = null,
    val user: PaymentUserDto? = null
)

data class PaymentUserDto(
    val id: String,
    val name: String
)

fun PaymentDto.toDomain(): Payment {
    val finalId = paymentId ?: id
    
    val paymentStatus = try {
        if (status != null) PaymentStatus.valueOf(status.uppercase()) else PaymentStatus.APPROVED // Default to APPROVED if missing (likely approved in join table)
    } catch (e: Exception) {
        PaymentStatus.PENDING
    }

    val paymentMethod = try {
        if (method != null) PaymentMethod.valueOf(method.uppercase()) else PaymentMethod.TRANSFERENCIA
    } catch (e: Exception) {
        PaymentMethod.TRANSFERENCIA
    }

    val dateObj = try {
        val dateStr = date ?: createdAt // Fallback to created_at if date is missing
        dateStr?.let { 
             try {
                java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).parse(it) 
             } catch(e: Exception) {
                // Try ISO format if simple format fails (created_at is ISO)
                java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", java.util.Locale.US).parse(it)
             }
        } ?: java.util.Date()
    } catch (e: Exception) {
        java.util.Date()
    }

    // Determine description: prioritize notes, then backend description (which might be periods), then fallback
    val finalDescription = if (!notes.isNullOrEmpty()) {
        notes
    } else if (!description.isNullOrEmpty()) {
        description
    } else {
        "Pago"
    }

    return Payment(
        id = finalId,
        amount = amount,
        date = dateObj ?: java.util.Date(),
        status = paymentStatus,
        description = finalDescription,
        method = paymentMethod,
        reference = reference,
        bank = bank,
        proofUrl = proofUrl,
        allocations = allocations?.map { it.toDomain() } ?: emptyList(),
        paidPeriods = periods ?: emptyList(),
        userName = user?.name
    )
    }

data class AllocationDto(
    @SerializedName("invoice_id")
    val invoiceId: String,
    val amount: Double
)

fun AllocationDto.toDomain(): PaymentAllocation {
    return PaymentAllocation(
        invoiceId = invoiceId,
        amount = amount
    )
}
data class CreatePaymentRequest(
    val amount: Double,
    @SerializedName("unit_id")
    val unitId: String,
    @SerializedName("building_id")
    val buildingId: String? = null,
    val method: String,
    val reference: String? = null,
    val date: String,
    val allocations: List<AllocationDto>? = null,
    val notes: String? = null,
    val bank: String? = null,
    @SerializedName("amount_currency")
    val amountCurrency: String = "USD",
    @SerializedName("proof_url")
    val proofUrl: String? = null,
    val periods: List<String>? = emptyList()
)

data class UpdateUserRequest(
    val name: String? = null,
    val phone: String? = null,
    val unit: String? = null
)

data class ApiError(
    val message: String,
    val code: String? = null
)

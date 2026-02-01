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
    val token: AuthToken,
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
    @SerializedName("user")
    val user: UserProfile
)

data class UserProfile(
    @SerializedName(value = "id", alternate = ["_id", "userId", "user_id"])
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
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
    val unitId: String? = null
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
        unitName = unitObject?.name ?: unitObject?.id ?: unitId ?: ""
    )
}

data class PaymentRequest(
    val amount: Double,
    val date: String, // "YYYY-MM-DD"
    val method: String, // "PAGO_MOVIL", "TRANSFER", "CASH"
    val reference: String? = null,
    val bank: String? = null,
    val period: String? = null // "YYYY-MM"
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

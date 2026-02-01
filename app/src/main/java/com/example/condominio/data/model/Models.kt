package com.example.condominio.data.model

import java.util.Date

data class User(
    val id: String,
    val name: String,
    val email: String,
    val apartmentUnit: String,
    val building: String = "",
    val buildingId: String = "" // New field
)

data class Payment(
    val id: String,
    val amount: Double,
    val date: Date,
    val status: PaymentStatus,
    val description: String,
    val method: PaymentMethod,
    val reference: String? = null,    // For Transfer/Pago Movil
    val bank: String? = null,         // For Transfer/Pago Movil
    val phone: String? = null,        // For Pago Movil
    val proofUrl: String? = null,     // Evidence for Cash/Others
    val paidPeriods: List<String> = emptyList(), // ["2024-01", "2024-02"]
    val createdAt: Date? = null       // When payment was reported
)

data class DashboardSummary(
    val solvencyStatus: String,
    val lastPaymentDate: Date?,
    val pendingPeriods: List<String>,
    val paidPeriods: List<String>,
    val recentTransactions: List<Payment>
)

enum class SolvencyStatus(val label: String) {
    SOLVENT("Al día"),
    PENDING("Pagos Pendientes")
}

enum class PaymentMethod(val label: String) {
    PAGO_MOVIL("Pago Móvil"),
    TRANSFERENCIA("Transferencia"),
    EFECTIVO("Efectivo")
}

enum class PaymentStatus {
    PENDING,
    APPROVED,
    REJECTED
}

data class PaymentSummary(
    val solvencyStatus: SolvencyStatus,
    val lastPaymentDate: Date?,
    val pendingPeriods: List<String>,
    val paidPeriods: List<String>,
    val recentTransactions: List<Payment>,
    val unitName: String = ""
)

class UserPendingException(message: String) : Exception(message)

package com.example.condominio.data.model

import java.util.Date

data class User(
        val id: String,
        val name: String,
        val email: String,
        val role: String = "resident",
        val status: String = "active",
        // Multi-unit support: A user can be associated with multiple units
        // The previous fields 'apartmentUnit' and 'building' are deprecated in favor of 'units'
        // but kept as derived properties or optional for backward compatibility if needed
        // temporarily.
        val units: List<UserUnit> = emptyList(),
        val buildingRoles: List<BuildingRole> = emptyList(),
        // Helper to get the primary or first unit for default display
        val currentUnit: UserUnit? = units.firstOrNull(),
        // Store building ID from profile directly regarding of units (for admins/board)
        val profileBuildingId: String? = null
) {
        // Computed properties for backward compatibility during refactor
        val apartmentUnit: String
                get() = currentUnit?.unitName ?: ""

        val building: String
                get() = currentUnit?.buildingName ?: ""

        val buildingId: String
                get() = currentUnit?.buildingId ?: profileBuildingId ?: ""
}

data class UserUnit(
        val unitId: String,
        val buildingId: String,
        val unitName: String, // e.g., "1-A"
        val buildingName: String, // e.g., "Residencias El Paraiso"
        val isPrimary: Boolean = false
)

data class BuildingRole(
        val buildingId: String,
        val role: String // e.g., "BOARD"
)

data class Payment(
        val id: String,
        val amount: Double,
        val date: Date,
        val status: PaymentStatus,
        val description: String,
        val method: PaymentMethod,
        val reference: String? = null,
        val bank: String? = null,
        val phone: String? = null,
        val proofUrl: String? = null,
        val allocations: List<PaymentAllocation> = emptyList(), // Detailed allocation
        val createdAt: Date? = null,
        val processedAt: Date? = null,
        val processorName: String? = null,
        val userName: String? = null
)

data class PaymentAllocation(
        val invoiceId: String,
        val amount: Double,
        val invoicePeriod: String? = null // Optional helper to show what was paid
)

// Billing & Invoices
data class Balance(
        val unitId: String,
        val totalDebt: Double,
        val currency: String = "USD",
        val pendingInvoices: List<Invoice>
)

data class Invoice(
        val id: String,
        val period: String, // e.g. "2024-02"
        val amount: Double, // Total original amount
        val paid: Double, // Amount already paid
        val remaining: Double,
        val status: InvoiceStatus,
        val description: String? = null,
        val dueDate: Date? = null,
        val type: InvoiceType = InvoiceType.COMMON
)

enum class InvoiceStatus {
        PENDING,
        PAID,
        CANCELLED,
        OVERDUE
}

enum class InvoiceType {
        COMMON,
        PETTY_CASH_REPLENISHMENT
}

data class DashboardSummary(
        val solvencyStatus: String,
        val lastPaymentDate: Date?,
        val recentTransactions: List<Payment>
)

enum class SolvencyStatus(val label: String) {
        SOLVENT("Al día"),
        PENDING("Pagos Pendientes")
}

enum class PaymentMethod(val label: String) {
        PAGO_MOVIL("Pago Móvil"),
        TRANSFER("Transferencia"),
        CASH("Efectivo")
}

enum class PaymentStatus {
        PENDING,
        APPROVED,
        REJECTED
}

data class PaymentSummary(
        val solvencyStatus: SolvencyStatus,
        val lastPaymentDate: Date?,
        val recentTransactions: List<Payment>,
        val unitName: String = "",
        val totalDebt: Double = 0.0
)

class UserPendingException(message: String) : Exception(message)

package com.example.condominio.data.model

import com.google.gson.annotations.SerializedName

enum class PettyCashTransactionType {
    @SerializedName("INCOME") INCOME,
    @SerializedName("EXPENSE") EXPENSE
}

enum class PettyCashCategory(val displayName: String) {
    @SerializedName("REPAIR") REPAIR("Reparación"),
    @SerializedName("CLEANING") CLEANING("Limpieza"),
    @SerializedName("EMERGENCY") EMERGENCY("Emergencia"),
    @SerializedName("OFFICE") OFFICE("Oficina"),
    @SerializedName("UTILITIES") UTILITIES("Servicios"),
    @SerializedName("OTHER") OTHER("Otro")
}

data class PettyCashBalanceDto(
    @SerializedName("current_balance") val currentBalance: Double,
    val currency: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class PettyCashTransactionDto(
    val id: String,
    val type: PettyCashTransactionType,
    val amount: Double,
    val description: String,
    val category: PettyCashCategory,
    @SerializedName("evidence_url") val evidenceUrl: String? = null,
    @SerializedName("created_at") val createdAt: String
)

data class RegisterIncomeRequest(
    @SerializedName("building_id") val buildingId: String,
    val amount: Double,
    val description: String
)

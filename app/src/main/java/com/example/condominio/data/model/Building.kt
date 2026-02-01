package com.example.condominio.data.model

import com.google.gson.annotations.SerializedName

data class Building(
    val id: String,
    val name: String,
    val address: String? = null,
    @SerializedName("rif")
    val rif: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)

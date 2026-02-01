package com.example.condominio.data.model

import com.google.gson.annotations.SerializedName

data class UnitDto(
    @SerializedName("id") val id: String,
    @SerializedName(value = "building_id", alternate = ["buildingId"]) val buildingId: String,
    @SerializedName(value = "name", alternate = ["nombre", "numero", "unit"]) val name: String,
    @SerializedName(value = "floor", alternate = ["piso"]) val floor: String?,
    @SerializedName(value = "aliquot", alternate = ["alicuota"]) val aliquot: Double?
)

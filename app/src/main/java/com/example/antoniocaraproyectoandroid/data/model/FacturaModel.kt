package com.example.antoniocaraproyectoandroid.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class FacturaModel(
    @SerializedName("fecha") val fecha: String,
    @SerializedName("importeOrdenacion") val importeOrdenacion: Double,
    @SerializedName("descEstado") val descEstado: String
)

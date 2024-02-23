package com.example.antoniocaraproyectoandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "factura")
data class FacturaEntity(
    @PrimaryKey
    val fecha: String,
    val importeOrdenacion: Double,
    val descEstado: String
)

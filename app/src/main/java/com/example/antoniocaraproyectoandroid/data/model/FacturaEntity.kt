package com.example.antoniocaraproyectoandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "factura")
data class FacturaEntity(
    @PrimaryKey
    val fecha: Date,
    val importeOrdenacion: Double,
    val descEstado: String
)

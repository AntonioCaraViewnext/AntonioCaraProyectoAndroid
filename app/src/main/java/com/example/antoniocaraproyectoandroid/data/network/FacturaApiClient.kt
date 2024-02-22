package com.example.antoniocaraproyectoandroid.data.network

import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import retrofit2.Response
import retrofit2.http.GET

data class FacturasResponse(
    val facturas: List<FacturaModel>
)

interface FacturaApiClient{
    @GET("/facturas")
    suspend fun getAllFacturas(): Response<FacturasResponse>
}
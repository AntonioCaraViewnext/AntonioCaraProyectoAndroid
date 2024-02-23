package com.example.antoniocaraproyectoandroid.data.network

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
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
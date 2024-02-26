package com.example.antoniocaraproyectoandroid.data.network

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import retrofit2.Response
import retrofit2.http.GET


interface RetromockService {
    @Mock
    @MockResponse(body = "{\"facturas\":[{\"descEstado\":\"Pendiente de pago\", \"importeOrdenacion\": 1.56 , \"fecha\": \"07/02/2019\"}]}")
    @GET("/facturas")
    suspend fun getAllFacturas(): Response<FacturasResponse>
}
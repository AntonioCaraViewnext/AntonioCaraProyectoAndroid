package com.example.antoniocaraproyectoandroid.data.network

import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FacturaService @Inject constructor(
    private val api:FacturaApiClient
) {
    suspend fun getFacturas(): FacturasResponse{
        return withContext(Dispatchers.IO){
            val response = api.getAllFacturas()
            FacturasResponse(response.body()?.facturas ?: emptyList())
        }
    }
}
package com.example.antoniocaraproyectoandroid.data

import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.model.FacturaProvider
import com.example.antoniocaraproyectoandroid.data.network.FacturaService
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import javax.inject.Inject

class FacturaRepository @Inject constructor(
    private val api: FacturaService,
    private val facturaProvider: FacturaProvider
){
    suspend fun getAllFacturas(): FacturasResponse{
        val response = api.getFacturas()
        facturaProvider.facturas = response
        return response
    }
}
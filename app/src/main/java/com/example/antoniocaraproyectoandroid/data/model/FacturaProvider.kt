package com.example.antoniocaraproyectoandroid.data.model

import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FacturaProvider @Inject constructor(){
    var facturas: FacturasResponse = FacturasResponse(emptyList())
}
package com.example.antoniocaraproyectoandroid.data

import com.example.antoniocaraproyectoandroid.data.dao.FacturaDao
import com.example.antoniocaraproyectoandroid.data.model.FacturaEntity
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.model.FacturaProvider
import com.example.antoniocaraproyectoandroid.data.network.FacturaService
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import javax.inject.Inject

class FacturaRepository @Inject constructor(
    private val api: FacturaService,
    private val facturaProvider: FacturaProvider,
    private val facturaDao : FacturaDao
){
    suspend fun getAllFacturas(): List<FacturaModel>{
        val response = api.getFacturas()
        facturaProvider.facturas = response
        return response.facturas
    }

    suspend fun getAllFacturasRoom(): MutableList<FacturaEntity>{
        val response = facturaDao.getAllTasks()
        return response
    }

    suspend fun insertFactura(facturasEntity :List<FacturaEntity>){
        facturaDao.insert(facturasEntity)
    }
    suspend fun nukeTable(){
        facturaDao.nukeTable()
    }
}
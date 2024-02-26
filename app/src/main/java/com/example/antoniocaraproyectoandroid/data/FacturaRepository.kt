package com.example.antoniocaraproyectoandroid.data

import co.infinum.retromock.Retromock
import com.example.antoniocaraproyectoandroid.data.dao.FacturaDao
import com.example.antoniocaraproyectoandroid.data.model.FacturaEntity
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.model.FacturaProvider
import com.example.antoniocaraproyectoandroid.data.network.FacturaService
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import com.example.antoniocaraproyectoandroid.data.network.RetromockService
import com.example.antoniocaraproyectoandroid.di.RetromockModule
import javax.inject.Inject

class FacturaRepository @Inject constructor(
    private val api: FacturaService,
    private val facturaProvider: FacturaProvider,
    private val facturaDao : FacturaDao,
    private val service : RetromockService
){
    suspend fun getAllFacturas(): List<FacturaModel>{
        val response = api.getFacturas()
        return if(response.facturas.isEmpty()){
            val serviceResponse = service.getAllFacturas()
            val resposeFacturaRetromock = FacturasResponse(serviceResponse.body()?.facturas ?: emptyList())
            facturaProvider.facturas = resposeFacturaRetromock
            resposeFacturaRetromock.facturas
        }else{
            facturaProvider.facturas = response
            response.facturas
        }
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
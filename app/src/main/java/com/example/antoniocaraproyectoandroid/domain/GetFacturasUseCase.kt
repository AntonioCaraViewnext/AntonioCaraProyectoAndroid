package com.example.antoniocaraproyectoandroid.domain

import com.example.antoniocaraproyectoandroid.data.FacturaRepository
import com.example.antoniocaraproyectoandroid.data.model.FacturaEntity
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import javax.inject.Inject

class GetFacturasUseCase @Inject constructor(
    private val repository: FacturaRepository
) {
    suspend operator fun invoke(switchRetrofit: Boolean):List<FacturaModel>{
        val facturas = repository.getAllFacturas(switchRetrofit)
        return if(facturas.isNotEmpty()){
            repository.nukeTable()
            repository.insertFactura(conversionModelEntity(facturas))
            facturas
        }else{
            val conversorLista = conversionEntityModel(repository.getAllFacturasRoom().toList())
            conversorLista
        }
    }

    fun conversionModelEntity(listaModel : List<FacturaModel>): List<FacturaEntity>{
        val lista : MutableList<FacturaEntity> = mutableListOf()
        for(factura in listaModel){
            lista.add(FacturaEntity(factura.fecha,factura.importeOrdenacion,factura.descEstado))
        }
        return lista.toList()
    }

    fun conversionEntityModel(listaModel : List<FacturaEntity>): List<FacturaModel>{
        val lista : MutableList<FacturaModel> = mutableListOf()
        for(factura in listaModel){
            lista.add(FacturaModel(factura.fecha,factura.importeOrdenacion,factura.descEstado))
        }
        return lista.toList()
    }

}
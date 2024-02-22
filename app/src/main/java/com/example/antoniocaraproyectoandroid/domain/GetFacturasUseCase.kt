package com.example.antoniocaraproyectoandroid.domain

import com.example.antoniocaraproyectoandroid.data.FacturaRepository
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import javax.inject.Inject

class GetFacturasUseCase @Inject constructor(
    private val repository: FacturaRepository
) {
    suspend operator fun invoke():FacturasResponse? = repository.getAllFacturas()
}
package com.example.antoniocaraproyectoandroid.domain

import com.example.antoniocaraproyectoandroid.data.FacturaRepository
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class GetFacturasUseCaseTest{


    @Mock
    private lateinit var mockRepository: FacturaRepository
    private lateinit var getFacturasUseCase: GetFacturasUseCase

    @Test
    suspend fun getUseCasePrueba(){
        val listaFacturas = getFacturasUseCase.invoke(true)
        assertEquals("Pendiente de pago", listaFacturas.first().descEstado)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
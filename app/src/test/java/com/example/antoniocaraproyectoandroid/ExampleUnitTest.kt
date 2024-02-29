package com.example.antoniocaraproyectoandroid

import com.example.antoniocaraproyectoandroid.data.FacturaRepository
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.domain.GetFacturasFiltradasUseCase
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Mock
    private lateinit var mockRepository: FacturaRepository

    private lateinit var getFacturasUseCase: GetFacturasFiltradasUseCase
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    suspend fun getUseCasePrueba(){

    }
}
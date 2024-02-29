package com.example.antoniocaraproyectoandroid.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.domain.GetFacturasFiltradasUseCase
import com.example.antoniocaraproyectoandroid.domain.GetFacturasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacturaViewModel @Inject constructor(
    private val getFacturasUseCase: GetFacturasUseCase,
    private val getFacturasFiltradasUseCase: GetFacturasFiltradasUseCase
) : ViewModel() {
    val facturaModel = MutableLiveData<List<FacturaModel>>()
    val isLoading = MutableLiveData<Boolean>()


    fun onCreate(switchRetrofit : Boolean) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFacturasUseCase(switchRetrofit)
            result.let {
                facturaModel.postValue(it)
                isLoading.postValue(false)
            }
        }
    }

    suspend fun filtros(importe: Int?, listaCheckBox : List<String?>,
                        btnDesde : String?, btnHasta : String?, switchRetrofit: Boolean){
        val listaCheckBoxMutable = listaCheckBox.toMutableList()

        val infoBtnDesde : String
        val infoBtnHasta : String

        if(("dia/mes/año" != btnDesde) && (btnDesde !== null)){
            infoBtnDesde = btnDesde
        }else{
            infoBtnDesde = "01/01/1800"
        }

        if(("dia/mes/año" != btnHasta) && (btnHasta !== null)){
            infoBtnHasta = btnHasta
        }else{
            infoBtnHasta = "01/01/3000"
        }

        val result = getFacturasFiltradasUseCase.invoke(importe,listaCheckBoxMutable,infoBtnDesde,infoBtnHasta, switchRetrofit)
        facturaModel.postValue(result)
    }
}
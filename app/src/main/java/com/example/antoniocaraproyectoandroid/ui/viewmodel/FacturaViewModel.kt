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


    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFacturasUseCase()
            result.let {
                facturaModel.postValue(it)
                isLoading.postValue(false)
            }
        }
    }

    fun filtros(importe: Int?, listaCheckBox : List<Boolean?>,
                btnDesde : String?, btnHasta : String?){
        val listaCheckBoxTrue : MutableList<Boolean> = mutableListOf()
        for (cb in listaCheckBox){
            if(cb == true){
                listaCheckBoxTrue.add(cb)
            }
        }
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
            infoBtnHasta = "01/01/1800"
        }


    }
}
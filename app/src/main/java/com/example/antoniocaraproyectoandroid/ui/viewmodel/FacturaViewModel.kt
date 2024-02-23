package com.example.antoniocaraproyectoandroid.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import com.example.antoniocaraproyectoandroid.domain.GetFacturasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacturaViewModel @Inject constructor(
    private val getFacturasUseCase: GetFacturasUseCase
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
}
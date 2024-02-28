package com.example.antoniocaraproyectoandroid.data

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.antoniocaraproyectoandroid.data.dao.FacturaDao
import com.example.antoniocaraproyectoandroid.data.model.FacturaEntity
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.model.FacturaProvider
import com.example.antoniocaraproyectoandroid.data.network.FacturaService
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import com.example.antoniocaraproyectoandroid.data.network.RetromockService
import javax.inject.Inject

class FacturaRepository @Inject constructor(
    private val api: FacturaService,
    private val facturaProvider: FacturaProvider,
    private val facturaDao : FacturaDao,
    private val service : RetromockService,
    private val context: Context
){
    private lateinit var sharedpreferences: SharedPreferences
    suspend fun getAllFacturas(): List<FacturaModel>{
        return if(isOnline(context)){
            val response = api.getFacturas()
            facturaProvider.facturas = response
            response.facturas
        }else{
            val serviceResponse = service.getAllFacturas()
            val resposeFacturaRetromock = FacturasResponse(serviceResponse.body()?.facturas ?: emptyList())
            facturaProvider.facturas = resposeFacturaRetromock
            resposeFacturaRetromock.facturas
        }
    }

    suspend fun getAllFacturasRoom(): MutableList<FacturaEntity> {
        return facturaDao.getAllTasks()
    }

    suspend fun insertFactura(facturasEntity :List<FacturaEntity>){
        facturaDao.insert(facturasEntity)
    }
    suspend fun nukeTable(){
        facturaDao.nukeTable()
    }


    //Metodo para saber si el usuario tiene conexion a internet
    suspend fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}
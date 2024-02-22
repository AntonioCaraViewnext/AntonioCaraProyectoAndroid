package com.example.antoniocaraproyectoandroid

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.antoniocaraproyectoandroid.databinding.ActivityFacturaBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antoniocaraproyectoandroid.data.adapter.FacturaAdapter
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse
import com.example.antoniocaraproyectoandroid.ui.viewmodel.FacturaViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityFacturaBinding
    private val facturaViewModel: FacturaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacturaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyler : RecyclerView = binding.recyclerFactura
        val adapter = FacturaAdapter()
        val corner : ImageView = findViewById(R.id.cornerIcon)

        corner.setImageResource(R.drawable.filtericon_3x)

        if(isOnline(this)){
            facturaViewModel.onCreate()
            facturaViewModel.facturaModel.observe(this, Observer {
                val facturas: FacturasResponse? = it
                if (facturas != null) {
                    adapter.RecyclerAdapter(it,this)
                    recyler.layoutManager = LinearLayoutManager(this)
                    recyler.adapter = adapter
                }
            })
        }

    }
}

//Metodo para saber si el usuario tiene conexion a internet
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
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
    }
    return false
}
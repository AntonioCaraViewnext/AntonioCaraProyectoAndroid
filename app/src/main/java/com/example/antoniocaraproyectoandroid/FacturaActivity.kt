package com.example.antoniocaraproyectoandroid

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antoniocaraproyectoandroid.data.adapter.FacturaAdapter
import com.example.antoniocaraproyectoandroid.databinding.ActivityFacturaBinding
import com.example.antoniocaraproyectoandroid.ui.viewmodel.FacturaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFacturaBinding
    private val facturaViewModel: FacturaViewModel by viewModels()

    //val app = applicationContext as FacturaApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacturaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyler: RecyclerView = binding.recyclerFactura
        val adapter = FacturaAdapter()

        //Cambiamos la toolbar para cuadrar con la activity
        val imCorner: ImageView = findViewById(R.id.imCornerIcon)
        imCorner.setImageResource(R.drawable.filtericon_3x)
        val tvTituloActivity: TextView = findViewById(R.id.tvTituloActivity)
        tvTituloActivity.text = resources.getText(R.string.facturas)
        val tvAnteriorActivity: TextView = findViewById(R.id.tvAnteriorActivity)
        tvAnteriorActivity.text = resources.getText(R.string.consumo)

        imCorner.setOnClickListener { intentFiltros(this) }

        facturaViewModel.onCreate()
        facturaViewModel.facturaModel.observe(this, Observer {
            adapter.RecyclerAdapter(it, this)
            recyler.layoutManager = LinearLayoutManager(this)
            recyler.adapter = adapter
        })

    }
}

fun intentFiltros(context: Context) {
    val intent = Intent(context, FiltrosActivity::class.java)
    startActivity(context, intent, null)
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
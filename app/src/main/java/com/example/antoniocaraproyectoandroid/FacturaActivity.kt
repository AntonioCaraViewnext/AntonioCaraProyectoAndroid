package com.example.antoniocaraproyectoandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed

    }

    var activityResultLauncher =registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            println("Entro en activity result ok")
            val importe = result.data?.getIntExtra("importe", 300)
            val cbPagadas = result.data?.getBooleanExtra("cbPagadas",false)
            val cbAnuladas = result.data?.getBooleanExtra("cbAnuladas",false)
            val cbCuotaFija = result.data?.getBooleanExtra("cbCuotaFija",false)
            val cbPendientePago = result.data?.getBooleanExtra("cbPendientePago",false)
            val cbPlanPago = result.data?.getBooleanExtra("cbPlanPago",false)
            val btnDesde = result.data?.getStringExtra("btnDesde")
            val btnHasta = result.data?.getStringExtra("btnHasta")
            val listaCheckBox : List<Boolean?> = listOf(cbPagadas,cbAnuladas,cbCuotaFija,cbPendientePago,cbPlanPago)
            facturaViewModel.filtros(importe,listaCheckBox,btnDesde,btnHasta)
        }else{
            println("Fallo")
        }
    }
    private fun intentFiltros(context: Context) {
        val intent = Intent(context, FiltrosActivity::class.java)
        activityResultLauncher.launch(intent)
    }
}




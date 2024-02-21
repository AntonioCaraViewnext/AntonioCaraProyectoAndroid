package com.example.antoniocaraproyectoandroid

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.example.antoniocaraproyectoandroid.databinding.ActivityFacturaBinding
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antoniocaraproyectoandroid.data.adapter.FacturaAdapter
import com.example.antoniocaraproyectoandroid.data.model.Factura


private lateinit var binding: ActivityFacturaBinding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacturaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyler : RecyclerView = binding.recyclerFactura
        val adapter = FacturaAdapter()
        val corner : ImageView = findViewById(R.id.cornerIcon)
        corner.setImageResource(R.drawable.filtericon_3x)

        adapter.RecyclerAdapter(getFacturas(),this)

        recyler.layoutManager = LinearLayoutManager(this)
        recyler.adapter = adapter

    }
    fun getFacturas() : MutableList<Factura>{
        var facturas: MutableList<Factura> = ArrayList()
        facturas.add(Factura("21 Ago 2020",31.53,true))
        facturas.add(Factura("22 Ago 2020",54.84,false))
        return facturas
    }
}
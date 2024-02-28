package com.example.antoniocaraproyectoandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.antoniocaraproyectoandroid.databinding.ActivityPantallaPrincipalBinding

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnFacturas : Button = binding.btnFacturas
        val btnSmartSolar : Button = binding.btnSmartSolar

        btnFacturas.setOnClickListener{
            val intent = Intent(it.context, MainActivity::class.java)
            startActivity(intent)
        }

        btnSmartSolar.setOnClickListener{
            val intent = Intent(it.context, SmartSolarActivity::class.java)
            startActivity(intent)
        }
    }
}
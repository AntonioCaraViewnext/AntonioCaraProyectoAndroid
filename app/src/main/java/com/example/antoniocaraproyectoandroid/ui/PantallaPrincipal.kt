package com.example.antoniocaraproyectoandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import com.example.antoniocaraproyectoandroid.databinding.ActivityPantallaPrincipalBinding

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnFacturas : Button = binding.btnFacturas
        val btnSmartSolar : Button = binding.btnSmartSolar
        val switchRetrofit : SwitchCompat = binding.switchRetrofit

        btnFacturas.setOnClickListener{
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("switchRetrofit",switchRetrofit.isChecked)
            startActivity(intent)
        }

        btnSmartSolar.setOnClickListener{
            val intent = Intent(it.context, SmartSolarActivity::class.java)
            startActivity(intent)
        }
    }
}
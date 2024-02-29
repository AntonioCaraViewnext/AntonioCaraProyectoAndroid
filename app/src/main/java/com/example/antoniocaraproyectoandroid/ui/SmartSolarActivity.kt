package com.example.antoniocaraproyectoandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.antoniocaraproyectoandroid.R
import com.example.antoniocaraproyectoandroid.databinding.ActivitySmartSolarBinding
import com.example.antoniocaraproyectoandroid.ui.smartsolarfragments.DetallesFragment
import com.example.antoniocaraproyectoandroid.ui.smartsolarfragments.EnergiaFragment
import com.example.antoniocaraproyectoandroid.ui.smartsolarfragments.InstalacionFragment
import com.google.android.material.tabs.TabLayout

class SmartSolarActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySmartSolarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmartSolarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id,
            InstalacionFragment.newInstance("","")).commit()

        val tvTituloActivity: TextView = findViewById(R.id.tvTituloActivity)
        tvTituloActivity.text = resources.getText(R.string.smart_solar)
        val tvAnteriorActivity : TextView = findViewById(R.id.tvAnteriorActivity)
        tvAnteriorActivity.text = resources.getText(R.string.smart_solar_atras)

        tvAnteriorActivity.setOnClickListener{
            val intent = Intent(it.context, PantallaPrincipal::class.java)
            startActivity(intent)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0 -> supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id,
                        InstalacionFragment.newInstance("","")).commit()
                    1 -> supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id,
                        EnergiaFragment.newInstance("","")).commit()
                    2 -> supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id,
                        DetallesFragment.newInstance("","")).commit()
                }

            }



            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
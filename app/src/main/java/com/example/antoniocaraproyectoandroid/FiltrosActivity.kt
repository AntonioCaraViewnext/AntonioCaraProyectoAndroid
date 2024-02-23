package com.example.antoniocaraproyectoandroid

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.antoniocaraproyectoandroid.databinding.ActivityFiltrosBinding
import java.util.Calendar

class FiltrosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFiltrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Cambiamos la toolbar para cuadrar con la activity
        val imCorner : ImageView = findViewById(R.id.imCornerIcon)
        imCorner.setImageResource(R.drawable.close_icon)
        val tvTituloActivity: TextView = findViewById(R.id.tvTituloActivity)
        tvTituloActivity.text = resources.getText(R.string.filros)
        val imFlechaAnteriorActivity: ImageView = findViewById(R.id.imFlechaAnteriorActivity)
        imFlechaAnteriorActivity.visibility = View.GONE

        imCorner.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            ContextCompat.startActivity(it.context, intent, null)
        }

        val skImporte : SeekBar = binding.skImporte
        val tvImporte : TextView = binding.tvImporte

        val btnDesde : Button = binding.btnDesde
        val btnHasta : Button = binding.btnHasta

        btnDesde.setOnClickListener {
            establecerFecha(it.context,btnDesde)
        }
        btnHasta.setOnClickListener {
            establecerFecha(it.context,btnHasta)
        }


        skImporte.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val textoImporte = skImporte.progress.toString()+"€"
                tvImporte.text = textoImporte
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                val textoImporte = skImporte.progress.toString()+"€"
                tvImporte.text = textoImporte
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val textoImporte = skImporte.progress.toString()+"€"
                tvImporte.text = textoImporte
            }

        })
    }

    //Establecemos la fecha con un DatePickerDialog
    fun establecerFecha(context: Context, btnDesde: Button) {
        println("Entro en el listener")
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            println("Entro en el dialog")
            val texto : String = "$dayOfMonth / $month / $year"
            btnDesde.setText(texto)

        }, year, month, day)
        dpd.show()
    }
}
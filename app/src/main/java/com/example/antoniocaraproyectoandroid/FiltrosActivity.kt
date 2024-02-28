package com.example.antoniocaraproyectoandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import com.example.antoniocaraproyectoandroid.data.model.FacturaEntity
import com.example.antoniocaraproyectoandroid.databinding.ActivityFiltrosBinding
import java.util.Calendar


class FiltrosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFiltrosBinding
    private lateinit var sharedpreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Cambiamos la toolbar para cuadrar con la activity
        val imCorner: ImageView = findViewById(R.id.imCornerIcon)
        imCorner.setImageResource(R.drawable.close_icon)
        val tvTituloActivity: TextView = findViewById(R.id.tvTituloActivity)
        tvTituloActivity.text = resources.getText(R.string.filros)
        val imFlechaAnteriorActivity: ImageView = findViewById(R.id.imFlechaAnteriorActivity)
        imFlechaAnteriorActivity.visibility = View.GONE

        sharedpreferences = getSharedPreferences("filtros", MODE_PRIVATE)
        imCorner.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("importe",sharedpreferences.getInt("importe", 300))
            intent.putExtra("cbPagadas",sharedpreferences.getBoolean("cbPagadas", false))
            intent.putExtra("cbAnuladas",sharedpreferences.getBoolean("cbAnuladas", false))
            intent.putExtra("cbCuotaFija",sharedpreferences.getBoolean("cbCuotaFija", false))
            intent.putExtra("cbPendientePago",sharedpreferences.getBoolean("cbPendientePago", false))
            intent.putExtra("cbPlanPago",sharedpreferences.getBoolean("cbPlanPago", false))
            intent.putExtra("btnHasta",sharedpreferences.getString("btnHasta", resources.getResourceEntryName(R.string.filtros_dia_mes_anio)))
            intent.putExtra("btnDesde",sharedpreferences.getString("btnDesde", resources.getResourceEntryName(R.string.filtros_dia_mes_anio)))
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

        val skImporte: SeekBar = binding.skImporte
        val tvImporte: TextView = binding.tvImporte

        val cbPagadas: CheckBox = binding.cbPagadas
        val cbAnuladas: CheckBox = binding.cbAnuladas
        val cbCuotaFija: CheckBox = binding.cbCuotaFija
        val cbPendientePago: CheckBox = binding.cbPendientePago
        val cbPlanPago: CheckBox = binding.cbPlanPago

        val btnDesde: Button = binding.btnDesde
        val btnHasta: Button = binding.btnHasta

        val btnAplicar: Button = binding.btnAplicar
        val btnEliminar: Button = binding.btnEliminar

        btnDesde.setOnClickListener {
            establecerFecha(it.context, btnDesde)
        }

        btnHasta.setOnClickListener {
            establecerFecha(it.context, btnHasta)
        }

        btnAplicar.setOnClickListener {
            sharedpreferences.edit { putInt("importe", skImporte.progress) }

            sharedpreferences.edit { putBoolean("cbPagadas", cbPagadas.isChecked) }
            sharedpreferences.edit { putBoolean("cbAnuladas", cbAnuladas.isChecked) }
            sharedpreferences.edit { putBoolean("cbCuotaFija", cbCuotaFija.isChecked) }
            sharedpreferences.edit { putBoolean("cbPendientePago", cbPendientePago.isChecked) }
            sharedpreferences.edit { putBoolean("cbPlanPago", cbPlanPago.isChecked) }

            sharedpreferences.edit { putString("btnHasta", btnDesde.text.toString()) }
            sharedpreferences.edit { putString("btnDesde", btnDesde.text.toString()) }

        }

        btnEliminar.setOnClickListener {
            sharedpreferences.edit { putInt("importe", 300) }
            tvImporte.text = "1€"
            skImporte.progress = 1

            sharedpreferences.edit { putBoolean("cbPagadas", false) }
            sharedpreferences.edit { putBoolean("cbAnuladas", false) }
            sharedpreferences.edit { putBoolean("cbCuotaFija", false) }
            sharedpreferences.edit { putBoolean("cbPendientePago", false) }
            sharedpreferences.edit { putBoolean("cbPlanPago", false) }

            cbPagadas.isChecked = false
            cbAnuladas.isChecked = false
            cbCuotaFija.isChecked = false
            cbPendientePago.isChecked = false
            cbPlanPago.isChecked = false

            sharedpreferences.edit { putString("btnDesde",resources.getString(R.string.filtros_dia_mes_anio))}
            sharedpreferences.edit { putString("btnHasta",resources.getString(R.string.filtros_dia_mes_anio))}

            btnDesde.text = resources.getString(R.string.filtros_dia_mes_anio)
            btnHasta.text = resources.getString(R.string.filtros_dia_mes_anio)
        }


        skImporte.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val textoImporte = skImporte.progress.toString() + "€"
                tvImporte.text = textoImporte
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                val textoImporte = skImporte.progress.toString() + "€"
                tvImporte.text = textoImporte
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val textoImporte = skImporte.progress.toString() + "€"
                tvImporte.text = textoImporte
            }

        })
    }

    @SuppressLint("CommitPrefEdits", "SetTextI18n")
    override fun onResume() {
        super.onResume()
        val skImporte: SeekBar = binding.skImporte
        val tvImporte: TextView = binding.tvImporte
        val checkboxList: List<CheckBox> = listOf(
            binding.cbPagadas,
            binding.cbAnuladas,
            binding.cbCuotaFija,
            binding.cbPendientePago,
            binding.cbPlanPago
        )

        val btnDesde: Button = binding.btnDesde
        val btnHasta: Button = binding.btnHasta

        skImporte.progress = sharedpreferences.getInt("importe", 300)
        tvImporte.text = sharedpreferences.getInt("importe", 300).toString() + "€"

        for (checkbox in checkboxList) {
            checkbox.isChecked =
                sharedpreferences.getBoolean(resources.getResourceEntryName(checkbox.id), false)
        }

        btnDesde.text = sharedpreferences.getString("btnDesde",resources.getString(R.string.filtros_dia_mes_anio))
        btnHasta.text = sharedpreferences.getString("btnHasta",resources.getString(R.string.filtros_dia_mes_anio))
    }

    //Establecemos la fecha con un DatePickerDialog
    private fun establecerFecha(context: Context, btnDesde: Button) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            context,
            { _, year, _, dayOfMonth ->
                val texto = "$dayOfMonth / $month / $year"
                btnDesde.text = texto

            },
            year,
            month,
            day
        )
        dpd.show()
    }
}
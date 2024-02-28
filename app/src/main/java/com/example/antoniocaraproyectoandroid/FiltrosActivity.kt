package com.example.antoniocaraproyectoandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
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
import androidx.core.content.edit
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

        val checkboxList: List<CheckBox> = listOf(
            binding.cbPagada,
            binding.cbAnuladas,
            binding.cbCuotaFija,
            binding.cbPendientePago,
            binding.cbPlanPago
        )

        sharedpreferences = getSharedPreferences("filtros", MODE_PRIVATE)
        imCorner.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("importe", sharedpreferences.getInt("importe", 300))

            //Guardamos en un intent el String de los checkbox
            for (cb in checkboxList) {
                if (sharedpreferences.getBoolean(cb.resources.getResourceEntryName(cb.id), true)) {
                    intent.putExtra(
                        cb.resources.getResourceEntryName(cb.id) + "String",
                        cb.text.toString()
                    )
                } else {
                    intent.putExtra(cb.resources.getResourceEntryName(cb.id) + "String", " ")
                }
            }

            intent.putExtra("cbPagada", sharedpreferences.getBoolean("cbPagada", true))
            intent.putExtra("cbAnuladas", sharedpreferences.getBoolean("cbAnuladas", true))
            intent.putExtra("cbCuotaFija", sharedpreferences.getBoolean("cbCuotaFija", true))
            intent.putExtra("cbPendientePago", sharedpreferences.getBoolean("cbPendientePago", true))
            intent.putExtra("cbPlanPago", sharedpreferences.getBoolean("cbPlanPago", true))
            intent.putExtra(
                "btnHasta",
                sharedpreferences.getString("btnHasta",
                    resources.getString(R.string.filtros_dia_mes_anio)
                )
            )
            intent.putExtra(
                "btnDesde",
                sharedpreferences.getString("btnDesde",
                    resources.getString(R.string.filtros_dia_mes_anio)
                )
            )
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        val skImporte: SeekBar = binding.skImporte
        val tvImporte: TextView = binding.tvImporte

        val cbPagada: CheckBox = binding.cbPagada
        val cbAnuladas: CheckBox = binding.cbAnuladas
        val cbCuotaFija: CheckBox = binding.cbCuotaFija
        val cbPendientePago: CheckBox = binding.cbPendientePago
        val cbPlanPago: CheckBox = binding.cbPlanPago

        val btnDesde: Button = binding.btnDesde
        val btnHasta: Button = binding.btnHasta

        val btnAplicar: Button = binding.btnAplicar
        val btnEliminar: Button = binding.btnEliminar

        btnDesde.setOnClickListener {
            establecerFecha(btnDesde)
        }

        btnHasta.setOnClickListener {
            establecerFecha(btnHasta)
        }

        btnAplicar.setOnClickListener {
            sharedpreferences.edit { putInt("importe", skImporte.progress) }

            sharedpreferences.edit { putBoolean("cbPagada", cbPagada.isChecked) }
            sharedpreferences.edit { putBoolean("cbAnuladas", cbAnuladas.isChecked) }
            sharedpreferences.edit { putBoolean("cbCuotaFija", cbCuotaFija.isChecked) }
            sharedpreferences.edit { putBoolean("cbPendientePago", cbPendientePago.isChecked) }
            sharedpreferences.edit { putBoolean("cbPlanPago", cbPlanPago.isChecked) }

            //Guardamos en un intent el String de los checkbox
            for (cb in checkboxList) {
                if (sharedpreferences.getBoolean(cb.resources.getResourceEntryName(cb.id), true)) {
                    intent.putExtra(
                        cb.resources.getResourceEntryName(cb.id) + "String",
                        cb.text.toString()
                    )
                } else {
                    intent.putExtra(cb.resources.getResourceEntryName(cb.id) + "String", " ")
                }
            }

            sharedpreferences.edit { putString("btnDesde", btnDesde.text.toString()) }
            sharedpreferences.edit { putString("btnHasta", btnHasta.text.toString()) }

        }

        btnEliminar.setOnClickListener {
            sharedpreferences.edit { putInt("importe", 300) }
            tvImporte.text = "300€"
            skImporte.progress = 300

            sharedpreferences.edit { putBoolean("cbPagada", true) }
            sharedpreferences.edit { putBoolean("cbAnuladas", true) }
            sharedpreferences.edit { putBoolean("cbCuotaFija", true) }
            sharedpreferences.edit { putBoolean("cbPendientePago", true) }
            sharedpreferences.edit { putBoolean("cbPlanPago", true) }

            //Guardamos en un intent el String de los checkbox
            for (cb in checkboxList) {
                if (sharedpreferences.getBoolean(cb.resources.getResourceEntryName(cb.id), true)) {
                    intent.putExtra(
                        cb.resources.getResourceEntryName(cb.id) + "String",
                        cb.text.toString()
                    )
                } else {
                    intent.putExtra(cb.resources.getResourceEntryName(cb.id) + "String", " ")
                }
            }

            cbPagada.isChecked = true
            cbAnuladas.isChecked = true
            cbCuotaFija.isChecked = true
            cbPendientePago.isChecked = true
            cbPlanPago.isChecked = true

            sharedpreferences.edit {
                putString(
                    "btnDesde",
                    resources.getString(R.string.filtros_dia_mes_anio)
                )
            }
            sharedpreferences.edit {
                putString(
                    "btnHasta",
                    resources.getString(R.string.filtros_dia_mes_anio)
                )
            }

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
            binding.cbPagada,
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
                sharedpreferences.getBoolean(resources.getResourceEntryName(checkbox.id), true)
        }

        btnDesde.text = sharedpreferences.getString(
            "btnDesde",
            resources.getString(R.string.filtros_dia_mes_anio)
        )
        btnHasta.text = sharedpreferences.getString(
            "btnHasta",
            resources.getString(R.string.filtros_dia_mes_anio)
        )
    }

    //Establecemos la fecha con un DatePickerDialog
    private fun establecerFecha(btnDesde: Button) {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, { _, anio, mes, dia ->

                val diaString : String

                diaString = if(dia<10){
                    "0$dia"
                }else{
                    dia.toString()
                }
                val mesString : String

                mesString = if(mes<10){
                    "0${mes+1}"
                }else{
                    (mes+1).toString()
                }

                val selectedDate = "$diaString/$mesString/$anio"

                btnDesde.text = selectedDate
            },
            year, month, dayOfMonth
        )

        datePickerDialog.show()
    }
}
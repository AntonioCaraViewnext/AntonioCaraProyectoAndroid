package com.example.antoniocaraproyectoandroid.smartsolarfragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.antoniocaraproyectoandroid.R
import com.example.antoniocaraproyectoandroid.databinding.FragmentDetallesBinding

class DetallesFragment : Fragment() {

    private lateinit var binding: FragmentDetallesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetallesBinding.inflate(layoutInflater)
        binding.imgInfo.setOnClickListener {
            println("Entrando en el evento")
            val builder = AlertDialog.Builder(context)
            val inflater: LayoutInflater = LayoutInflater.from(context)
            val view : View = inflater.inflate(R.layout.popup_estado_solicitud,null)
            builder.setView(view)
            val dialog : AlertDialog = builder.create()
            dialog.show()
            //Cerramos el dialogo
            val boton : Button = view.findViewById(R.id.btnAceptar)
            boton.setOnClickListener { dialog.dismiss() }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {

        @JvmStatic fun newInstance(param1: String, param2: String) =
                DetallesFragment().apply {

                }
    }
}
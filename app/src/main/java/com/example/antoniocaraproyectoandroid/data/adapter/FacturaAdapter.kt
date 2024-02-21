package com.example.antoniocaraproyectoandroid.data.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.material3.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.antoniocaraproyectoandroid.R
import com.example.antoniocaraproyectoandroid.data.model.Factura

class FacturaAdapter : RecyclerView.Adapter<FacturaAdapter.ViewHolder>() {
    var facturas: MutableList<Factura> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(facturas: MutableList<Factura>, context: Context) {
        this.facturas = facturas
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.facturas_recycle, parent, false))
    }

    override fun getItemCount(): Int {
        return facturas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = facturas.get(position)
        holder.bind(item, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fecha = view.findViewById(R.id.tvFecha) as TextView
        val pendiente = view.findViewById(R.id.tvPendiente) as TextView
        val coste = view.findViewById(R.id.tvCoste) as TextView
        fun bind(factura : Factura, context: Context) {
            fecha.text = factura.factura
            if(factura.pendiente){
                pendiente.text = "Pediente de pago"
            }else{
                pendiente.text = ""
            }
            coste.text = factura.coste.toString()

            //Alerta de dialogo indicando que no está disponible la función
            itemView.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(context)
                val inflater: LayoutInflater = LayoutInflater.from(context)
                val view : View = inflater.inflate(R.layout.popup_facturas,null)
                builder.setView(view)
                val dialog : AlertDialog = builder.create()
                dialog.show()
                //Cerramos el dialogo
                val boton : Button = view.findViewById(R.id.btnCerrar)
                boton.setOnClickListener { dialog.dismiss() }
            })
        }
    }
}
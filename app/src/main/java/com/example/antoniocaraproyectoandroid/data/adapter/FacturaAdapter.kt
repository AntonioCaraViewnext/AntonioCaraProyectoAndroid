package com.example.antoniocaraproyectoandroid.data.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.antoniocaraproyectoandroid.R
import com.example.antoniocaraproyectoandroid.data.model.FacturaModel
import com.example.antoniocaraproyectoandroid.data.network.FacturasResponse

class FacturaAdapter : RecyclerView.Adapter<FacturaAdapter.ViewHolder>() {
    var facturaModels: List<FacturaModel> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(facturaModels: List<FacturaModel>, context: Context) {
        this.facturaModels = facturaModels
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.facturas_recycle, parent, false))
    }

    override fun getItemCount(): Int {
        return facturaModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = facturaModels.get(position)
        holder.bind(item, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fecha = view.findViewById(R.id.tvFecha) as TextView
        val pendiente = view.findViewById(R.id.tvPendiente) as TextView
        val coste = view.findViewById(R.id.tvCoste) as TextView
        fun bind(facturaModel : FacturaModel, context: Context) {
            fecha.text = facturaModel.fecha.toString()
            if(facturaModel.descEstado == "Pendiente de pago"){
                pendiente.text = facturaModel.descEstado
            }else{
                pendiente.text = ""
            }
            coste.text = facturaModel.importeOrdenacion.toString()

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

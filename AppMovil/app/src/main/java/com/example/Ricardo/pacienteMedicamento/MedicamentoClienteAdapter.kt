package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class MedicamentoClienteAdapter(private val medicamentoList: List<Medicamento>) :  RecyclerView.Adapter<MedicamentoClienteAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var composicion : TextView
        var usadoPara: TextView
        var detalles: Button

        lateinit var medicamentoM1: Medicamento

        init {
            nombre = view.findViewById(R.id.txtNombrePaciente) as TextView
            composicion = view.findViewById(R.id.txtApellidoPaciente) as TextView
            usadoPara = view.findViewById(R.id.txtFechaNacimientoPaciente) as TextView
            detalles = view.findViewById(R.id.btnDetallesPaciente) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_paciente_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val medicamentoM= medicamentoList[position]
        holder.nombre.text = medicamentoM.nombre
        holder.composicion.text = medicamentoM.composicion
        holder.usadoPara.text = medicamentoM.usadoPara
        holder.medicamentoM1 = medicamentoM
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesMedicamentoClienteActivity::class.java)
            intent.putExtra("detallesMedicamentoCliente", medicamentoM)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return medicamentoList.size
    }


}
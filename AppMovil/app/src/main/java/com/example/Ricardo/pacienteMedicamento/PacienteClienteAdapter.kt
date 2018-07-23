package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class PacienteClienteAdapter(private val pacienteList: List<Paciente>) :  RecyclerView.Adapter<PacienteClienteAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var apellido : TextView
        var fechaNacimiento: TextView
        var detalles: Button

        lateinit var paciente: Paciente

        init {
            nombre = view.findViewById(R.id.txtNombrePaciente) as TextView
            apellido = view.findViewById(R.id.txtApellidoPaciente) as TextView
            fechaNacimiento = view.findViewById(R.id.txtFechaNacimientoPaciente) as TextView
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
        val paciente = pacienteList[position]
        holder.nombre.text = paciente.nombres
        holder.apellido.text = paciente.apellidos
        holder.fechaNacimiento.text = paciente.fechaNacimiento
        holder.paciente = paciente
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesPacienteClienteActivity::class.java)
            intent.putExtra("detallesPacienteCliente", paciente)

            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return pacienteList.size
    }


}
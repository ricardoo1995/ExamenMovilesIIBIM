package com.example.ricardo0.paciente_medicina.Vendedor

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ricardo0.paciente_medicina.R

class MedicinaAdaptador(private val listaMedicinas: List<Medicina>,valor: Int) : RecyclerView.Adapter<MedicinaAdaptador.MyViewHolder>() {

    private var position: Int = 0
    var tipo = valor
    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var costo: TextView
        var estado: TextView
        var usadaPara: Button
        lateinit var medicina: Medicina
        var layout: RelativeLayout


        init {
            nombre = view.findViewById(R.id.txt_1) as TextView
            costo = view.findViewById(R.id.txt_2) as TextView
            estado = view.findViewById(R.id.txt_3) as TextView
            usadaPara = view.findViewById(R.id.btn_1) as Button
            layout = view.findViewById(R.id.relative_layout) as RelativeLayout

            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {

            when(tipo){
                1 -> {
                    menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Seleccionar")
                }
                2 -> {
                    menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Quitar")
                }
                else -> {
                    Log.i("Error","Error")
                }
            }

            //menu?.add(Menu.NONE, R.id.item_menu_borrar, Menu.NONE, "Quitar")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val medicinas = listaMedicinas[position]
        holder.nombre.text = medicinas.nombre
        holder.costo.text = medicinas.precio
        // Get the dimensions of the bitmap
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(medicinas.foto, bmOptions)

        // Determine how much to scale down the image
        //val scaleFactor = Math.min(photoW / targetW, photoH / targetH)

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false
        //bmOptions.inSampleSize = scaleFactor
        bmOptions.inPurgeable = true

        val bitmap = BitmapFactory.decodeFile(medicinas.foto, bmOptions)


        when (medicinas.estado) {
            1 -> {
                holder.estado.text = "Disponible"
            }
            2 -> {
                holder.estado.text = "Seleccionado"
            }
            3 -> {
                holder.estado.text = "No Disponible"
            }
            else -> {
                Log.i("Error", "Error")
            }
        }

        holder.usadaPara.setBackgroundColor(Color.GRAY)
        holder.medicina = medicinas

        holder.usadaPara.setOnClickListener { view: View ->
            var intent = Intent(view.context, DetailsMedicinaActivity::class.java)
            intent.putExtra("medicina", medicinas)
            startActivity(view.context, intent, null)

        }

        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
        /*holder.layout.setOnClickListener { view ->
            val popup = PopupMenu(view.context, view)
            popup.setOnMenuItemClickListener(this)
            val inflater = popup.menuInflater
            inflater.inflate(R.menu.pop_up_menu, popup.menu)
            popup.show()
        }*/
    }

    override fun getItemCount(): Int {
        return listaMedicinas.size
    }
}
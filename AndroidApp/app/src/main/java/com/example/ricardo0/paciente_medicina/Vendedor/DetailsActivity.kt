package com.example.ricardo0.paciente_medicina.Vendedor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ricardo0.paciente_medicina.BaseDeDatos.DataBaseMedicina
import com.example.ricardo0.paciente_medicina.Parceleable.Paciente
import com.example.ricardo0.paciente_medicina.R
import kotlinx.android.synthetic.main.activity_details.*
class DetailsActivity : AppCompatActivity() {

    var paciente: Paciente? = null
    lateinit var adaptador: MedicinaAdapter
    lateinit var medicina: ArrayList<Medicina>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        paciente = intent.getParcelableExtra("paciente")

        txtShowNombreAutor.text = paciente?.nombre
        txtShowApellidoAutor.text = paciente?.apellido
        txtShowFechaNAutor.text = paciente?.fechaNacimiento
        txtShowNumLibAutor.text = paciente?.numeroHijos.toString()
        txtShowEcuAutor.text = if(paciente?.ecuatoriano == 1) getString(R.string.yes) else getString(R.string.no)

        medicina = DataBaseMedicina.getMedicinaList(paciente?.id!!)

        val layoutManager = LinearLayoutManager(this)
        adaptador = MedicinaAdapter(medicina)
        recycler_view_book.layoutManager = layoutManager
        recycler_view_book.itemAnimator = DefaultItemAnimator()
        recycler_view_book.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_book)

        btnNuevoLibro.setOnClickListener{
            v: View? ->  crearMedicina()
        }


    }

    fun crearMedicina() {
        val intent = Intent(this, CreateMedicinaActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("idPaciente", paciente?.id!!)
        startActivity(intent)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var medicina = medicina[position]

        when (item.itemId) {
            R.id.item_menu_compartir1 -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_SUBJECT, "${getString(R.string.medicina)} - ${getString(R.string.app_name)}")
                intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.isbn)} ${medicina.gramosAConsumir}\n${getString(R.string.name)} ${medicina.nombre}\n${getString(R.string.edicion)} ${medicina.numeroPastillas}\n${getString(R.string.editorial)} ${medicina.usadaPara}")
                startActivity(intent)
                return true
            }
            R.id.item_menu_editar -> {
                val intent = Intent(this, CreateMedicinaActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("medicina", medicina)
                startActivity(intent)
                return true
            }
            R.id.item_menu_borrar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(R.string.confirmation)
                        .setPositiveButton(R.string.yes, { dialog, which ->
                            DataBaseMedicina.deleteMedicina(medicina.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton(R.string.no, null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.item_menu_pokemon -> {
                Log.i("menu", "Menu pokemon")
                return true
            }


            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }
}

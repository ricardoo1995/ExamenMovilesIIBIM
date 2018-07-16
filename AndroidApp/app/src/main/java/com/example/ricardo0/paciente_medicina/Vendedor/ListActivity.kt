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
import com.example.ricardo0.paciente_medicina.BaseDeDatos.DataBasePaciente
import com.example.ricardo0.paciente_medicina.Parceleable.Paciente
import com.example.ricardo0.paciente_medicina.R
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var adaptador: PacienteAdapter
    lateinit var paciente: ArrayList<Paciente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        paciente = DataBasePaciente.getList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = PacienteAdapter(paciente)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var paciente = paciente[position]

        when (item.itemId) {
            R.id.item_menu_compartir1 -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_SUBJECT, "${getString(R.string.autor)} - ${getString(R.string.app_name)}")
                intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.name)} ${paciente.nombre} ${paciente.apellido}\n${getString(R.string.numero_libros)} ${paciente.numeroHijos}\n${getString(R.string.fecha_nacimiento)} ${paciente.fechaNacimiento}")
                startActivity(intent)
                return true
            }
            R.id.item_menu_editar -> {
                val intent = Intent(this, CreateActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("paciente", paciente)
                startActivity(intent)
                return true
            }
            R.id.item_menu_borrar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(R.string.confirmation)
                        .setPositiveButton(R.string.yes, { dialog, which ->
                            DataBasePaciente.deleteAutor(paciente.id)
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
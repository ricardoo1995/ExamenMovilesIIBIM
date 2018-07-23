package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_listar_pacientes.*

class ListarPacientesActivity : AppCompatActivity() {

    lateinit var adaptador: PacienteAdapter
    lateinit var pacientes: ArrayList<Paciente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_pacientes)

        pacientes = BaseDatosPaciente.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = PacienteAdapter(pacientes)
        recyclerViewPaciente.layoutManager = layoutManager
        recyclerViewPaciente.itemAnimator = DefaultItemAnimator()
        recyclerViewPaciente.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerViewPaciente)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var paciente = pacientes[position]

        when (item.itemId) {

            R.id.item_menu_editar -> {
                val intent = Intent(this, PacienteActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("paciente", paciente)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            BaseDatosPaciente.eliminarPaciente(paciente.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
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
}

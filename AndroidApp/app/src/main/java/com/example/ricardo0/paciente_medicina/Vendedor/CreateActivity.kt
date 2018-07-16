package com.example.ricardo0.paciente_medicina.Vendedor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ricardo0.paciente_medicina.BaseDeDatos.DataBasePaciente
import com.example.ricardo0.paciente_medicina.Parceleable.Paciente
import com.example.ricardo0.paciente_medicina.R
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    var paciente: Paciente? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewAutor.text = getString(R.string.edit_autor)
            paciente = intent.getParcelableExtra("Paciente")
            fillFields()
            tipo = true
        }

        btnCrearAutor.setOnClickListener{
            v: View? -> crearPaciente()
        }
    }

    fun fillFields() {
        txtNombreAutor.setText(paciente?.nombre)
        txtApellidoAutor.setText(paciente?.apellido)
        txtFechaAutor.setText(paciente?.fechaNacimiento)
        txtNumeroLibrosAutor.setText(paciente?.numeroHijos.toString())
        if (paciente?.ecuatoriano == 1) {
            switchEcAutor.toggle()
        }
    }

    fun crearPaciente() {
        var nombre = txtNombreAutor.text.toString()
        var apellido = txtApellidoAutor.text.toString()
        var fecha = txtFechaAutor.text.toString()
        var numeroHijos = txtNumeroLibrosAutor.text.toString().toInt()
        var ecutoriano = if (switchEcAutor.isChecked) 1 else 0

        if (!tipo) {
            var paciente = Paciente(0, nombre, apellido, fecha, numeroHijos, ecutoriano, 0, 0)
            DataBasePaciente.insertarPaciente(paciente)
        } else {
            var paciente = Paciente(paciente?.id!!, nombre, apellido, fecha, numeroHijos, ecutoriano, 0, 0)
            DataBasePaciente.updatePaciente(paciente)
        }

        irAListView()
    }

    fun irAListView() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
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

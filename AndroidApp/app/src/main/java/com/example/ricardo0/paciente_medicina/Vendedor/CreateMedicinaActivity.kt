package com.example.ricardo0.paciente_medicina.Vendedor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ricardo0.paciente_medicina.BaseDeDatos.DataBaseMedicina
import com.example.ricardo0.paciente_medicina.R
import kotlinx.android.synthetic.main.activity_create_medicina.*

class CreateMedicinaActivity : AppCompatActivity() {

    var idPaciente: Int = 0
    var idMedicina: Int = 0
    var medicina: Medicina? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_medicina)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewLibro.text = getString(R.string.edit_book)
            medicina = intent.getParcelableExtra("medicina")
            idMedicina = medicina!!.id
            fillFields()
            tipo = true
        }

        idPaciente = intent.getIntExtra("idPaciente", 0)

        btnCrearLibro.setOnClickListener { v: View? ->
            crearMedicina()
        }
        buttonCamara1.setOnClickListener{v: View? ->  irCamara()}
    }

    fun fillFields() {
        txtISBNLibro.setText(medicina?.gramosAConsumir)
        txtNombreLibro.setText(medicina?.nombre)
        txtNPagLibro.setText(medicina?.numeroPastillas.toString())
        txtEdicionLibro.setText(medicina?.composicion)
        txtFechaPLibro.setText(medicina?.fechaCaducidad)
        txtEditorialLibro.setText(medicina?.usadaPara)
        txtEditorialLibro2.setText(medicina?.precio)
        txtEditorialLibro4.setText(medicina?.estado.toString())
       txtEditorialLibro.setText(medicina?.foto)
    }

    fun crearMedicina() {
        var gramosAConsumir = txtISBNLibro.text.toString()
        var nombre = txtNombreLibro.text.toString()
        var numeroPastillas = txtNPagLibro.text.toString().toInt()
        var composicion = txtEdicionLibro.text.toString()
        var fechaCaducidad = txtFechaPLibro.text.toString()
        var usadaPara = txtEditorialLibro.text.toString()
        var precio = txtEditorialLibro2.text.toString()
        var estado= txtEditorialLibro4.text.toString().toInt()
        var foto = txtEditorialLibro.text.toString()
        var medicina = Medicina(this.idMedicina, gramosAConsumir, nombre, numeroPastillas, composicion, fechaCaducidad, usadaPara, precio, estado, foto, idPaciente, 0, 0)

        if (!tipo) {
            DataBaseMedicina.insertarMedicina(medicina)
        } else {
            DataBaseMedicina.updateMedicina(medicina)
        }

        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }
    fun irCamara(){
        val intent = Intent(this, TomarFotoActivity::class.java)
        startActivity(intent)
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
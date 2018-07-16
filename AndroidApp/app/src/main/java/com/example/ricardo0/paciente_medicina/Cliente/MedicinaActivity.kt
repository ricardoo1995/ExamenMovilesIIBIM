package com.example.ricardo0.paciente_medicina.Cliente

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ricardo0.paciente_medicina.OrdenesClientesActivity
import com.example.ricardo0.paciente_medicina.R
import com.example.ricardo0.paciente_medicina.Vendedor.ListaMedicinaActivity
import kotlinx.android.synthetic.main.activity_medicina.*

class MedicinaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicina)
        button_Medicinas.setOnClickListener { v: View? -> irActividadMedicina() }
        button_find.setOnClickListener { v: View? -> irbuscar() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu1, menu)
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
    fun irActividadMedicina(){
        var intent = Intent(this, OrdenesClientesActivity::class.java)
        startActivity(intent)
    }
    fun irbuscar(){
        var intent = Intent(this, FindActivity::class.java)
        startActivity(intent)
    }
}

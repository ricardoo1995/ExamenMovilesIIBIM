package com.example.ricardo0.paciente_medicina.Vendedor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.ricardo0.paciente_medicina.R
import kotlinx.android.synthetic.main.activity_details_medicina.*

class DetailsMedicinaActivity : AppCompatActivity() {

    var medicina: Medicina? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_medicina)

        medicina= intent.getParcelableExtra("medicina")

        txtShowIsbn.text = medicina?.gramosAConsumir
        txtShowNombreLibro.text = medicina?.nombre
        txtShowNumPagLibro.text = medicina?.numeroPastillas.toString()
        txtShowEdicLibro.text = medicina?.composicion
        txtShowFechaPLibro.text = medicina?.fechaCaducidad
        txtShowEditorialLibro.text = medicina?.usadaPara
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

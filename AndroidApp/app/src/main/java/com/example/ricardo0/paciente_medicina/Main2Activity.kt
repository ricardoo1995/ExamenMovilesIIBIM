package com.example.ricardo0.paciente_medicina

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ricardo0.paciente_medicina.Vendedor.CreateActivity
import com.example.ricardo0.paciente_medicina.Vendedor.ListActivity
import com.onesignal.OneSignal
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_crear.setOnClickListener{
            v: View? ->  irACreateView()
        }

        btn_listar.setOnClickListener{
            v: View? ->  irAListView()
        }
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        Alerter.create(this@Main2Activity)
                .setTitle("Hola")
                .setText("Bienvenido")
                .show()

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
    fun irAListView() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    fun irACreateView() {
        val intent = Intent(this, CreateActivity::class.java)
        intent.putExtra("tipo", "Create")
        startActivity(intent)
    }
}

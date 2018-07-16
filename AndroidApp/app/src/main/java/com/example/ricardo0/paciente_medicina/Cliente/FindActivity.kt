package com.example.ricardo0.paciente_medicina.Cliente

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.ricardo0.paciente_medicina.BaseDeDatos.DataBaseMedicina
import com.example.ricardo0.paciente_medicina.CarritoComprasActivity
import com.example.ricardo0.paciente_medicina.R
import com.example.ricardo0.paciente_medicina.Vendedor.Medicina
import com.example.ricardo0.paciente_medicina.Vendedor.MedicinaAdaptador
import kotlinx.android.synthetic.main.activity_find.*

class FindActivity : AppCompatActivity() {
    lateinit var adaptador: MedicinaAdaptador
    lateinit var aplicaciones: ArrayList<Medicina>
    lateinit var arregloIds: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)
        arregloIds = ArrayList()
        boton_buscar.setOnClickListener { view: View ->
            val busqueda = text_buscar.text.toString().toInt()
            aplicaciones = DataBaseMedicina.getMedicinaList(busqueda)
            val layoutManager = LinearLayoutManager(this)
            adaptador = MedicinaAdaptador(aplicaciones,1)
            adaptador.notifyDataSetChanged()
            recyclerview_lista_apps.layoutManager = layoutManager
            recyclerview_lista_apps.itemAnimator = DefaultItemAnimator()
            recyclerview_lista_apps.adapter = adaptador
            recyclerview_lista_apps.invalidate()
            registerForContextMenu(recyclerview_lista_apps)

        }

        boton_mostrar_todos.setOnClickListener {view: View ->
            aplicaciones = DataBaseMedicina.getList()
            val layoutManager = LinearLayoutManager(this)
            adaptador = MedicinaAdaptador(aplicaciones,1)
            adaptador.notifyDataSetChanged()
            recyclerview_lista_apps.layoutManager = layoutManager
            recyclerview_lista_apps.itemAnimator = DefaultItemAnimator()
            recyclerview_lista_apps.adapter = adaptador
            recyclerview_lista_apps.invalidate()
            registerForContextMenu(recyclerview_lista_apps)
        }

        boton_carrito.setOnClickListener{view: View ->
            irAActividadCarrito()
        }

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
    fun irAActividadCarrito(){
        val intent = Intent(this,CarritoComprasActivity::class.java)
        intent.putIntegerArrayListExtra("ids",arregloIds)
        arregloIds = ArrayList()
        startActivity(intent)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var posicion = adaptador.getPosition()
        var aplicacion = aplicaciones[posicion]

        when(item.itemId){
            R.id.item_menu_editar-> {

                when (aplicacion.estado){
                    1 ->{
                        val toast = Toast.makeText(this,"Seleccioado ${aplicacion.nombre}", Toast.LENGTH_SHORT)
                        toast.show()
                        arregloIds.add(aplicacion.pacienteID)
                        DataBaseMedicina.putAplicacionEstado(aplicacion,2)
                    }
                    2 ->{
                        val toast = Toast.makeText(this,"Item ya Seleccionado", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                    3 ->{
                        val toast = Toast.makeText(this,"Item no disponible", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                    else ->{
                        Log.i("Error","Error")}
                }

                /*val layoutManager = LinearLayoutManager(this)
                adaptador = AplicacionAdaptador2(aplicaciones)
                adaptador.notifyDataSetChanged()
                recyclerview_lista_apps.layoutManager = layoutManager
                recyclerview_lista_apps.itemAnimator = DefaultItemAnimator()
                recyclerview_lista_apps.adapter = adaptador
                recyclerview_lista_apps.invalidate()
                registerForContextMenu(recyclerview_lista_apps)*/
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }
}

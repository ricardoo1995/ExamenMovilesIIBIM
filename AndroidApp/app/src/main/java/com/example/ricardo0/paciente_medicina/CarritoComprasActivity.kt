package com.example.ricardo0.paciente_medicina

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.ricardo0.paciente_medicina.BaseDeDatos.DataBaseMedicina
import com.example.ricardo0.paciente_medicina.Cliente.FindActivity
import com.example.ricardo0.paciente_medicina.Vendedor.Medicina
import com.example.ricardo0.paciente_medicina.Vendedor.MedicinaAdaptador
import kotlinx.android.synthetic.main.activity_carrito_compras.*
import kotlinx.android.synthetic.main.activity_find.*

class CarritoComprasActivity : AppCompatActivity() {
    lateinit var adaptador: MedicinaAdaptador
    lateinit var arreglo: ArrayList<Int>
    lateinit var aplicaciones: ArrayList<Medicina>
    lateinit var aplicacionesAux: ArrayList<Medicina>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito_compras)

        aplicaciones = DataBaseMedicina.getList()
        aplicacionesAux= ArrayList()

        aplicaciones.forEach {

            if (it.estado == 2){
                aplicacionesAux.add(it)
            }
        }

        val layoutManager = LinearLayoutManager(this)
        adaptador = MedicinaAdaptador(aplicacionesAux, 1)
        adaptador.notifyDataSetChanged()
        recyclerview_carrito.layoutManager = layoutManager
        recyclerview_carrito.itemAnimator = DefaultItemAnimator()
        recyclerview_carrito.adapter = adaptador
        recyclerview_carrito.invalidate()
        registerForContextMenu(recyclerview_carrito)

        boton_guardar.setOnClickListener { view: View ->

            if (aplicacionesAux.size === 0) {
                val toast = Toast.makeText(this, "No existen Items seleccionados", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                irAActividadOrdenes()
            }

            finish()
        }

        boton_cancelar.setOnClickListener { view: View ->
            aplicaciones.forEach {

                if (it.estado === 2){
                    DataBaseMedicina.putAplicacionEstado(it, 1)
                }
            }

            irAActividadBuscar()
        }

    }

    fun irAActividadOrdenes() {
        val intent = Intent(this, OrdenesClientesActivity::class.java)
        intent.putParcelableArrayListExtra("medicamentos", aplicacionesAux)
        startActivity(intent)
    }

    fun irAActividadBuscar() {
        val intent = Intent(this, FindActivity::class.java)
        startActivity(intent)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        var posicion = adaptador.getPosition()
        var aplicacion = aplicacionesAux[posicion]

        when (item.itemId) {
            R.id.item_menu_editar -> {
                DataBaseMedicina.putAplicacionEstado(aplicacion, 1)
                aplicacionesAux.removeAt(posicion)
                val layoutManager = LinearLayoutManager(this)
                adaptador = MedicinaAdaptador(aplicacionesAux,2)
                adaptador.notifyDataSetChanged()
                recyclerview_carrito.layoutManager = layoutManager
                recyclerview_carrito.itemAnimator = DefaultItemAnimator()
                recyclerview_carrito.adapter = adaptador
                recyclerview_carrito.invalidate()
                registerForContextMenu(recyclerview_carrito)
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }
}


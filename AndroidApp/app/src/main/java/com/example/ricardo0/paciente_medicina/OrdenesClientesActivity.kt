package com.example.ricardo0.paciente_medicina

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ricardo0.paciente_medicina.BaseDeDatos.BaseDeDatosDetalle
import com.example.ricardo0.paciente_medicina.BaseDeDatos.BaseDeDatosOrdenes
import com.example.ricardo0.paciente_medicina.BaseDeDatos.DataBaseMedicina
import com.example.ricardo0.paciente_medicina.Parceleable.DetalleOrden
import com.example.ricardo0.paciente_medicina.Parceleable.Ordenes
import com.example.ricardo0.paciente_medicina.Vendedor.Medicina
import kotlinx.android.synthetic.main.activity_ordenes_clientes.*
import java.nio.charset.Charset
import java.util.*

class OrdenesClientesActivity : AppCompatActivity() {
    lateinit var aplicaciones: ArrayList<Medicina>
    lateinit var aplicacionesAux: ArrayList<Medicina>
    var total: Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenes_clientes)
        text_fecha.text = Calendar.getInstance().time.toString()
        aplicacionesAux = ArrayList()
        //aplicaciones = intent.getParcelableArrayListExtra("aplicaciones")
        aplicaciones = DataBaseMedicina.getList()
        aplicaciones.forEach {
            if (it.estado == 2) {
                total += it.precio.toInt()
                aplicacionesAux.add(it)
            }
        }

        boton_guardar.setOnClickListener { view: View ->
            crearOrden()
        }
    }
        fun crearOrden(){

            val usuario = "Luis"

            val orden = Ordenes(0,Calendar.getInstance().time.toString(),this.total,1, 78, 0, 0, Calendar.getInstance().time.toString(),1, 0,0 )
            val identificador = BaseDeDatosOrdenes.postOrden(orden)

            BaseDeDatosDetalle.postDetalle(DetalleOrden(0,this.total,identificador,0 ,0))
            val idDetalle = BaseDeDatosDetalle.getDetalle(identificador)

            /*aplicacionesAux.forEach {
                BaseDeDatosApp.putAplicacionDetalle(it,idDetalle[0].id)
            }*/

        }

        fun leerUsuario():String{

            val FILENAME ="usuario"
            var usuario = ""
            this.openFileInput(FILENAME).use {
                val usuarioAux = it.readBytes()
                usuario = usuarioAux.toString(Charset.defaultCharset())
            }

            val FILENAMEUNO ="contraseña"
            var contraseña = ""
            this.openFileInput(FILENAMEUNO).use {
                val contraseñaAux = it.readBytes()
                contraseña = contraseñaAux.toString(Charset.defaultCharset())
            }

            val toast = Toast.makeText(this,"suaurio: $usuario, contraseña: $contraseña", Toast.LENGTH_SHORT)
            toast.show()

            return usuario

        }

    }

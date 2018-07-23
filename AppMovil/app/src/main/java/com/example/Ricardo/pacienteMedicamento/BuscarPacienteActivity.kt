package com.example.Ricardo.pacienteMedicamento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buscar_paciente.*

class BuscarPacienteActivity : AppCompatActivity() {

    lateinit var adaptador: PacienteClienteAdapter
    lateinit var pacientes: ArrayList<Paciente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_paciente)


        btnBuscarPacienteServer.setOnClickListener { v: View? ->
            consultarDatos()
        }


    }

    fun consultarDatos(){
        if (txtBuscarPaciente.equals("")){
            Toast.makeText(this,"Ingrese el nombre par abuscar",Toast.LENGTH_SHORT).show()
        }else{
            var datoBusqueda:String = txtBuscarPaciente.text.toString()
            

            pacientes = BaseDatosPaciente.buscarPaciente(datoBusqueda)

            //Toast.makeText(this,datoBusqueda,Toast.LENGTH_SHORT).show()

            val layoutManager = LinearLayoutManager(this)
            adaptador = PacienteClienteAdapter(pacientes)
            recyclerView_Resultados_Paciente.layoutManager = layoutManager
            recyclerView_Resultados_Paciente.itemAnimator = DefaultItemAnimator()
            recyclerView_Resultados_Paciente.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(recyclerView_Resultados_Paciente)

        }
    }
}

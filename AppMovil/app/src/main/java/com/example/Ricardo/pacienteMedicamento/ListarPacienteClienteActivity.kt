package com.example.Ricardo.pacienteMedicamento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_listar_pacientes_cliente.*

class ListarPacienteClienteActivity : AppCompatActivity() {

    lateinit var adaptador: PacienteClienteAdapter
    lateinit var pacientes: ArrayList<Paciente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_pacientes_cliente)

        pacientes = BaseDatosPaciente.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = PacienteClienteAdapter(pacientes)
        recyclerView_listarPaciente_Clientes.layoutManager = layoutManager
        recyclerView_listarPaciente_Clientes.itemAnimator = DefaultItemAnimator()
        recyclerView_listarPaciente_Clientes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_listarPaciente_Clientes)
    }
}

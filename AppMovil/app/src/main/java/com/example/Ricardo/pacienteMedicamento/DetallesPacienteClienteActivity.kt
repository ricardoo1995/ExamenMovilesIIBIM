package com.example.Ricardo.pacienteMedicamento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_detalles_paciente.*

class DetallesPacienteClienteActivity : AppCompatActivity() {

    var paciente: Paciente? = null
    lateinit var medicamento: ArrayList<Medicamento>
    lateinit var adaptador: MedicamentoClienteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_paciente_cliente)

        paciente = intent.getParcelableExtra("detallesPacienteCliente")

        txtShowPacienteId.text = paciente?.id.toString()
        txtShowNombrePaciente.text = paciente?.nombres
        txtShowApellidoPaciente.text = paciente?.apellidos
        txtShowFechaNacPaciente.text = paciente?.fechaNacimiento
        txtShowNumHijosPaciente.text = paciente?.numHijos.toString()
        txtShowTieneSeguroPaciente.text = if(paciente?.tieneSeguro == 1) "Si" else "No"



        medicamento = BaseDatosMedicamento.getMedicamentoList(paciente?.id!!)
        Log.d("resultado",medicamento.toString())

        val layoutManager = LinearLayoutManager(this)
        adaptador = MedicamentoClienteAdapter(medicamento)
        recycler_view_medicamento.layoutManager = layoutManager
        recycler_view_medicamento.itemAnimator = DefaultItemAnimator()
        recycler_view_medicamento.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_medicamento)
    }
}

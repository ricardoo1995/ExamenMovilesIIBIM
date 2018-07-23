package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_pacientes.*

class PacienteActivity : AppCompatActivity() {

    var paciente: Paciente? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewEntreador.text = "Editar Paciente"
            paciente = intent.getParcelableExtra("paciente")
            fillFields()
            tipo = true
        }

        btnGuardarPaciente.setOnClickListener { v: View? ->
            crearPaciente()
        }
    }

    fun fillFields() {
        txtNombrePaciente.setText(paciente?.nombres)
        txtApellidoPaciente.setText(paciente?.apellidos)
        txtFechaNacimientoPaciente.setText(paciente?.fechaNacimiento)
        txtNumHijosPaciente.setText(paciente?.numHijos.toString())
        if (paciente?.tieneSeguro == 1) {
            switchTieneSeguroPaciente.toggle()
        }
    }

    fun crearPaciente(){
        var nombres = txtNombrePaciente.text.toString()
        var apellidos = txtApellidoPaciente.text.toString()
        var fecha = txtFechaNacimientoPaciente.text.toString()
        var numHijos = txtNumHijosPaciente.text.toString().toInt()
        var tieneSeguro = if (switchTieneSeguroPaciente.isChecked) 1 else 0


        if (!tipo){

            var paciente = Paciente(0, nombres, apellidos, fecha, numHijos, tieneSeguro,0,0)
            BaseDatosPaciente.insertarPaciente(paciente)

        }else{
            var paciente = Paciente(paciente?.id!!, nombres, apellidos, fecha, numHijos, tieneSeguro,0,0)
            BaseDatosPaciente.actualizarPaciente(paciente)
        }
        iraActividadPaciente()

    }

    fun iraActividadPaciente(){
        val intent = Intent(this, RegistrarUsuarios::class.java)
        intent.putExtra("valorRol","VENDEDOR")
        startActivity(intent)
    }
}

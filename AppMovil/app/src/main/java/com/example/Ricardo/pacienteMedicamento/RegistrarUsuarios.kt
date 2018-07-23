package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_registrar_usuarios.*


class RegistrarUsuarios : AppCompatActivity() {

    lateinit var rol:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuarios)


        rol = intent.getStringExtra("valorRol")


        if (rol.equals("VENDEDOR",true)){
            btnBuscarPacientes.visibility = View.INVISIBLE

        }else if (rol.equals("DELIVERY",true)){
            btnBuscarPacientes.visibility = View.INVISIBLE
            btnCrearPaciente.visibility = View.INVISIBLE
            btnListarPaciente.visibility = View.INVISIBLE
            irActividadDelivery()

        }else{
            btnCrearPaciente.visibility = View.INVISIBLE
            btnListarPaciente.visibility = View.INVISIBLE
        }


      btnCrearPaciente.setOnClickListener { v: View? ->
          irPacienteActivity()
      }

        btnListarPaciente.setOnClickListener { v: View? ->
            irListarPacienteActivity()
        }

        btnBuscarPacientes.setOnClickListener { v: View? ->
            irBuscarPacienteActivity()
        }

        btn_salirsistema.setOnClickListener { v: View? ->
            irMainActivity()
        }


    }

    fun irPacienteActivity(){
        val intent = Intent(this, PacienteActivity::class.java)
        intent.putExtra("tipo", "Create")
        startActivity(intent)
    }

    fun irListarPacienteActivity(){
        val intent = Intent(this, ListarPacientesActivity::class.java)
        startActivity(intent)
    }

    fun irActividadDelivery(){
        val intent = Intent(this,DeliveryActivity::class.java)
        startActivity(intent)

    }

    fun irBuscarPacienteActivity(){
        val intent = Intent(this,BuscarPacienteActivity::class.java)
        startActivity(intent)

    }

    fun irMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}

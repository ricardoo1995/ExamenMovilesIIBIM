package com.example.ricardo0.paciente_medicina.Vendedor

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.ricardo0.paciente_medicina.Cliente.MedicinaActivity
import com.example.ricardo0.paciente_medicina.DeliveryActivity
import com.example.ricardo0.paciente_medicina.Main2Activity
import com.example.ricardo0.paciente_medicina.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    var tipoUsuario = arrayOf("Seleccionar","Vendedor", "Cliente", "Delivery")
    var spinner:Spinner? = null
    var textView_msg:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = this.spinner1
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipoUsuario)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)


    }
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
       Log.d("Erroe" , "Selected : "+tipoUsuario[position])
        Log.d("Erroe" , "Selected : "+editTextNombreUsuario.text.toString())
        if(tipoUsuario[position] == "Vendedor" && editTextNombreUsuario.text.toString() == "Ricardo"  && editTextContraseña.text.toString() == "12345") {
            irActividadPaciente()
        }
        else
        {
            title_activity_login.setOnClickListener{v: View? ->  toast("Datos Incorrectos")}
        }

            if(tipoUsuario[position] == "Cliente" && editTextNombreUsuario.text.toString() == "Luis"  && editTextContraseña.text.toString() == "root"){
                irActividadMedicina()
        }else
            {
                title_activity_login.setOnClickListener{v: View? ->  toast("Datos Incorrectos")}
            }
            if(tipoUsuario[position] == "Delivery" && editTextNombreUsuario.text.toString() == "Pepe"  && editTextContraseña.text.toString() == "Pepe1234") {
                irActividadDelivery()
            }
            else
            {
                title_activity_login.setOnClickListener{v: View? ->  toast("Datos Incorrectos")}
            }

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
    fun irActividadPaciente(){
        var intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)
        toast("Bienvenido al Sistema")
    }
    fun irActividadMedicina(){
        var intent = Intent(this, MedicinaActivity::class.java)
        startActivity(intent)
        toast("Bienvenido al Sistema")
    }
    fun irActividadDelivery(){
        var intent = Intent(this, DeliveryActivity::class.java)
        startActivity(intent)
        toast("Bienvenido al Sistema")
    }
    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }
}

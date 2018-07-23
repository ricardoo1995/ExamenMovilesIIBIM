package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_detalles_paciente.*

class DetallesPacienteActivity : AppCompatActivity() {

    var paciente: Paciente? = null
    lateinit var medicamento: ArrayList<Medicamento>
    lateinit var adaptador: MedicamentoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_paciente)



        paciente = intent.getParcelableExtra("detallesPaciente")

        txtShowPacienteId.text = paciente?.id.toString()
        txtShowNombrePaciente.text = paciente?.nombres
        txtShowApellidoPaciente.text = paciente?.apellidos
        txtShowFechaNacPaciente.text = paciente?.fechaNacimiento
        txtShowNumHijosPaciente.text = paciente?.numHijos.toString()
        txtShowTieneSeguroPaciente.text = if(paciente?.tieneSeguro == 1) "Si" else "No"



        medicamento = BaseDatosMedicamento.getMedicamentoList(paciente?.id!!)
       Log.d("resultado",medicamento.toString())

       val layoutManager = LinearLayoutManager(this)
        adaptador = MedicamentoAdapter(medicamento)
        recycler_view_medicamento.layoutManager = layoutManager
        recycler_view_medicamento.itemAnimator = DefaultItemAnimator()
        recycler_view_medicamento.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_medicamento)

        btnNuevaMedicamento.setOnClickListener { v: View? ->
            irActividdadCrearMedicamento()
        }


    }

    fun irActividdadCrearMedicamento(){
        val intent = Intent(this, MedicamentoActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("pacienteId", paciente?.id!!)
        startActivity(intent)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var medicamentos = medicamento[position]

        when (item.itemId) {

            R.id.item_menu_editar -> {
                val intent = Intent(this, MedicamentoActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("medicamento", medicamentos)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            BaseDatosMedicamento.eliminarMedicamento(medicamentos.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

}

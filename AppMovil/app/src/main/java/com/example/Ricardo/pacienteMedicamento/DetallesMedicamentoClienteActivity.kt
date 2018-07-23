package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_medicamento.*
import kotlinx.android.synthetic.main.activity_detalles_medicamento_cliente.*

class DetallesMedicamentoClienteActivity : AppCompatActivity() {

    var medicamento: Medicamento? = null
    lateinit var myBitmapAgain: Bitmap
    lateinit var idMedicamento:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_medicamento_cliente)

        medicamento = intent.getParcelableExtra("detallesMedicamentoCliente")

        txtShowgramosAIngerirMedicamento.text = medicamento?.gramosAIngerir.toString()
        txtShowNombreMedicamento.text = medicamento?.nombre
        txtShowComposicion.text = medicamento?.composicion
        txtShowUsadoPara.text = medicamento?.usadoPara
        txtShowFechaCaducidad.text = medicamento?.fechaCaducidad
        txtShowNumeroPastillas.text = medicamento?.numeroPastillas.toString()


        myBitmapAgain = decodeBase64(medicamento?.imagenMedicamento!!)
        //showImageViewMedicamento.setImageBitmap(myBitmapAgain)

        btnAdquirirMedicamento.setOnClickListener { v ->
            irActividadDatosComprador()
        }


    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun irActividadDatosComprador(){
        val intent = Intent(this, CompradorActivity::class.java)
        idMedicamento = medicamento?.id.toString()
        intent.putExtra("idMedicamento",idMedicamento)
        startActivity(intent)
    }


}

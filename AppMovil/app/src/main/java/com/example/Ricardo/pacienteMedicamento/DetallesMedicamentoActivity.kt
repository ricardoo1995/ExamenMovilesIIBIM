package com.example.Ricardo.pacienteMedicamento

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_medicamento.*
import kotlinx.android.synthetic.main.activity_detalles_medicamento.*


class DetallesMedicamentoActivity : AppCompatActivity() {

    var medicamento: Medicamento? = null
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_medicamento)

        medicamento = intent.getParcelableExtra("detallesMedicamento")


        txtShowgramosAIngerirMedicamento.text = medicamento?.gramosAIngerir.toString()
        txtShowNombreMedicamento.text = medicamento?.nombre
        txtShowComposicion.text = medicamento?.composicion
        txtShowUsadoPara.text = medicamento?.usadoPara
        txtShowFechaCaducidad.text = medicamento?.fechaCaducidad
        txtShowNumeroPastillas.text = medicamento?.numeroPastillas.toString()

        myBitmapAgain = decodeBase64(medicamento?.imagenMedicamento.toString()!!)
        showImageViewMedicamento.setImageBitmap(myBitmapAgain)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}

package com.example.Ricardo.pacienteMedicamento

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.View
import kotlinx.android.synthetic.main.activity_medicamento.*
import kotlinx.android.synthetic.main.activity_medicamento.*
import java.io.ByteArrayOutputStream


class MedicamentoActivity : AppCompatActivity() {

    var medicamento: Medicamento? = null
    var pacienteId: Int = 0
    private lateinit var imageBitmap: Bitmap
    var tipo = false
    lateinit var myBase64Image:String
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicamento)

        pacienteId = intent.getIntExtra("pacienteId", 0)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewMedicamento.text = "Editar Medicamento"
            medicamento = intent.getParcelableExtra("medicamento")
            fillFields()
            tipo = true
        }

        btnGuardarMedicamento.setOnClickListener { v: View? ->
            crearMedicamento()
        }

        btnTomarFoto.setOnClickListener{v: View? ->
            tomarFoto()

        }
    }

    fun fillFields() {
        txtGramosAIngerirMedicamento.setText(medicamento?.gramosAIngerir.toString())
        txtNombreMedicamento.setText(medicamento?.nombre)
        txtComposicionMedicamento.setText(medicamento?.composicion)
        txtusadoParaMedicamento.setText(medicamento?.usadoPara)
        txtFechaCaducidadMedicamento.setText(medicamento?.fechaCaducidad)
        txtNumerosPastillasMedicamento.setText(medicamento?.numeroPastillas.toString())
    }

    fun crearMedicamento(){
        var nombre = txtGramosAIngerirMedicamento.text.toString().toInt()
        var codigo = txtNombreMedicamento.text.toString()
        var descripcion =  txtComposicionMedicamento.text.toString()
        var activo =  txtusadoParaMedicamento.text.toString()
        var fechaCreacion =  txtFechaCaducidadMedicamento.text.toString()
        var numeroHorasPorSemana = txtNumerosPastillasMedicamento.text.toString().toInt()
        var imagenMedicamento = myBase64Image

        if (!tipo){
            var medicamento = Medicamento(0,nombre,codigo,descripcion,activo,fechaCreacion,numeroHorasPorSemana,imagenMedicamento,pacienteId,0,0)
            BaseDatosMedicamento.insertarMedicamento(medicamento)

        }else{
            var medicamento2 = Medicamento(medicamento?.id!!,nombre,codigo,descripcion,activo,fechaCreacion,numeroHorasPorSemana,imagenMedicamento,pacienteId,0,0)
            BaseDatosMedicamento.actualizarMedicamento(medicamento2)
        }

        finish()

    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun tomarFoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
       super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap

            myBase64Image = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100)
            myBitmapAgain = decodeBase64(myBase64Image)

            imageViewMedicamento.setImageBitmap(myBitmapAgain)


        }

    }

    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat, quality: Int): String {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return android.util.Base64.encodeToString(byteArrayOS.toByteArray(), android.util.Base64.DEFAULT)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }



}


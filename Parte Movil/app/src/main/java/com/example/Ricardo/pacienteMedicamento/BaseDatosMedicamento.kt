package com.example.Ricardo.pacienteMedicamento

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class BaseDatosMedicamento{
    companion object {

        fun insertarMedicamento(medicamento:Medicamento){
            "http://192.168.100.134:1337/Medicamento".httpPost(listOf("gramosAIngerir" to medicamento.gramosAIngerir, "nombre" to medicamento.nombre, "composicion" to medicamento.composicion, "usadoPara" to medicamento.usadoPara, "fechaCaducidad" to medicamento.fechaCaducidad,"numeroPastillas" to medicamento.numeroPastillas,"imagenMedicamento" to medicamento.imagenMedicamento,"pacienteId" to medicamento.pacienteId ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarMedicamento(id: Int) {
            "http://192.168.100.134:1337/Medicamento/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarMedicamento(medicamento: Medicamento) {
            "http://192.168.100.134:1337/Medicamento/${medicamento.id}".httpPut(listOf("gramosAIngerir" to medicamento.gramosAIngerir, "nombre" to medicamento.nombre, "composicion" to medicamento.composicion, "usadoPara" to medicamento.usadoPara, "fechaCaducidad" to medicamento.fechaCaducidad, "numeroPastillas" to medicamento.numeroPastillas))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getMedicamentoList(pacienteId: Int): ArrayList<Medicamento> {
            val medicamento: ArrayList<Medicamento> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Medicamento?pacienteId=$pacienteId".httpGet().responseString()
            val jsonStringMedicamento = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringMedicamento)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val gramosAIngerir = it["gramosAIngerir"] as Int
                val nombre = it["nombre"] as String
                val composicion = it["composicion"] as String
                val usadoPara = it["usadoPara"] as String
                val fechaCaducidad = it["fechaCaducidad"] as String
                val numeroPastillas = it["numeroPastillas"] as Int
                val imagenMedicamento = it["imagenMedicamento"] as String
                val medicamentos = Medicamento(id,gramosAIngerir,nombre,composicion,usadoPara,fechaCaducidad,numeroPastillas,imagenMedicamento,pacienteId,0,0)
                medicamento.add(medicamentos)
            }
            return medicamento
        }




    }
}
package com.example.ricardo0.paciente_medicina.BaseDeDatos

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.ricardo0.paciente_medicina.Vendedor.Medicina
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class DataBaseMedicina {

    companion object {
        fun insertarMedicina(medicina: Medicina) {
            "http://192.168.100.134:1337/Medicina".httpPost(listOf("gramosAConsumir" to medicina.gramosAConsumir, "nombre" to medicina.nombre, "numeroPastillas" to medicina.numeroPastillas, "composicon" to medicina.composicion, "fechaCaducidad" to medicina.fechaCaducidad, "usadaPara" to medicina.usadaPara, "pacienteId" to medicina.pacienteID))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updateMedicina(medicina: Medicina) {
            "http://192.168.100.134:1337/Medicina/${medicina.id}".httpPut(listOf("gramosAConsumir" to medicina.gramosAConsumir, "nombre" to medicina.nombre, "numeroPastillas" to medicina.numeroPastillas, "composicon" to medicina.composicion, "fechaCaducidad" to medicina.fechaCaducidad, "usadaPara" to medicina.usadaPara))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun deleteMedicina(id: Int) {
            "http://192.168.100.134:1337/Medicina/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getMedicinaList(idPaciente: Int): ArrayList<Medicina> {
            val medicinas: ArrayList<Medicina> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Medicina?pacienteId=$idPaciente".httpGet().responseString()
            val jsonStringLibro = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringLibro)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val gramosAConsumir = it["gramosAConsumir"] as String
                val nombre = it["nombre"] as String
                val numeroPastillas = it["numeroPastillas"] as Int
                val composicion = it["composicion"] as String
                val fechaCaducidad = it["fechaCaducidad"] as String
                val usadaPara = it["usadaPara"] as String
                val precio =it["precio"] as String
                val estado= it["estado"] as Int
                val foto = it["foto"] as String
                val medicina = Medicina(id, gramosAConsumir, nombre, numeroPastillas, composicion, fechaCaducidad, usadaPara,precio,estado,foto, idPaciente, 0, 0)
                medicinas.add(medicina)
            }
            return medicinas
        }


        fun getList(): ArrayList<Medicina> {
            val medicinas: ArrayList<Medicina> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Medicina".httpGet().responseString()
            val jsonStringPaciente = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPaciente)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val gramosAConsumir = it["gramosAConsumir"] as String
                val nombre = it["nombre"] as String
                val numeroPastillas = it["numeroPastillas"] as Int
                val composicion = it["composicion"] as String
                val fechaCaducidad = it["fechaCaducidad"] as String
                val usadaPara = it["usadaPara"] as String
                val precio =it["precio"] as String
                val estado= it["estado"] as Int
                val foto = it["foto"] as String
                val idPaciente = 1
                val medicina = Medicina(id, gramosAConsumir, nombre, numeroPastillas, composicion, fechaCaducidad, usadaPara,precio,estado,foto, idPaciente, 0, 0)
                medicinas.add(medicina)
            }
            return medicinas
        }
        fun getAplicacionesBusqueda(nombre:String): ArrayList<Medicina> {
            val medicinas: ArrayList<Medicina> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Medicina/$nombre".httpGet().responseString()
            val jsonStringLibro = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringLibro)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val gramosAConsumir = it["gramosAConsumir"] as String
                val nombre = it["nombre"] as String
                val numeroPastillas = it["numeroPastillas"] as Int
                val composicion = it["composicion"] as String
                val fechaCaducidad = it["fechaCaducidad"] as String
                val usadaPara = it["usadaPara"] as String
                val precio =it["precio"] as String
                val estado= it["estado"] as Int
                val foto = it["foto"] as String
                val idPaciente = 1
                val medicina = Medicina(id, gramosAConsumir, nombre, numeroPastillas, composicion, fechaCaducidad, usadaPara,precio,estado,foto, idPaciente, 0, 0)
                medicinas.add(medicina)
            }
            return medicinas
        }
        fun putAplicacionEstado(aplicacion: Medicina, estado: Int) {
            "\"http://192.168.100.134:1337/Medicina?pacienteId=0\"".httpPut(listOf("estado" to estado))
                    .responseString { request, responde, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }
        fun getAplicacioneUnica(id: Int): Medicina {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Medicina/$id".httpGet().responseString()
            val jsonStringLibro = result.get()
            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringLibro)
            val app = parser.parse(stringBuilder) as JsonObject
            val id = app["id"] as Int
            val gramosAConsumir = app["gramosAConsumir"] as String
            val nombre = app["nombre"] as String
            val numeroPastillas = app["numeroPastillas"] as Int
            val composicion = app["composicion"] as String
            val fechaCaducidad = app["fechaCaducidad"] as String
            val usadaPara = app["usadaPara"] as String
            val precio =app["precio"] as String
            val estado= app["estado"] as Int
            val foto = app["foto"] as String
            val idPaciente = 1
            val aplicacion = Medicina(id, gramosAConsumir, nombre, numeroPastillas, composicion, fechaCaducidad, usadaPara,precio,estado,foto, idPaciente, 0, 0)

            return aplicacion
        }
    }
}



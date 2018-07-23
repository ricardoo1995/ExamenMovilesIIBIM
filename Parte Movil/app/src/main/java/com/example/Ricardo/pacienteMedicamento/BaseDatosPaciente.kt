package com.example.Ricardo.pacienteMedicamento

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.*

class BaseDatosPaciente{

    companion object {

        fun insertarPaciente(paciente:Paciente){
            "http://192.168.100.134:1337/Paciente".httpPost(listOf("nombres" to paciente.nombres, "apellidos" to paciente.apellidos, "fechaNacimiento" to paciente.fechaNacimiento, "numHijos" to paciente.numHijos, "tieneSeguro" to paciente.tieneSeguro))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarPaciente(id: Int) {
            "http://192.168.100.134:1337/Paciente/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarPaciente(paciente: Paciente) {
            "http://192.168.100.134:1337/Paciente/${paciente.id}".httpPut(listOf("nombres" to paciente.nombres, "apellidos" to paciente.apellidos, "fechaNacimiento" to paciente.fechaNacimiento, "numHijos" to paciente.numHijos, "tieneSeguro" to paciente.tieneSeguro))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Paciente> {
            val pacientes: ArrayList<Paciente> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Paciente".httpGet().responseString()
            val jsonStringPaciente = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPaciente)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombres = it["nombres"] as String
                val apellidos = it["apellidos"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numHijos = it["numHijos"] as Int
                val tieneSeguro = it["tieneSeguro"] as Int
                val paciente = Paciente(id, nombres, apellidos, fechaNacimiento, numHijos, tieneSeguro, 0, 0)
                pacientes.add(paciente)
            }
            return pacientes
        }

        fun buscarPaciente(nombre:String): ArrayList<Paciente> {
            val pacientes: ArrayList<Paciente> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Paciente?nombres=${nombre}".httpGet().responseString()
            val jsonStringPaciente = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPaciente)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombres = it["nombres"] as String
                val apellidos = it["apellidos"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numHijos = it["numHijos"] as Int
                val tieneSeguro = it["tieneSeguro"] as Int
                val paciente = Paciente(id, nombres, apellidos, fechaNacimiento, numHijos, tieneSeguro, 0, 0)
                pacientes.add(paciente)
            }
            return pacientes
        }

    }
}
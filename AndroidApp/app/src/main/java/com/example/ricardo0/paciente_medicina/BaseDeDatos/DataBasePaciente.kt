package com.example.ricardo0.paciente_medicina.BaseDeDatos

import android.util.Log
import android.os.StrictMode
import com.beust.klaxon.*
import com.example.ricardo0.paciente_medicina.Parceleable.Paciente
import com.github.kittinunf.fuel.*
class DataBasePaciente {

    companion object {

        fun insertarPaciente(paciente: Paciente) {
            "http://192.168.100.134:1337/Paciente".httpPost(listOf("nombre" to paciente.nombre, "apellido" to paciente.apellido, "fechaNacimiento" to paciente.fechaNacimiento, "numeroHijos" to paciente.numeroHijos, "afiliado" to paciente.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updatePaciente(paciente: Paciente) {
            "http://192.168.100.134:1337/Paciente/${paciente.id}".httpPut(listOf("nombre" to paciente.nombre, "apellido" to paciente.apellido, "fechaNacimiento" to paciente.fechaNacimiento, "numeroHijos" to paciente.numeroHijos, "afiliado" to paciente.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun deleteAutor(id: Int) {
            "http://192.168.100.134:1337/Paciente/$id".httpDelete()
                    .responseString { request, response, result ->
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
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroHijos = it["numeroHijos"] as Int
                val afiliado = it["afiliado"] as Int
                val paciente = Paciente(id, nombre, apellido, fechaNacimiento, numeroHijos, afiliado, 0, 0)
                pacientes.add(paciente)
            }
            return pacientes
        }

    }

}

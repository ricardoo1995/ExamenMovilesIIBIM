package com.example.ricardo0.paciente_medicina.BaseDeDatos

import android.os.StrictMode
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.ricardo0.paciente_medicina.Parceleable.Usuario
import com.github.kittinunf.fuel.httpGet
class BaseDeDatosUsuario {
companion object {

    val ip = "192.168.100.134:1337"

    fun getUsuario(usuario: String): ArrayList<Usuario> {
        val usuarios: ArrayList<Usuario> = ArrayList()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val (request, response, result) = "http://${this.ip}/Usuario?username=$usuario".httpGet().responseString()
        val jsonStringLibro = result.get()

        val parser = Parser()
        val stringBuilder = StringBuilder(jsonStringLibro)
        val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

        array.forEach {
            val id = it["id"] as Int
            val username = it["username"] as String
            val password = it["password"] as String
            val tipo = it["tipo"] as String
            val created = it["createdAt"] as Long
            val updated = it["updatedAt"] as Long

            val usuario = Usuario(id, username, password, tipo,created, updated)
            usuarios.add(usuario)
        }
        return usuarios
    }
}
}
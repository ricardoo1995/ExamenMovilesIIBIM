package com.example.ricardo0.paciente_medicina.BaseDeDatos

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.ricardo0.paciente_medicina.Parceleable.DetalleOrden
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
class BaseDeDatosDetalle {

companion object {

    val ip = "192.168.100.134:1337"
    fun postDetalle(detalle: DetalleOrden) {
        "http://${this.ip}/DetalleOrden".httpPost(listOf("orden" to detalle.ordenId, "precio" to detalle.precio))
                .responseString { request, response, result ->
                    Log.d("request", request.toString())
                }
    }

    fun putDetalle(detalle: DetalleOrden) {
        "http://${this.ip}/DetalleOrden/${detalle.id}".httpPut(listOf("precio" to detalle.precio))
                .responseString { request, response, result ->
                    Log.d("request", request.toString())
                }
    }

    fun deleteOrden(id: Int) {
        "http://${this.ip}/DetalleOrden/$id".httpDelete()
                .responseString { request, response, result ->
                    Log.d("request", request.toString())
                }
    }

    fun getDetalle(orden: Int): ArrayList<DetalleOrden>{
        val ordenes: ArrayList<DetalleOrden> = ArrayList()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val (request, response, result) = "http://${this.ip}/DetalleOrden?orden=$orden".httpGet().responseString()
        val jsonStringAutor = result.get()

        val parser = Parser()
        val stringBuilder = StringBuilder(jsonStringAutor)
        val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

        array.forEach {
            val id = it["id"] as Int
            val precio = it["precio"] as Int
            //val orden = it["orden"] as Int

            val sistema = DetalleOrden(id, precio, 0,0,0)
            ordenes.add(sistema)
        }
        return ordenes
    }

}

}
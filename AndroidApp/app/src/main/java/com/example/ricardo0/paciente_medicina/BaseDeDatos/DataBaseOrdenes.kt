package com.example.ricardo0.paciente_medicina.BaseDeDatos

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.ricardo0.paciente_medicina.Parceleable.Ordenes
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class BaseDeDatosOrdenes {

    companion object {

        val ip = "192.168.100.134:1337"
        var identificador = 0
        fun postOrden(orden: Ordenes): Int {
            identificador += 1
            var resultado: Ordenes
            "http://${this.ip}/Ordenes".httpPost(listOf("fecha" to orden.fecha, "total" to orden.total, "estado" to orden.estado, "usuario" to orden.usuarioId, "lugar" to orden.longitud,"lugar" to orden.latitud))
                    .responseString { request, response, result ->
                        Log.d("request", request.toString())
                    }
            return identificador
        }

        fun putOrden(orden: Ordenes) {
            "http://${this.ip}/Ordenes/${orden.id}".httpPut(listOf("costoDelivery" to orden.costoDelivery, "fechaEntrega" to orden.fechaEntrega, "estado" to orden.estado))
                    .responseString { request, response, result ->
                        Log.d("request", request.toString())
                    }
        }

        fun deleteOrden(id: Int) {
            "http://${this.ip}/Ordenes/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("request", request.toString())
                    }
        }

        fun getList(usuario: Int): ArrayList<Ordenes> {
            val ordenes: ArrayList<Ordenes> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://${this.ip}/Ordenes?usuario=$usuario".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val fecha = it["fecha"] as String
                val total = it["total"] as Int
                val estado = it["estado"] as Int
                val longitud = it["longitud"] as Int
                val latitud =it["latitud"] as Int
                val costoDelivery = it["costoDelivery"] as Int
                val fechaEntrega = it["fechaEntrega"] as String
                val idUsuario = it["idUsuario"] as Int
                val idDetalle = it["idDetalle"] as Long

                val sistema = Ordenes(id, fecha, total, estado, latitud ,longitud, costoDelivery, fechaEntrega, 1, 0, 0)
                ordenes.add(sistema)
            }
            return ordenes
        }

    }

}
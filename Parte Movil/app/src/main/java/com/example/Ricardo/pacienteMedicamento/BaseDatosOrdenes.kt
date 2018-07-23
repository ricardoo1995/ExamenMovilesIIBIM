package com.example.Ricardo.pacienteMedicamento

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class BaseDatosOrdenes{
    companion object {
        fun insertarOrden(ordenes: Ordenes){
            "http://192.168.100.134:1337/Orden".httpPost(listOf("cedulaIdentidad" to ordenes.cedulaComprador, "sector" to ordenes.sector, "idMedicamento" to ordenes.idMedicamento))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun insertarOrdenDetalles(detallesOrden: DetallesOrden){
            "http://192.168.100.134:1337/DetalleOrden".httpPost(listOf("fechaEnvio" to detallesOrden.fechaEnvio, "precio" to detallesOrden.precio, "idMedicamento" to detallesOrden.idMedicamento))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }



        fun getOrdenesList(): ArrayList<Ordenes> {
            val orden: ArrayList<Ordenes> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Orden".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val cedulaIdentidad = it["cedulaIdentidad"] as Int
                val sector = it["sector"] as String
                val idMedicamento = it["idMedicamento"] as Int
                //val latitud = it["latitud"] as Double
                // val longitud = it["longitud"] as Double
                val ordenes = Ordenes(0,cedulaIdentidad,sector,idMedicamento)
                orden.add(ordenes)
            }
            return orden
        }
    }



}
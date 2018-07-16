package com.example.ricardo0.paciente_medicina.Parceleable

import android.os.Parcel
import android.os.Parcelable

class Ordenes(var id: Int,
               var fecha: String,
               var total: Int,
               var estado: Int,
               var latitud: Int,
               var longitud: Int,
               var costoDelivery: Int,
               var fechaEntrega: String,
               var usuarioId: Int,
               var createdAt: Long,
               var updatedAt: Long): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(destino: Parcel?, p1: Int) {
        destino?.writeInt(id)
        destino?.writeString(fecha)
        destino?.writeInt(total)
        destino?.writeInt(estado)
        destino?.writeInt(latitud)
        destino?.writeInt(longitud)
        destino?.writeInt(costoDelivery)
        destino?.writeString(fechaEntrega)
        destino?.writeInt(usuarioId)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Ordenes> {
        override fun createFromParcel(parcel: Parcel): Ordenes {
            return Ordenes(parcel)
        }

        override fun newArray(size: Int): Array<Ordenes?> {
            return arrayOfNulls(size)
        }
    }

}
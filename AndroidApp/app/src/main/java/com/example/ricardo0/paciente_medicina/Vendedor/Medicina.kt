package com.example.ricardo0.paciente_medicina.Vendedor

import android.os.Parcel
import android.os.Parcelable

class Medicina(var id: Int,
            var gramosAConsumir: String,
            var nombre: String,
            var numeroPastillas: Int,
            var composicion: String,
            var fechaCaducidad: String,
            var usadaPara: String,
               var precio:String,
               var estado:Int,
               var foto:String,
            var pacienteID: Int,
            var createdAt: Long,
            var updatedAt: Long) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
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
        destino?.writeString(gramosAConsumir)
        destino?.writeString(nombre)
        destino?.writeInt(numeroPastillas)
        destino?.writeString(composicion)
        destino?.writeString(fechaCaducidad)
        destino?.writeString(usadaPara)
        destino?.writeString(precio)
        destino?.writeInt(estado)
        destino?.writeString(foto)
        destino?.writeInt(pacienteID)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Medicina> {
        override fun createFromParcel(parcel: Parcel): Medicina {
            return Medicina(parcel)
        }

        override fun newArray(size: Int): Array<Medicina?> {
            return arrayOfNulls(size)
        }
    }

}
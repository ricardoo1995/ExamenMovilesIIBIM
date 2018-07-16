package com.example.ricardo0.paciente_medicina.Parceleable

import android.os.Parcel
import android.os.Parcelable

class Paciente(var id: Int,
            var nombre: String,
            var apellido: String,
            var fechaNacimiento: String,
            var numeroHijos: Int,
            var ecuatoriano: Int,
            var createdAt: Long,
            var updatedAt: Long): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(destino: Parcel?, p1: Int) {
        destino?.writeInt(id)
        destino?.writeString(nombre)
        destino?.writeString(apellido)
        destino?.writeString(fechaNacimiento)
        destino?.writeInt(numeroHijos)
        destino?.writeInt(ecuatoriano)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Paciente> {
        override fun createFromParcel(parcel: Parcel): Paciente {
            return Paciente(parcel)
        }

        override fun newArray(size: Int): Array<Paciente?> {
            return arrayOfNulls(size)
        }
    }

}
package com.example.Ricardo.pacienteMedicamento

import android.os.Parcel
import android.os.Parcelable

class Paciente(var id: Int, var nombres: String, var apellidos: String, var fechaNacimiento: String, var numHijos: Int, var tieneSeguro: Int, var createdAt: Long,
                 var updatedAt: Long) : Parcelable {
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombres)
        parcel.writeString(apellidos)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(numHijos)
        parcel.writeInt(tieneSeguro)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
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
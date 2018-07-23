package com.example.Ricardo.pacienteMedicamento

import android.os.Parcel
import android.os.Parcelable

class Medicamento(var id: Int, var gramosAIngerir: Int, var nombre: String, var composicion: String, var usadoPara: String, var fechaCaducidad: String, var numeroPastillas: Int, var imagenMedicamento:String, var pacienteId:Int, var createdAt: Long,
              var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(gramosAIngerir)
        parcel.writeString(nombre)
        parcel.writeString(composicion)
        parcel.writeString(usadoPara)
        parcel.writeString(fechaCaducidad)
        parcel.writeInt(numeroPastillas)
        parcel.writeString(imagenMedicamento)
        parcel.writeInt(pacienteId)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicamento> {
        override fun createFromParcel(parcel: Parcel): Medicamento {
            return Medicamento(parcel)
        }

        override fun newArray(size: Int): Array<Medicamento?> {
            return arrayOfNulls(size)
        }
    }
}

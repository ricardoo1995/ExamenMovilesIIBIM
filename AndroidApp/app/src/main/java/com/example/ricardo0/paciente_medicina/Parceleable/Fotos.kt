package com.example.ricardo0.paciente_medicina.Parceleable

import android.os.Parcel
import android.os.Parcelable


class Fotos(var id: Int,
                   var url: String,
                   var medicinaId: Int,
                   var createdAt: Long,
                   var updatedAt: Long): Parcelable {

    constructor(parcel: Parcel) : this(
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
        destino?.writeString(url)
        destino?.writeInt(medicinaId)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Fotos> {
        override fun createFromParcel(parcel: Parcel): Fotos {
            return Fotos(parcel)
        }

        override fun newArray(size: Int): Array<Fotos?> {
            return arrayOfNulls(size)
        }
    }

}
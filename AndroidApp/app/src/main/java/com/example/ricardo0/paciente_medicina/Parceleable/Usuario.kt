package com.example.ricardo0.paciente_medicina.Parceleable

import android.os.Parcel
import android.os.Parcelable

class Usuario (var id: Int,
               var tipo: String,
               var username: String,
               var password: String,
               var createdAt: Long,
               var updatedAt: Long): Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(destino: Parcel?, p1: Int) {
        destino?.writeInt(id)
        destino?.writeString(tipo)
        destino?.writeString(username)
        destino?.writeString(password)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}
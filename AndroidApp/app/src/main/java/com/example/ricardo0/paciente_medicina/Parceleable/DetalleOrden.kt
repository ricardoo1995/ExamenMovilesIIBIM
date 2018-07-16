package com.example.ricardo0.paciente_medicina.Parceleable

import android.os.Parcel
import android.os.Parcelable


class DetalleOrden(var id: Int,
              var precio: Int,
                   var ordenId: Int,
              var createdAt: Long,
              var updatedAt: Long): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
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
        destino?.writeInt(precio)
        destino?.writeInt(ordenId)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<DetalleOrden> {
        override fun createFromParcel(parcel: Parcel): DetalleOrden {
            return DetalleOrden(parcel)
        }

        override fun newArray(size: Int): Array<DetalleOrden?> {
            return arrayOfNulls(size)
        }
    }

}
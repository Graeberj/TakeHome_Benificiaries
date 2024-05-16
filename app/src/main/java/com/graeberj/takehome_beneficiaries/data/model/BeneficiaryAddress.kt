package com.graeberj.takehome_beneficiaries.data.model

import android.os.Parcel
import android.os.Parcelable

data class BeneficiaryAddress(
    val firstLineMailing: String,
    val scndLineMailing: String?,
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstLineMailing)
        parcel.writeString(scndLineMailing)
        parcel.writeString(city)
        parcel.writeString(zipCode)
        parcel.writeString(stateCode)
        parcel.writeString(country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BeneficiaryAddress> {
        override fun createFromParcel(parcel: Parcel): BeneficiaryAddress {
            return BeneficiaryAddress(parcel)
        }

        override fun newArray(size: Int): Array<BeneficiaryAddress?> {
            return arrayOfNulls(size)
        }
    }
}
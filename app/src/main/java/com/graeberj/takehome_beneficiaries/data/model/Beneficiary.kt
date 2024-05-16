package com.graeberj.takehome_beneficiaries.data.model

import android.os.Parcel
import android.os.Parcelable

data class Beneficiary(
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val beneType: String,
    val designationCode: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val beneficiaryAddress: BeneficiaryAddress
) : Parcelable {

    // This constructor is used to create the object from a Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(BeneficiaryAddress::class.java.classLoader)!!
    )

    // This method is used to write the object to a Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(middleName)
        parcel.writeString(lastName)
        parcel.writeString(beneType)
        parcel.writeString(designationCode)
        parcel.writeString(socialSecurityNumber)
        parcel.writeString(dateOfBirth)
        parcel.writeString(phoneNumber)
        parcel.writeParcelable(beneficiaryAddress, flags)
    }

    // This method is used to describe the contents of the object
    override fun describeContents(): Int {
        return 0
    }

    // This companion object is used to create the object from a Parcel
    companion object CREATOR : Parcelable.Creator<Beneficiary> {
        override fun createFromParcel(parcel: Parcel): Beneficiary {
            return Beneficiary(parcel)
        }

        override fun newArray(size: Int): Array<Beneficiary?> {
            return arrayOfNulls(size)
        }
    }
}

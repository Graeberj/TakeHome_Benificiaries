package com.graeberj.takehome_beneficiaries.data.repository

import android.content.Context
import android.util.Log
import com.graeberj.takehome_beneficiaries.domain.mapper.toDomain
import com.graeberj.takehome_beneficiaries.domain.model.Beneficiary
import com.graeberj.takehome_beneficiaries.domain.repository.BeneficiaryRepository
import org.json.JSONArray
import java.io.IOException

class BeneficiaryRepositoryImpl(private val context: Context) : BeneficiaryRepository {

    // this method reads the JSON file from the assets folder and returns a list of Beneficiary objects
    override fun getBeneficiaries(): List<Beneficiary> {
        val jsonString = loadJsonFromAsset(context, "Beneficiaries.json")
        return if (jsonString != null) {
            parseJson(jsonString).map { it.toDomain() }
        } else {
            emptyList()
        }
    }

    // this method reads the JSON file from the assets folder and returns the content as a String
    private fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    // this method parses the JSON string and returns a list of Beneficiary objects
    private fun parseJson(jsonString: String): List<com.graeberj.takehome_beneficiaries.data.model.Beneficiary> {
        val beneficiaries = mutableListOf<com.graeberj.takehome_beneficiaries.data.model.Beneficiary>()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val addressObject = jsonObject.getJSONObject("beneficiaryAddress")
            val beneficiaryAddress = com.graeberj.takehome_beneficiaries.data.model.BeneficiaryAddress(
                addressObject.getString("firstLineMailing"),
                addressObject.optString("scndLineMailing"),
                addressObject.getString("city"),
                addressObject.getString("zipCode"),
                addressObject.getString("stateCode"),
                addressObject.getString("country")
            )
            val beneficiary = com.graeberj.takehome_beneficiaries.data.model.Beneficiary(
                firstName = jsonObject.getString("firstName"),
                middleName = jsonObject.optString("middleName", null),
                lastName = jsonObject.getString("lastName"),
                beneType = jsonObject.getString("beneType"),
                designationCode = jsonObject.getString("designationCode"),
                socialSecurityNumber = jsonObject.getString("socialSecurityNumber"),
                dateOfBirth = jsonObject.getString("dateOfBirth"),
                phoneNumber = jsonObject.getString("phoneNumber"),
                beneficiaryAddress = beneficiaryAddress
            )
            Log.d("BeneficiaryRepositoryImpl", "Parsed beneficiary: $beneficiary")
            beneficiaries.add(beneficiary)
        }
        return beneficiaries
    }
}

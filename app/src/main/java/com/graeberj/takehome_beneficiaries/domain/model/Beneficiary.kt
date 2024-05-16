package com.graeberj.takehome_beneficiaries.domain.model

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
)



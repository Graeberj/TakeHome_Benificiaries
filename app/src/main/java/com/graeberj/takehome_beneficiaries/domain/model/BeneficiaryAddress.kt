package com.graeberj.takehome_beneficiaries.domain.model

data class BeneficiaryAddress(
    val firstLineMailing: String,
    val scndLineMailing: String?,
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
)

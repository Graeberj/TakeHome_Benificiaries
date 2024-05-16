package com.graeberj.takehome_beneficiaries.domain.mapper

import com.graeberj.takehome_beneficiaries.data.model.Beneficiary as DataBeneficiary
import com.graeberj.takehome_beneficiaries.data.model.BeneficiaryAddress as DataBeneficiaryAddress
import com.graeberj.takehome_beneficiaries.domain.model.Beneficiary as DomainBeneficiary
import com.graeberj.takehome_beneficiaries.domain.model.BeneficiaryAddress as DomainBeneficiaryAddress

// Convert DataBeneficiary to DomainBeneficiary
fun DataBeneficiary.toDomain(): DomainBeneficiary {
    return DomainBeneficiary(
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        beneType = beneType,
        designationCode = designationCode,
        socialSecurityNumber = socialSecurityNumber,
        dateOfBirth = dateOfBirth,
        phoneNumber = phoneNumber,
        beneficiaryAddress = beneficiaryAddress.toDomain()
    )
}

// Convert DataBeneficiaryAddress to DomainBeneficiaryAddress
fun DataBeneficiaryAddress.toDomain(): DomainBeneficiaryAddress {
    return DomainBeneficiaryAddress(
        firstLineMailing = firstLineMailing,
        scndLineMailing = scndLineMailing,
        city = city,
        zipCode = zipCode,
        stateCode = stateCode,
        country = country
    )
}

// Convert DomainBeneficiary to DataBeneficiary
fun DomainBeneficiary.toData(): DataBeneficiary {
    return DataBeneficiary(
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        beneType = beneType,
        designationCode = designationCode,
        socialSecurityNumber = socialSecurityNumber,
        dateOfBirth = dateOfBirth,
        phoneNumber = phoneNumber,
        beneficiaryAddress = beneficiaryAddress.toData()
    )
}

// Convert DomainBeneficiaryAddress to DataBeneficiaryAddress
fun DomainBeneficiaryAddress.toData(): DataBeneficiaryAddress {
    return DataBeneficiaryAddress(
        firstLineMailing = firstLineMailing,
        scndLineMailing = scndLineMailing,
        city = city,
        zipCode = zipCode,
        stateCode = stateCode,
        country = country
    )
}

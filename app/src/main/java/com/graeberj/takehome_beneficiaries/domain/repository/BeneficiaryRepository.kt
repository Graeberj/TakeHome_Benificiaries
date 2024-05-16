package com.graeberj.takehome_beneficiaries.domain.repository

import com.graeberj.takehome_beneficiaries.domain.model.Beneficiary


interface BeneficiaryRepository {
    // interface used to define the methods that will be implemented in the BeneficiaryRepositoryImpl class
    // keeps the code clean and organized and separates the presentation layer from the data layer
    fun getBeneficiaries(): List<Beneficiary>
}

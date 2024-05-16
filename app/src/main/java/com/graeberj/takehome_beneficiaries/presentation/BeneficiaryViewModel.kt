package com.graeberj.takehome_beneficiaries.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graeberj.takehome_beneficiaries.domain.model.Beneficiary
import com.graeberj.takehome_beneficiaries.domain.repository.BeneficiaryRepository

class BeneficiaryViewModel(
    private val beneficiaryRepository: BeneficiaryRepository
) : ViewModel() {
    // LiveData object to hold the list of beneficiaries
    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()

    // Expose the LiveData object to the observers
    val beneficiaries: LiveData<List<Beneficiary>>
        get() = _beneficiaries

    // Load the beneficiaries from the repository when the ViewModel is created
    init {
        loadBeneficiaries()
    }

    // fetches the beneficiaries from the repository and updates the LiveData object
    private fun loadBeneficiaries() {
        _beneficiaries.value = beneficiaryRepository.getBeneficiaries()
    }
}
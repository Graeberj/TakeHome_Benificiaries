package com.graeberj.takehome_beneficiaries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.graeberj.takehome_beneficiaries.domain.repository.BeneficiaryRepository

class ViewModelFactory(
    private val repository: BeneficiaryRepository
) : ViewModelProvider.Factory {
    // Create instance of the BeneficiaryViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeneficiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeneficiaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

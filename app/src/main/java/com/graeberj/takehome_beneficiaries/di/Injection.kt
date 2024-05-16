package com.graeberj.takehome_beneficiaries.di

import android.content.Context
import com.graeberj.takehome_beneficiaries.data.repository.BeneficiaryRepositoryImpl
import com.graeberj.takehome_beneficiaries.domain.repository.BeneficiaryRepository

object Injection {
    // this method provides an instance of BeneficiaryRepository
    fun provideBeneficiaryRepository(context: Context): BeneficiaryRepository {
        return BeneficiaryRepositoryImpl(context)
    }
}
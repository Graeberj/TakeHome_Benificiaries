package com.graeberj.takehome_beneficiaries.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graeberj.takehome_beneficiaries.di.Injection
import com.graeberj.takehome_beneficiaries.domain.mapper.toData

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var beneficiaryAdapter: BeneficiaryAdapter

    // Using viewModels delegate to obtain ViewModel instance getting the repository from the Dependency Injection
    private val viewModel: BeneficiaryViewModel by viewModels {
        ViewModelFactory(Injection.provideBeneficiaryRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create RecyclerView programmatically and set the layout manager
        recyclerView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        // Set the RecyclerView as the content view
        setContentView(recyclerView)

        // Initialize the adapter with an empty list
        beneficiaryAdapter = BeneficiaryAdapter(emptyList()) { beneficiary ->
            // Start the BeneficiaryDetailActivity when a beneficiary is clicked and use the intent to pass the beneficiary data
            val intent = Intent(this, BeneficiaryDetailActivity::class.java)
            intent.putExtra("beneficiary", beneficiary.toData())
            startActivity(intent)
        }
        recyclerView.adapter = beneficiaryAdapter

        // Observe the beneficiaries LiveData from the ViewModel
        viewModel.beneficiaries.observe(this, Observer { beneficiaries ->
            // Update the adapter's data
            beneficiaryAdapter.updateData(beneficiaries)
        })
    }
}

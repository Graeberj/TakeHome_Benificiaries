package com.graeberj.takehome_beneficiaries.presentation

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.graeberj.takehome_beneficiaries.domain.mapper.toDomain
import com.graeberj.takehome_beneficiaries.domain.model.BeneficiaryAddress
import com.graeberj.takehome_beneficiaries.data.model.Beneficiary as DataBeneficiary
import java.text.SimpleDateFormat
import java.util.*

class BeneficiaryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the beneficiary data from the intent
        val dataBeneficiary: DataBeneficiary? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("beneficiary", DataBeneficiary::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra("beneficiary")
            }

        // Convert the data beneficiary to a domain beneficiary
        val beneficiary = dataBeneficiary?.toDomain()

        // Create a LinearLayout to display the beneficiary data
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16)
        }

        // Function to create a TextView with specified text and styling
        fun createTextView(text: String): TextView {
            return TextView(this).apply {
                this.text = text
                textSize = 16f
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(8)
                }
            }
        }

        // Add beneficiary details to the layout
        beneficiary?.let {
            val address = buildAddress(it.beneficiaryAddress)
            layout.apply {
                addView(createTextView("Name: ${it.firstName} ${it.middleName ?: ""} ${it.lastName}"))
                addView(createTextView("SSN: ${it.socialSecurityNumber}"))
                addView(createTextView("Date of Birth: ${formatDate(it.dateOfBirth)}"))
                addView(createTextView("Phone: ${it.phoneNumber}"))
                addView(createTextView("Address:"))
                address.split("\n").forEach { line ->
                    addView(createTextView(line).apply { setPadding(32, 0, 0, 0) })
                }
            }
        } ?: layout.addView(createTextView("Beneficiary not found"))

        setContentView(layout)
    }

    // Function to format the date of birth
    private fun formatDate(dateString: String): String {
        return try {
            val originalFormat = SimpleDateFormat("MMddyyyy", Locale.US)
            val targetFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val date = originalFormat.parse(dateString)
            targetFormat.format(date)
        } catch (e: Exception) {
            dateString // Return the original string if parsing fails
        }
    }

    // Function to build the address string
    private fun buildAddress(address: BeneficiaryAddress): String {
        return buildString {
            append(address.firstLineMailing)
            /*
            all data sets here had a null entry for scndLineMailing, but I wanted to show how I would
            handle the case where it is not null. I did notice that something I did was turn the null
            into a string "null" which is not ideal. I would need to investigate the parsing of the
            data that I did to see where that happened.
            * */
            address.scndLineMailing?.takeIf { it.isNotBlank() && it != "null" }?.let {
                append("\n$it")
            }
            append("\n${address.city}, ${address.stateCode} ${address.zipCode}, ${address.country}")
        }
    }
}

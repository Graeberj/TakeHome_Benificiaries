package com.graeberj.takehome_beneficiaries.presentation

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.graeberj.takehome_beneficiaries.domain.model.Beneficiary

class BeneficiaryAdapter(
    private var beneficiaries: List<Beneficiary>,
    private val onClick: (Beneficiary) -> Unit
) : RecyclerView.Adapter<BeneficiaryAdapter.BeneficiaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiaryViewHolder {
        // Create item view programmatically
        val layout = LinearLayout(parent.context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val text1 = TextView(parent.context).apply {
            id = android.R.id.text1
            textSize = 18f
            setTypeface(null, Typeface.BOLD)
        }

        val text2 = TextView(parent.context).apply {
            id = android.R.id.text2
            textSize = 14f
        }

        layout.addView(text1)
        layout.addView(text2)

        return BeneficiaryViewHolder(layout)
    }

    // Bind the data to the view holder for the item at the given position and set the click listener
    override fun onBindViewHolder(holder: BeneficiaryViewHolder, position: Int) {
        val beneficiary = beneficiaries[position]
        holder.bind(beneficiary)
        holder.itemView.setOnClickListener { onClick(beneficiary) }
    }

    override fun getItemCount(): Int = beneficiaries.size

    // Update the adapter's data and notify the adapter that the data has changed
    fun updateData(newBeneficiaries: List<Beneficiary>) {
        beneficiaries = newBeneficiaries
        notifyDataSetChanged()
    }

    // View holder for the beneficiary item. bind method sets the text for the text views
    class BeneficiaryViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view) {
        // Get the text views from the view used naming convention "Tv" for TextView
        private val nameTv: TextView = view.findViewById(android.R.id.text1)
        private val typeTv: TextView = view.findViewById(android.R.id.text2)

        fun bind(beneficiary: Beneficiary) {
            // convert designation code to designation
            val designation = if (beneficiary.designationCode == "P") "Primary" else "Contingent"
            // Set the text for the text views
            nameTv.text = "${beneficiary.firstName} ${beneficiary.lastName}"
            typeTv.text = "${beneficiary.beneType} - $designation"
        }
    }
}

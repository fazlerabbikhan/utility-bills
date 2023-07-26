package com.fazlerabbikhan.utilitybills.presentation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.fazlerabbikhan.utilitybills.R
import com.fazlerabbikhan.utilitybills.databinding.ActivityPayBillBinding
import com.fazlerabbikhan.utilitybills.domain.model.Field
import com.fazlerabbikhan.utilitybills.domain.model.Utility
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class PayBillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayBillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the selected Utility object from the intent
        val selectedUtility = intent.getParcelableExtra<Utility>("selectedUtility") as Utility
        Log.d("selectedUtility", selectedUtility.name)

        // Set the title of the action bar with the name of the selected utility
        supportActionBar?.title = selectedUtility.name

        // Dynamically generate input fields based on the fields list of the selectedUtility
        val fields = selectedUtility.fields
        generateInputFields(fields)

        // Set OnClickListener for the Submit button
        binding.btnSubmit.setOnClickListener {
            // Handle the form submission here
        }
    }

    @SuppressLint("InflateParams")
    private fun generateInputFields(fields: List<Field>) {
        val containerLayout = binding.containerLayout

        // Loop through the fields list and create input fields dynamically
        for (field in fields) {
            // Inflate the input_field_layout for each field
            val inputFieldLayout = layoutInflater.inflate(R.layout.input_field_layout, null) as LinearLayout
            val textInputLayout = inputFieldLayout.findViewById<TextInputLayout>(R.id.text_input_layout)
            val textInputEditText = inputFieldLayout.findViewById<TextInputEditText>(R.id.text_input_edit_text)

            // Set the label of the TextInputLayout (if available)
            field.label?.let { label ->
                textInputLayout.hint = label
            }

            // Add the input field to the container layout
            containerLayout.addView(inputFieldLayout)
        }
    }
}
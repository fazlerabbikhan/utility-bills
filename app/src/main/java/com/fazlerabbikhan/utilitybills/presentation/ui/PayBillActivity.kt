package com.fazlerabbikhan.utilitybills.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.fazlerabbikhan.utilitybills.R
import com.fazlerabbikhan.utilitybills.databinding.ActivityPayBillBinding
import com.fazlerabbikhan.utilitybills.domain.model.Field
import com.fazlerabbikhan.utilitybills.domain.model.Utility
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class PayBillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayBillBinding
    private val allFields = mutableListOf<TextInputEditText>()
    private val requiredFields = mutableListOf<TextInputEditText>()
    private val requiredErrorMsg = "This field is required"
    private val invalidErrorMsg = "Invalid input"

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
            // Remove focus to trigger validation for each input field
            for (requiredField in requiredFields) {
                requiredField.clearFocus()
            }

            // After validation, check if any field has an error
            val errorField = requiredFields.find { it.error == requiredErrorMsg || it.error == invalidErrorMsg }
                ?: requiredFields.find { it.text.isNullOrBlank() }

            if (errorField != null) {
                // Show an error message for the first field with an error
                val trimmedLabel = errorField.hint?.toString()?.substringAfter(' ')?.trim(':') ?: ""
                val errorMessage = if (errorField.error == invalidErrorMsg) {
                    "Please Enter Your $trimmedLabel Correctly"
                } else {
                    "Please Enter Your $trimmedLabel"
                }
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            } else {
                // No fields have errors, proceed with the form submission
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()

                // Prepare the input values list
                val inputValuesList = mutableListOf<Pair<String, String>>()
                for (field in allFields) {
                    val label = field.hint?.toString()?.substringAfter(' ')?.trim(':') ?: ""
                    val text = field.text.toString()
                    inputValuesList.add(Pair(label, text))
                }

                // Start the new activity and pass the input values list
                val intent = Intent(this, DisplayInputValuesActivity::class.java)
                intent.putExtra("inputValuesList", ArrayList(inputValuesList))
                startActivity(intent)
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun generateInputFields(fields: List<Field>) {
        val containerLayout = binding.containerLayout

        for (field in fields) {
            val inputFieldLayout = layoutInflater.inflate(R.layout.input_field_layout, null) as LinearLayout
            val textInputLayout = inputFieldLayout.findViewById<TextInputLayout>(R.id.text_input_layout)
            val textInputEditText = inputFieldLayout.findViewById<TextInputEditText>(R.id.text_input_edit_text)

            // Add the inputFieldLayout to the containerLayout
            containerLayout.addView(inputFieldLayout)

            // Add hint to the textInputLayout
            textInputLayout.hint = field.label

            // Apply validation to the inputEditText based on the field properties
            applyValidation(field, textInputEditText)

            // Add the TextInputEditText to the list of all fields
            allFields.add(textInputEditText)

            // Add the TextInputEditText to the list if the field is required
            if(field.required == true){
                requiredFields.add(textInputEditText)
            }
        }
    }

    private fun applyValidation(field: Field, textInputEditText: TextInputEditText) {
        // TextWatcher to validate the field while typing
        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (field.required == true) {
                    validateRequiredField(s, textInputEditText)
                }
                if (!field.regex.isNullOrEmpty()) {
                    validateRegexField(field.regex, s, textInputEditText)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // OnFocusChangeListener to validate the field when focus is lost (switching to another field)
        textInputEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (field.required == true) {
                    validateRequiredField(textInputEditText.text, textInputEditText)
                }
                if (!field.regex.isNullOrEmpty()) {
                    validateRegexField(field.regex, textInputEditText.text, textInputEditText)
                }
            }
        }
    }

    private fun validateRequiredField(text: CharSequence?, textInputEditText: TextInputEditText) {
        if (text.isNullOrEmpty()) {
            textInputEditText.error = requiredErrorMsg
        }
        Log.d("validateRequiredField", "$text ${textInputEditText.error}")
    }

    private fun validateRegexField(regex: String, text: CharSequence?, textInputEditText: TextInputEditText) {
        if (!text.isNullOrEmpty() && !text.matches(Regex(regex))) {
            textInputEditText.error = invalidErrorMsg
        }
        Log.d("validateRegexField", "$text ${textInputEditText.error}")
    }
}
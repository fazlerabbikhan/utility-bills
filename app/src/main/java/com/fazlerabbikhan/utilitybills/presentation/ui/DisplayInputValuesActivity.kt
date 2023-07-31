package com.fazlerabbikhan.utilitybills.presentation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.fazlerabbikhan.utilitybills.R
import com.fazlerabbikhan.utilitybills.databinding.ActivityDisplayInputValuesBinding

class DisplayInputValuesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayInputValuesBinding
    private lateinit var containerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayInputValuesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        containerLayout = binding.containerLayout

        val inputValuesList = intent.getSerializableExtra("inputValuesList") as ArrayList<Pair<String, String>>
        displayInputValues(inputValuesList)
    }

    private fun displayInputValues(inputValuesList: ArrayList<Pair<String, String>>) {
        inputValuesList.forEach { pair ->
            val hint = pair.first
            val text = pair.second
            addReadOnlyTextInputEditText(hint, text)
        }
    }

    @SuppressLint("InflateParams")
    private fun addReadOnlyTextInputEditText(hint: String, text: String) {
        val inputFieldLayout = layoutInflater.inflate(R.layout.input_values_layout, null) as LinearLayout
        val textLabel = inputFieldLayout.findViewById<TextView>(R.id.text_label)
        val textValue = inputFieldLayout.findViewById<TextView>(R.id.text_value)

        // Set label and value text
        textLabel.text = hint
        textValue.text = text

        // Add the inputFieldLayout to the containerLayout
        containerLayout.addView(inputFieldLayout)
    }
}
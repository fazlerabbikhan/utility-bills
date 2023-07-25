package com.fazlerabbikhan.utilitybills.presentation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.fazlerabbikhan.utilitybills.R
import com.fazlerabbikhan.utilitybills.databinding.ActivityMainBinding
import com.fazlerabbikhan.utilitybills.domain.model.Utility
import com.fazlerabbikhan.utilitybills.presentation.ui.utility_type_list.UtilityTypeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up ViewModel and observe state changes
        val viewModel: UtilityTypeListViewModel = ViewModelProvider(this)[UtilityTypeListViewModel::class.java]
        viewModel.state.observe(this) { state ->
            Log.d("ViewModelObserver", "$state")

            // Clear any previous views in the gridLayout
            binding.utilityTypesLayout.removeAllViews()

            // Add the utility type cards to the grid layout dynamically
            for (utilityType in state.utilityTypes) {
                val utilityTypeLayout = layoutInflater.inflate(R.layout.utility_type_list_item, null) as LinearLayout
                val textView = utilityTypeLayout.findViewById<TextView>(R.id.utility_type_name)
                val params = GridLayout.LayoutParams()
                params.width = 0
                params.height = GridLayout.LayoutParams.WRAP_CONTENT
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                utilityTypeLayout.layoutParams = params
                textView.text = utilityType.name
                utilityTypeLayout.setOnClickListener {
                    viewModel.filterUtilityTypes(utilityType.name)
                }
                binding.utilityTypesLayout.addView(utilityTypeLayout)
            }

            // Extract the list of utilities from the state
            val utilities: List<Utility> = state.filteredUtilityTypes.flatMap { utilityType -> utilityType.utilities }

            // Clear any previous views in the utilitiesLayout
            binding.utilitiesLayout.removeAllViews()

            // Populate the utilitiesLayout with TextViews displaying the utility names
            for (utility in utilities) {
                val utilityLayout = layoutInflater.inflate(R.layout.utility_list_item, null) as LinearLayout
                val textView = utilityLayout.findViewById<TextView>(R.id.utility_name)
                textView.text = utility.name
                binding.utilitiesLayout.addView(utilityLayout)
            }
        }
    }
}
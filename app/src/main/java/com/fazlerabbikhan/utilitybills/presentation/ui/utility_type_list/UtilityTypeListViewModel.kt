package com.fazlerabbikhan.utilitybills.presentation.ui.utility_type_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fazlerabbikhan.utilitybills.common.Resource
import com.fazlerabbikhan.utilitybills.domain.model.UtilityType
import com.fazlerabbikhan.utilitybills.domain.use_case.GetUtilityTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UtilityTypeListViewModel @Inject constructor(
    val getUtilityTypesUseCase: GetUtilityTypesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UtilityTypeListState>()
    val state: LiveData<UtilityTypeListState> = _state

    private var allUtilityTypes: List<UtilityType> = emptyList()

    init {
        getUtilityTypes()
    }

    private fun getUtilityTypes() {
        getUtilityTypesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("UtilityTypeListViewModel", "${result.data}")
                    allUtilityTypes = result.data ?: emptyList()
                    _state.value = UtilityTypeListState(utilityTypes = result.data ?: emptyList(), filteredUtilityTypes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = UtilityTypeListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UtilityTypeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun filterUtilityTypes(category: String) {
        val filteredUtilityType = allUtilityTypes.filter { utilityType ->
            utilityType.name == category
        }

        _state.value = UtilityTypeListState(utilityTypes = allUtilityTypes, filteredUtilityTypes = filteredUtilityType)
    }
}
package com.fazlerabbikhan.utilitybills.presentation.ui.utility_type_list

import com.fazlerabbikhan.utilitybills.domain.model.UtilityType

data class UtilityTypeListState(
    val isLoading: Boolean = false,
    val utilityTypes: List<UtilityType> = emptyList(),
    val filteredUtilityTypes: List<UtilityType> = emptyList(),
    val error: String = ""
)
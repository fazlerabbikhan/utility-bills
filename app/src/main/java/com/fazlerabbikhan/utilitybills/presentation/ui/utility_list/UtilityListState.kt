package com.fazlerabbikhan.utilitybills.presentation.ui.utility_list

import com.fazlerabbikhan.utilitybills.domain.model.Utility

data class UtilityListState(
    val isLoading: Boolean = false,
    val utilities: List<Utility> = emptyList(),
    val error: String = ""
)
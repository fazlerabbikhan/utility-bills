package com.fazlerabbikhan.utilitybills.presentation.ui.field_list

import com.fazlerabbikhan.utilitybills.domain.model.Field

data class FieldListState(
    val isLoading: Boolean = false,
    val fields: List<Field> = emptyList(),
    val error: String = ""
)

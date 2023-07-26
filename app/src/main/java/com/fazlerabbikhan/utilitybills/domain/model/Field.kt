package com.fazlerabbikhan.utilitybills.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Field(
    val code: String?,
    val config: @RawValue Any?,
    val default_value: @RawValue Any?,
    val label: String?,
    val options: @RawValue Any?,
    val regex: String?,
    val required: Boolean?,
    val type: String?
) : Parcelable
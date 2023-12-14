package com.d121211078.valorantkuy.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Role(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
) : Parcelable
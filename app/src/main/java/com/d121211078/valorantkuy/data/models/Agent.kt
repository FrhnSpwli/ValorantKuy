package com.d121211078.valorantkuy.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Agent(
    val uuid: String??,
    val displayName: String?,
    val description: String?,
    val developerName: String?,
    val displayIcon: String?,
    val fullPortrait: String?,
    val background: String?,
    val role:Role?,
): Parcelable
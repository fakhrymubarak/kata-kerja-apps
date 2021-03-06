package com.fakhry.katakerjaapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Register(
    val token: String,
    val id: Int,
    val name: String,
    val idRole: Int,
) : Parcelable

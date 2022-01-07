package com.example.run.api.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class corridas (
    val id: Int,
    val km: Int,
    val id_user: Int,
    val hora_inicio: String,
    val hora_fim: String,
    val data: String,
    val users_id: Int,
    val created_at:String

): Parcelable
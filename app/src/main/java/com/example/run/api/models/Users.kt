package com.example.run.api.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Users (
        val users_id: Int,
        val created_at:String,
        val id: Int,
        val firstName: String,
        val lastName: String,
        val age: Int

        ):Parcelable

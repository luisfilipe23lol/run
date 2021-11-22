package com.example.run.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "corrida_table")
class Corrida (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_user: Int,
    val hora_inicio: String,
    val hora_fim: String,
    val data: String

): Parcelable


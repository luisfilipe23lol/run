package com.example.run.api.dto

import com.example.run.api.models.Users
import com.example.run.api.models.corridas

class CorridasDto (
    val status : String,
    val message : String,
    val corridas : List<corridas>,
    val token : String
        )

package com.example.run.api.dto

import com.example.run.api.models.Users

class UsersDto (
    val status : String,
    val message : String,
    val users : List<Users>,
    val token : String
        )

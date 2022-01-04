package com.example.run.api.dto

import com.example.run.api.models.User

class UserDto (
    val status : String,
    val message : String,
    val user : List<User>,
    val token : String
)
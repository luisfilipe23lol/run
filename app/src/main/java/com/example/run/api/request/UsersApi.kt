package com.example.run.api.request

import com.example.run.api.dto.UsersDto
import com.example.run.api.models.Users
import retrofit2.Call
import retrofit2.http.*

interface UsersApi {
    @GET("user/read")
    fun getUsers(@Header("Authorization") token: String): Call<List<Users>>

    @FormUrlEncoded
    @POST("user/create")
    fun createUsers(
        @Header("Authorization") token: String,
        @Field("users_id") users_id: String?,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("age") age: Int
    ): Call<UsersDto>

    @FormUrlEncoded
    @POST("user/update")
    fun updateUsers(
        @Header("Authorization") token: String,
        @Field("id") id: Int,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("age") age: Int
    ): Call<UsersDto>

    @FormUrlEncoded
    @POST("user/delete")
    fun deleteUsers(@Header("Authorization") token: String, @Field("id") id: Int): Call<UsersDto>
}
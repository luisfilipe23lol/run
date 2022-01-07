package com.example.run.api.request

import com.example.run.api.dto.CorridasDto
import com.example.run.api.models.corridas
import retrofit2.Call
import retrofit2.http.*

interface CorridasApi {
    @GET("corrida/read")
    fun getCorrida(@Header("Authorization") token: String): Call<List<corridas>>

    @FormUrlEncoded
    @POST("corrida/create")
    fun createCorrida(
        @Header("Authorization") token: String,
        @Field("users_id") users_id: String?,
        @Field("km") km: Int,
        @Field("id_user") id_user: Int,
        @Field("hora_inicio") hora_inicio: String,
        @Field("hora_fim") hora_fim: String,
        @Field("data") data: String
    ): Call<CorridasDto>

    @FormUrlEncoded
    @POST("corrida/update")
    fun updateCorrida(
        @Header("Authorization") token: String,
        @Field("id") id: Int,
        @Field("km") km: Int,
        @Field("id_user") id_user: Int,
        @Field("hora_inicio") hora_inicio: String,
        @Field("hora_fim") hora_fim: String,
        @Field("data") data: String
    ): Call<CorridasDto>

    @FormUrlEncoded
    @POST("corrida/delete")
    fun deleteCorrida(@Header("Authorization") token: String, @Field("id") id: Int): Call<CorridasDto>
}
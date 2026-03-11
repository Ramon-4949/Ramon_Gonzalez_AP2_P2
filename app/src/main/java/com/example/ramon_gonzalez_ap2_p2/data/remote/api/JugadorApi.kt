package com.example.ramon_gonzalez_ap2_p2.data.remote.api

import com.example.ramon_gonzalez_ap2_p2.data.remote.dto.JugadorRequestDto
import com.example.ramon_gonzalez_ap2_p2.data.remote.dto.JugadorResponseDto
import retrofit2.Response
import retrofit2.http.*

interface JugadorApi {
    @GET("api/Jugadores")
    suspend fun getJugadores(): Response<List<JugadorResponseDto>>

    @GET("api/Jugadores/{id}")
    suspend fun getJugadorById(@Path("id") id: Int): Response<JugadorResponseDto>

    @POST("api/Jugadores")
    suspend fun createJugador(@Body jugador: JugadorRequestDto): Response<JugadorResponseDto>

    @PUT("api/Jugadores/{id}")
    suspend fun updateJugador(@Path("id") id: Int, @Body jugador: JugadorRequestDto): Response<Unit>
}
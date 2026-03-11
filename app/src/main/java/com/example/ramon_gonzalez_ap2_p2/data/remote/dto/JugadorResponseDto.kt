package com.example.ramon_gonzalez_ap2_p2.data.remote.dto

import com.squareup.moshi.JsonClass
import com.example.ramon_gonzalez_ap2_p2.domain.model.Jugador

@JsonClass(generateAdapter = true)
data class JugadorResponseDto(
    val jugadorId: Int?,
    val nombres: String?,
    val email: String?
) {
    fun toDomain() = Jugador(
        jugadorId = jugadorId ?: 0,
        nombres = nombres ?: "",
        email = email ?: ""
    )
}

fun Jugador.toRequestDto() = JugadorRequestDto(
    nombres = nombres,
    email = email
)
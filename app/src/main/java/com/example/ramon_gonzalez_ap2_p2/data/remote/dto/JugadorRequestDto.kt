package com.example.ramon_gonzalez_ap2_p2.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JugadorRequestDto(
    val nombres : String,
    val email: String,
)
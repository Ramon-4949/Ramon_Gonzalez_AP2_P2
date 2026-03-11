package com.example.ramon_gonzalez_ap2_p2.presentation.list

import com.example.ramon_gonzalez_ap2_p2.domain.model.Jugador

data class JugadorListUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val error: String? = null
)
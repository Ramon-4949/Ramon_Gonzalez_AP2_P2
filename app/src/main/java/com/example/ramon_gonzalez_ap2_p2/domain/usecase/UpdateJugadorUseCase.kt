package com.example.ramon_gonzalez_ap2_p2.domain.usecase

import com.example.ramon_gonzalez_ap2_p2.domain.model.Jugador
import com.example.ramon_gonzalez_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class UpdateJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int, jugador: Jugador) = repository.updateJugador(id, jugador)
}
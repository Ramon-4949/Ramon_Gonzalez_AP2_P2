package com.example.ramon_gonzalez_ap2_p2.domain.repository

import com.example.ramon_gonzalez_ap2_p2.domain.model.Jugador
import com.example.ramon_gonzalez_ap2_p2.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun getJugadores(): Flow<Resource<List<Jugador>>>
    suspend fun getJugadorById(id: Int): Resource<Jugador>
    suspend fun createJugador(jugador: Jugador): Resource<Jugador>
    suspend fun updateJugador(id: Int, jugador: Jugador): Resource<Unit>
}
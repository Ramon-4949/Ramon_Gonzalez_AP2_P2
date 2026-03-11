package com.example.ramon_gonzalez_ap2_p2.data.repository

import com.example.ramon_gonzalez_ap2_p2.data.remote.dto.toRequestDto
import com.example.ramon_gonzalez_ap2_p2.data.remote.remotedatasource.JugadorRemoteDataSource
import com.example.ramon_gonzalez_ap2_p2.domain.model.Jugador
import com.example.ramon_gonzalez_ap2_p2.domain.repository.JugadorRepository
import com.example.ramon_gonzalez_ap2_p2.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override fun getJugadores(): Flow<Resource<List<Jugador>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getJugadores().fold(
            onSuccess = { dtoList -> emit(Resource.Success(dtoList.map { it.toDomain() })) },
            onFailure = { emit(Resource.Error(it.message ?: "Error desconocido")) }
        )
    }

    override suspend fun getJugadorById(id: Int): Resource<Jugador> {
        return remoteDataSource.getJugadorById(id).fold(
            onSuccess = { Resource.Success(it.toDomain()) },
            onFailure = { Resource.Error(it.message ?: "Error desconocido") }
        )
    }

    override suspend fun createJugador(jugador: Jugador): Resource<Jugador> {
        return remoteDataSource.createJugador(jugador.toRequestDto()).fold(
            onSuccess = { Resource.Success(it.toDomain()) },
            onFailure = { Resource.Error(it.message ?: "Error al crear") }
        )
    }

    override suspend fun updateJugador(id: Int, jugador: Jugador): Resource<Unit> {
        return remoteDataSource.updateJugador(id, jugador.toRequestDto()).fold(
            onSuccess = { Resource.Success(Unit) },
            onFailure = { Resource.Error(it.message ?: "Error al actualizar") }
        )
    }
}
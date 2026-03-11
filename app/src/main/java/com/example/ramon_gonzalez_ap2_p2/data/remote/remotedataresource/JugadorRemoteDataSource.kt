package com.example.ramon_gonzalez_ap2_p2.data.remote.remotedatasource

import com.example.ramon_gonzalez_ap2_p2.data.remote.api.JugadorApi
import com.example.ramon_gonzalez_ap2_p2.data.remote.dto.JugadorRequestDto
import com.example.ramon_gonzalez_ap2_p2.data.remote.dto.JugadorResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadorApi
) {
    suspend fun getJugadores(): Result<List<JugadorResponseDto>> {
        return try {
            val response = api.getJugadores()
            if (!response.isSuccessful) Result.failure(Exception("Error HTTP ${response.code()}"))
            else Result.success(response.body() ?: emptyList())
        } catch (e: HttpException) { Result.failure(Exception("Error servidor", e))
        } catch (e: Exception) { Result.failure(Exception("Error desconocido", e)) }
    }

    suspend fun getJugadorById(id: Int): Result<JugadorResponseDto> {
        return try {
            val response = api.getJugadorById(id)
            if (!response.isSuccessful) Result.failure(Exception("Error HTTP ${response.code()}"))
            else Result.success(response.body()!!)
        } catch (e: HttpException) { Result.failure(Exception("Error servidor", e))
        } catch (e: Exception) { Result.failure(Exception("Error desconocido", e)) }
    }

    suspend fun createJugador(jugador: JugadorRequestDto): Result<JugadorResponseDto> {
        return try {
            val response = api.createJugador(jugador)
            if (!response.isSuccessful) Result.failure(Exception("Error HTTP ${response.code()}"))
            else Result.success(response.body()!!)
        } catch (e: Exception) { Result.failure(Exception("Error de red", e)) }
    }

    suspend fun updateJugador(id: Int, jugador: JugadorRequestDto): Result<Unit> {
        return try {
            val response = api.updateJugador(id, jugador)
            if (!response.isSuccessful) Result.failure(Exception("Error HTTP ${response.code()}"))
            else Result.success(Unit)
        } catch (e: Exception) { Result.failure(Exception("Error de red", e)) }
    }
}
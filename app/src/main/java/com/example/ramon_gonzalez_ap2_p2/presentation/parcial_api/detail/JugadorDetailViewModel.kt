package com.example.ramon_gonzalez_ap2_p2.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.ramon_gonzalez_ap2_p2.domain.model.Jugador
import com.example.ramon_gonzalez_ap2_p2.domain.usecase.GetJugadorByIdUseCase
import com.example.ramon_gonzalez_ap2_p2.domain.usecase.CreateJugadorUseCase
import com.example.ramon_gonzalez_ap2_p2.domain.usecase.UpdateJugadorUseCase
import com.example.ramon_gonzalez_ap2_p2.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorDetailViewModel @Inject constructor(
    private val getJugadorByIdUseCase: GetJugadorByIdUseCase,
    private val createJugadorUseCase: CreateJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(JugadorDetailUiState())
    val state = _state.asStateFlow()

    init {
        val id = savedStateHandle.get<Int>("id") ?: 0
        if (id > 0) {
            loadJugador(id)
        }
    }

    fun onEvent(event: JugadorDetailEvent) {
        when (event) {
            is JugadorDetailEvent.OnNombresChange -> _state.update { it.copy(nombres = event.nombres, nombresError = null) }
            is JugadorDetailEvent.OnEmailChange -> _state.update { it.copy(email = event.email, emailError = null) }
            JugadorDetailEvent.Save -> saveJugador()
        }
    }

    private fun loadJugador(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = getJugadorByIdUseCase(id)
            when (result) {
                is Resource.Success -> {
                    val data = result.data
                    if (data != null) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                jugadorId = data.jugadorId,
                                nombres = data.nombres,
                                email = data.email
                            )
                        }
                    }
                }
                is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }

    private fun saveJugador() {
        val nombres = _state.value.nombres
        val email = _state.value.email
        var hasError = false

        if (nombres.isBlank()) {
            _state.update { it.copy(nombresError = "El nombre no puede estar vacío") }
            hasError = true
        }

        if (email.isBlank() || !email.contains("@")) {
            _state.update { it.copy(emailError = "Ingrese un email válido que contenga @") }
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val currentState = _state.value
            val jugador = Jugador(
                jugadorId = currentState.jugadorId,
                nombres = currentState.nombres,
                email = currentState.email
            )

            val result = if (currentState.jugadorId == 0) {
                createJugadorUseCase(jugador)
            } else {
                updateJugadorUseCase(currentState.jugadorId, jugador)
            }

            when (result) {
                is Resource.Success -> _state.update { it.copy(isLoading = false, isSaved = true) }
                is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }
}
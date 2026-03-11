package com.example.ramon_gonzalez_ap2_p2.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.ramon_gonzalez_ap2_p2.domain.usecase.GetJugadoresUseCase
import com.example.ramon_gonzalez_ap2_p2.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorListViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(JugadorListUiState())
    val state = _state.asStateFlow()

    init {
        loadJugadores()
    }

    fun onEvent(event: JugadorListEvent) {
        when (event) {
            JugadorListEvent.Load -> loadJugadores()
        }
    }

    private fun loadJugadores() {
        viewModelScope.launch {
            getJugadoresUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true, error = null) }
                    is Resource.Success -> _state.update {
                        it.copy(isLoading = false, jugadores = result.data ?: emptyList())
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}
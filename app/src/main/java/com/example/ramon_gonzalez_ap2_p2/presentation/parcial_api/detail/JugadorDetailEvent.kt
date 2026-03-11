package com.example.ramon_gonzalez_ap2_p2.presentation.detail

sealed interface JugadorDetailEvent {
    data class OnNombresChange(val nombres: String) : JugadorDetailEvent
    data class OnEmailChange(val email: String) : JugadorDetailEvent
    data object Save : JugadorDetailEvent
}
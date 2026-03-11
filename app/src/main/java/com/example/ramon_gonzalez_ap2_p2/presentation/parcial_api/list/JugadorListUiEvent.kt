package com.example.ramon_gonzalez_ap2_p2.presentation.list

sealed interface JugadorListEvent {
    data object Load : JugadorListEvent
}
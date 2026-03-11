package com.example.ramon_gonzalez_ap2_p2.presentation.detail

data class JugadorDetailUiState(
    val isLoading: Boolean = false,
    val jugadorId: Int = 0,
    val nombres: String = "",
    val email: String = "",
    val nombresError: String? = null,
    val emailError: String? = null,
    val error: String? = null,
    val isSaved: Boolean = false
)
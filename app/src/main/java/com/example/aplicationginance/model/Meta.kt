package com.example.aplicationginance.model

data class Meta(
    val nombre: String,
    val montoObjetivo: Double,
    val plazoMeses: Int,
    val fechaInicio: String,
    var montoActual: Double = 0.0
)


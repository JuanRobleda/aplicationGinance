package com.example.aplicationginance.model

data class Ingreso(
    val fecha: String,
    val monto: Double,
    val categoria: String,
    val descripcion: String?,
    val fuente: String
)

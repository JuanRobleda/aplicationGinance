package com.example.aplicationginance.model

data class Egreso(
    val fecha: String,
    val monto: Double,
    val categoria: String,
    val subcategoria: String,
    val descripcion: String?,
    val metodoPago: String,
    val esExtra: Boolean
)

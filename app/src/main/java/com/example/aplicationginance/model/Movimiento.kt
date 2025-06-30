package com.example.aplicationginance.model


sealed class Movimiento {
    data class Ingreso(
        val fecha: String,
        val monto: Double,
        val categoria: String,
        val descripcion: String?,
        val fuente: String
    ) : Movimiento()

    data class Egreso(
        val fecha: String,
        val monto: Double,
        val categoria: String,
        val subcategoria: String,
        val descripcion: String?,
        val metodoPago: String,
        val esExtra: Boolean
    ) : Movimiento()
}

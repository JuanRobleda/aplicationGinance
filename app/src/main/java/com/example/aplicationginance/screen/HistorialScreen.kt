package com.example.aplicationginance.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicationginance.model.Movimiento

@Composable
fun HistorialScreen(navController: NavController) {
    // Datos temporales simulados
    val movimientos = remember {
        mutableStateListOf<Movimiento>(
            Movimiento.Ingreso("01/06/2025", 500.0, "Trabajo", "Salario", "Empresa XYZ"),
            Movimiento.Egreso("03/06/2025", 120.0, "Comida", "Supermercado", "Compras semana", "Efectivo", false),
            Movimiento.Ingreso("10/06/2025", 200.0, "Freelance", "Proyecto web", "Cliente ABC"),
            Movimiento.Egreso("15/06/2025", 80.0, "Transporte", "Gasolina", "Viaje", "Tarjeta", true)
        )
    }

    var filtroTipo by remember { mutableStateOf("Todos") }

    val tipos = listOf("Todos", "Ingresos", "Egresos")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("📋 Historial Financiero", style = MaterialTheme.typography.titleLarge)

        // Filtro por tipo
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            tipos.forEach { tipo ->
                FilterChip(
                    selected = filtroTipo == tipo,
                    onClick = { filtroTipo = tipo },
                    label = { Text(tipo) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            val filtrados = movimientos.filter {
                when (filtroTipo) {
                    "Ingresos" -> it is Movimiento.Ingreso
                    "Egresos" -> it is Movimiento.Egreso
                    else -> true
                }
            }

            items(filtrados.size) { index ->
                val item = filtrados[index]

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        when (item) {
                            is Movimiento.Ingreso -> {
                                Text("💰 Ingreso - ${item.categoria}")
                                Text("Fecha: ${item.fecha}")
                                Text("Monto: C$${item.monto}")
                                item.descripcion?.let { Text("Descripción: $it") }
                                Text("Fuente: ${item.fuente}")
                            }
                            is Movimiento.Egreso -> {
                                Text("📉 Egreso - ${item.categoria}/${item.subcategoria}")
                                Text("Fecha: ${item.fecha}")
                                Text("Monto: C$${item.monto}")
                                item.descripcion?.let { Text("Descripción: $it") }
                                Text("Pago: ${item.metodoPago}")
                                if (item.esExtra) Text("📌 Gasto Extra")
                            }
                        }
                    }
                }
            }
        }

        // Botón exportar (funcionalidad futura)
        Button(onClick = {
            // Aquí se podría exportar a PDF o Excel en una versión con backend
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("📤 Exportar resumen")
        }
    }
}

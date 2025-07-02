package com.example.aplicationginance.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.roundToInt


@Composable
fun PlanificacionScreen(navController: NavController) {
    var ingresoMensual by remember { mutableStateOf(1200.0) }
    var egresoMensual by remember { mutableStateOf(850.0) }

    val saldoDisponible = ingresoMensual - egresoMensual
    val ahorro15 = (saldoDisponible * 0.15).roundToInt()
    val ahorro30 = (saldoDisponible * 0.30).roundToInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Planificación Inteligente", style = MaterialTheme.typography.titleLarge)

        Text("Ingresos mensuales: C$$ingresoMensual")
        Text("Egresos mensuales: C$$egresoMensual")
        Text("Saldo disponible: C$$saldoDisponible")

        Divider()

        Text("Sugerencias de ahorro:")

        Text("🔹 Ahorro recomendado (15% del saldo): C$$ahorro15")
        Text("🔸 Ahorro intensivo (30% del saldo): C$$ahorro30")

        Divider()

        Text("Tips financieros:")
        Text("• Automatiza tus ahorros.")
        Text("• Reduce gastos variables antes que los fijos.")
        Text("• Revisa tus metas mensualmente.")
    }
}

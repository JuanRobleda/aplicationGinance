package com.example.aplicationginance.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Juan Robleda", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("configuracion") }) {
            Text("Configuración")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Resumen del mes", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ingresos: $1200")
        Text("Egresos: $850")
        Text("Saldo disponible: $350")
        Text("Ahorro sugerido: $100")

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("registro_ingreso") }) {
            Text("Agregar Ingreso")
        }
        Button(onClick = { navController.navigate("registro_egreso") }) {
            Text("Agregar Gasto")
        }
        Button(onClick = { navController.navigate("metas") }) {
            Text("Mis Metas")
        }
        Button(onClick = { navController.navigate("historial") }) {
            Text("Historial")
        }
        Button(onClick = { navController.navigate("planificacion") }) {
            Text("Planificación Inteligente")
        }
    }
}

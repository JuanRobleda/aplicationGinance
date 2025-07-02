package com.example.aplicationginance

import ConfiguracionScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Alignment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicationginance.model.Movimiento
import com.example.aplicationginance.screen.HistorialScreen
import com.example.aplicationginance.screen.InicioScreen
import com.example.aplicationginance.screen.MetasScreen
import com.example.aplicationginance.screen.PlanificacionScreen
import com.example.aplicationginance.screen.RegistroEgresoScreen
import com.example.aplicationginance.screen.RegistroIngresoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio") { InicioScreen(navController) }
                    composable("registro_ingreso") { RegistroIngresoScreen(navController) }
                    composable("registro_egreso") { RegistroEgresoScreen(navController) }
                    composable("metas") { MetasScreen(navController) }
                    composable("historial") { HistorialScreen(navController) }
                    composable("planificacion") { PlanificacionScreen(navController) }
                    composable("configuracion") { ConfiguracionScreen(navController) }
                }
            }
        }
    }
}

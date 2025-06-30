package com.example.aplicationginance.screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicationginance.model.Egreso
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroEgresoScreen(navController: NavController) {
    val context = LocalContext.current

    val calendario = Calendar.getInstance()
    var fecha by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Comida") }
    var subcategoria by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var metodoPago by remember { mutableStateOf("") }
    var esExtra by remember { mutableStateOf(false) }

    val categorias = listOf("Vivienda", "Comida", "Transporte", "Educación", "Salud", "Ocio")

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registrar Gasto", style = MaterialTheme.typography.titleLarge)

        Button(onClick = {
            DatePickerDialog(
                context,
                { _: DatePicker, año, mes, día ->
                    fecha = "$día/${mes + 1}/$año"
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text(if (fecha.isEmpty()) "Seleccionar Fecha" else "Fecha: $fecha")
        }

        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            singleLine = true
        )

        // Categoría Dropdown
        var expandedCat by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expandedCat, onExpandedChange = { expandedCat = !expandedCat }) {
            OutlinedTextField(readOnly = true, value = categoria, onValueChange = {}, label = { Text("🧾 Categoría") }, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCat) }, modifier = Modifier.menuAnchor())
            ExposedDropdownMenu(expanded = expandedCat, onDismissRequest = { expandedCat = false }) {
                categorias.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            categoria = it
                            expandedCat = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = subcategoria,
            onValueChange = { subcategoria = it },
            label = { Text("Subcategoría") },
            singleLine = true
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción (opcional)") },
            maxLines = 3
        )

        OutlinedTextField(
            value = metodoPago,
            onValueChange = { metodoPago = it },
            label = { Text("💳 Método de pago") },
            singleLine = true
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = esExtra, onCheckedChange = { esExtra = it })
            Text("Marcar como Gasto Extra")
        }

        Button(onClick = {
            val egreso = Egreso(
                fecha = fecha,
                monto = monto.toDoubleOrNull() ?: 0.0,
                categoria = categoria,
                subcategoria = subcategoria,
                descripcion = descripcion,
                metodoPago = metodoPago,
                esExtra = esExtra
            )

            Toast.makeText(context, "Gasto registrado: $${egreso.monto}", Toast.LENGTH_LONG).show()
            navController.popBackStack()
        }) {
            Text("Registrar Gasto")
        }
    }
}

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
import com.example.aplicationginance.model.Ingreso
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroIngresoScreen(navController: NavController) {
    val context = LocalContext.current

    var fecha by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Trabajo") }
    var descripcion by remember { mutableStateOf("") }
    var fuente by remember { mutableStateOf("") }

    val categorias = listOf("Trabajo", "Freelance", "Regalo", "Otro")

    val calendario = Calendar.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registrar Ingreso", style = MaterialTheme.typography.titleLarge)

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

        // Selector de categoría
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                readOnly = true,
                value = categoria,
                onValueChange = {},
                label = { Text("🧾 Categoría") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                categorias.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            categoria = it
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción (opcional)") },
            singleLine = false,
            maxLines = 3
        )

        OutlinedTextField(
            value = fuente,
            onValueChange = { fuente = it },
            label = { Text("💼 Fuente o tipo de ingreso") },
            singleLine = true
        )

        Button(onClick = {
            val ingreso = Ingreso(
                fecha = fecha,
                monto = monto.toDoubleOrNull() ?: 0.0,
                categoria = categoria,
                descripcion = descripcion,
                fuente = fuente
            )

            // Aquí lo guardarías o enviarías al backend más adelante
            Toast.makeText(context, "Ingreso registrado: $${ingreso.monto}", Toast.LENGTH_LONG).show()
            navController.popBackStack() // volver a la pantalla anterior
        }) {
            Text("✅ Registrar Ingreso")
        }
    }
}


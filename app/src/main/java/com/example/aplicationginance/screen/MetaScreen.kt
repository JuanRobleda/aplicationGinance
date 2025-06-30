package com.example.aplicationginance.screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicationginance.model.Meta
import java.util.*

@Composable
fun MetasScreen(navController: NavController) {
    val context = LocalContext.current

    val metas = remember { mutableStateListOf<Meta>() }

    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("🎯 Metas Financieras", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(metas.size) { index ->
                val meta = metas[index]
                val progreso = if (meta.montoObjetivo != 0.0)
                    (meta.montoActual / meta.montoObjetivo).coerceIn(0.0, 1.0)
                else 0.0

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🏷️ ${meta.nombre}")
                        Text("Meta: C$${meta.montoObjetivo}")
                        LinearProgressIndicator(progress = progreso.toFloat(), modifier = Modifier.fillMaxWidth())
                        Text("Progreso: ${(progreso * 100).toInt()}%")
                    }
                }
            }
        }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("➕ Nueva Meta")
        }
    }
    @Composable
    fun CrearMetaDialog(onGuardar: (Meta) -> Unit, onCancelar: () -> Unit) {
        val context = LocalContext.current
        var nombre by remember { mutableStateOf("") }
        var monto by remember { mutableStateOf("") }
        var plazo by remember { mutableStateOf("") }
        var fechaInicio by remember { mutableStateOf("") }

        val calendario = Calendar.getInstance()

        AlertDialog(
            onDismissRequest = { onCancelar() },
            title = { Text("Crear nueva meta") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("📝 Nombre") })
                    OutlinedTextField(
                        value = monto,
                        onValueChange = { monto = it },
                        label = { Text("💰 Monto objetivo") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        value = plazo,
                        onValueChange = { plazo = it },
                        label = { Text("📆 Plazo (meses)") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                    Button(onClick = {
                        DatePickerDialog(
                            context,
                            { _: DatePicker, año, mes, día ->
                                fechaInicio = "$día/${mes + 1}/$año"
                            },
                            calendario.get(Calendar.YEAR),
                            calendario.get(Calendar.MONTH),
                            calendario.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }) {
                        Text(if (fechaInicio.isEmpty()) "Seleccionar Fecha de inicio" else "📅 $fechaInicio")
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    onGuardar(
                        Meta(
                            nombre = nombre,
                            montoObjetivo = monto.toDoubleOrNull() ?: 0.0,
                            plazoMeses = plazo.toIntOrNull() ?: 0,
                            fechaInicio = fechaInicio
                        )
                    )
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onCancelar() }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showDialog) {
        CrearMetaDialog(
            onGuardar = { nuevaMeta ->
                metas.add(nuevaMeta)
                showDialog = false
            },
            onCancelar = { showDialog = false }
        )
    }
}


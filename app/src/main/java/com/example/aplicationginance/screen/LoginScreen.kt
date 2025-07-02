package com.example.aplicationginance.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, onLoginSuccess: (String) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var frecuencia by remember { mutableStateOf("Mensual") }
    var monto by remember { mutableStateOf("") }

    val frecuencias = listOf("Mensual", "Quincenal", "Semanal")

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear usuario", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Clave") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = frecuencia,
                onValueChange = {},
                label = { Text("Frecuencia de ingreso") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                frecuencias.forEach {
                    DropdownMenuItem(text = { Text(it) }, onClick = {
                        frecuencia = it
                        expanded = false
                    })
                }
            }
        }

        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto de ingreso") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            onLoginSuccess(nombre)
            navController.navigate("inicio") {
                popUpTo("login") { inclusive = true }
            }
        }) {
            Text("Ingresar")
        }
    }
}

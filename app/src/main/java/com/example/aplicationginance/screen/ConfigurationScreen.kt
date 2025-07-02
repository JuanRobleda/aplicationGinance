import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("Usuario") }
    var email by remember { mutableStateOf("usuario@email.com") }
    var frecuencia by remember { mutableStateOf("Mensual") }
    var ingreso by remember { mutableStateOf("1200") }

    val frecuencias = listOf("Mensual", "Quincenal", "Semanal")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Configuración", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("👤 Nombre") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("📧 Email") })

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = frecuencia,
                onValueChange = {},
                readOnly = true,
                label = { Text("🔁 Frecuencia de ingreso") },
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

        OutlinedTextField(value = ingreso, onValueChange = { ingreso = it }, label = { Text("💰 Ingreso mensual") })

        Button(onClick = {
            // Aquí podrías guardar en DataStore o ViewModel más adelante
        }) {
            Text("💾 Guardar cambios")
        }
    }
}

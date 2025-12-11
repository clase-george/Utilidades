package es.fpsumma.dam2.utilidades.ui.screens.tareas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import es.fpsumma.dam2.utilidades.model.Asignatura
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditarAsignaturas(
    navController: NavController,
    vm: AsignaturasViewModel,
    modifier: Modifier = Modifier,
    id: Int
) {

    val asignaturaFlow = vm.getAsignaturaById(id)
    val asignaturas by asignaturaFlow.collectAsState(initial = null) //Convertimos de flow a state
    var alumno by remember(asignaturas) { mutableStateOf(asignaturas?.alumno?:"") }
    var asignatura by remember(asignaturas) { mutableStateOf(asignaturas?.asignatura?:"") }
    var trimestre by remember(asignaturas) { mutableStateOf(asignaturas?.trimestre?:"") }
    var nota by remember(asignaturas) { mutableStateOf(asignaturas?.nota?.toString()?:"") }

    Scaffold (
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector =Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                title = {Text("Informacion de la asignatura a editar")}
            )
        }
    ){padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
                Card(modifier = modifier.padding(16.dp)
                ){
                    Column(
                        modifier=modifier.padding(8.dp)
                    ) {
                        Text("${asignaturas?.alumno}")
                        HorizontalDivider(modifier=modifier.padding(vertical = 4.dp))
                        Row{
                            Text("Asignatura: ")
                            Text("${asignaturas?.asignatura}")
                        }
                        Row{
                            Text("Trimestre: ")
                            Text("${asignaturas?.trimestre}")
                        }
                        Row {
                            Text("Nota: ")
                            Text("${asignaturas?.nota}")
                        }
                    }
                }
                HorizontalDivider(modifier=modifier.padding(vertical = 4.dp, horizontal = 16.dp))
                Column {
                    OutlinedTextField(
                        value = alumno,
                        onValueChange = { alumno = it },
                        label = { Text("Alumno*") },
                        singleLine = true,
                        modifier = modifier.fillMaxWidth().padding(16.dp)
                    )
                    OutlinedTextField(
                        value = asignatura,
                        onValueChange = { asignatura = it },
                        label = { Text("Asignatura*") },
                        singleLine = true,
                        modifier = modifier.fillMaxWidth().padding(16.dp)
                    )
                    Text("Trimestre", modifier=modifier.padding(start=16.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        ) {
                        FilterChip(
                            selected = trimestre == "Primero",
                            onClick = { trimestre = "Primero" },
                            label = { Text("1er Trimestre") },
                        )
                        FilterChip(
                            selected = trimestre == "Segundo",
                            onClick = { trimestre = "Segundo" },
                            label = { Text("2o Trimestre") },
                        )
                        FilterChip(
                            selected = trimestre == "Tercero",
                            onClick = { trimestre = "Tercero" },
                            label = { Text("3er Trimestre") },
                        )
                    }
                    OutlinedTextField(
                        value = nota,
                        onValueChange = { nota = it },
                        label = { Text("Asignatura*") },
                        singleLine = true,
                        modifier = modifier.fillMaxWidth().padding(16.dp)
                    )

                    Button(
                        onClick = {
                            val asignaturaToUpdate = Asignatura( //creo un nuevo objeto asignatura
                                id = id,
                                alumno = alumno,
                                asignatura = asignatura,
                                trimestre = trimestre,
                                nota = nota.toDoubleOrNull()
                            )
                            vm.updateAsignatura(asignaturaToUpdate) //llamo a la funcion del vm para actualizar
                            navController.popBackStack()
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Guardar cambios")
                    }
                }
        }
    }


}
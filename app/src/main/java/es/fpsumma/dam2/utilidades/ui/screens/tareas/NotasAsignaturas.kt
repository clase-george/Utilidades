package es.fpsumma.dam2.utilidades.ui.screens.tareas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import es.fpsumma.dam2.utilidades.ui.navigation.Routes
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotasAsignaturas(
    navController: NavController,
    vm: AsignaturasViewModel,
    modifier: Modifier = Modifier
) {
    val asignaturas by vm.asignaturas.collectAsState()
    var alumno by remember { mutableStateOf("") }
    var asignatura by remember { mutableStateOf("") }
    var trimestre by remember { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Notas de Asignaturas")
                },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ){padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            Column (
                modifier = modifier.padding(horizontal = 16.dp , vertical = 4.dp)
            ){
                OutlinedTextField(
                    value = alumno,
                    onValueChange = {alumno = it},
                    label = {Text("Alumno*")},
                    singleLine = true,
                    modifier = modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = asignatura,
                    onValueChange = {asignatura = it},
                    label = {Text("Asignatura*")},
                    singleLine = true,
                    modifier = modifier.fillMaxWidth()
                )
                Text("Trimestre")
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    FilterChip(
                        selected = trimestre =="Primero",
                        onClick = { trimestre = "Primero"},
                        label = {Text("1er Trimestre")},


                    )
                    FilterChip(
                        selected = trimestre =="Segundo",
                        onClick = { trimestre = "Segundo"},
                        label = {Text("2o Trimestre")}
                    )
                    FilterChip(
                        selected = trimestre =="Tercero",
                        onClick = { trimestre = "Tercero"},
                        label = {Text("3er Trimestre")}
                    )
                }
                OutlinedTextField(
                    value = nota,
                    onValueChange = {nota = it},
                    label = {Text("Nota*")},
                    singleLine = true,
                    modifier = modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        vm.addAsignatura(
                            alumno = alumno,
                            asignatura = asignatura,
                            trimestre = trimestre,
                            nota = nota.toDoubleOrNull() ?: 0.0
                        )
                        navController.navigate(Routes.NOTAS_ASIGNATURAS)
                    },
                    modifier = modifier.fillMaxWidth().padding( vertical = 16.dp)
                ) {
                    Text("Añadir nota")
                }

                HorizontalDivider(modifier.padding(vertical = 16.dp))

                LazyColumn {
                    items(asignaturas, key={it.id}){ asignatura ->
                        Card (
                            modifier = modifier.fillMaxWidth().padding(6.dp),
                                    colors = CardDefaults.cardColors(
                                    containerColor =
                                        if ((asignatura.nota?:0.0) >=5.0 ){
                                            Color(0xFFD0F0C0) // Verde claro para notas aprobadas
                                        }else{
                                            Color(0xFFFFC0C0) // Rojo claro para notas no aprobadas
                                        }
                                    )
                        ){
                            Column(
                                modifier=modifier.padding(6.dp)
                            ){
                                Text("${asignatura.alumno}",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold)
                                HorizontalDivider(modifier.padding(vertical = 4.dp))
                                Row {
                                    Text("Asignatura: ",
                                        fontWeight = FontWeight.Bold)
                                    Text("${asignatura.asignatura}")
                                }
                                Row {
                                    Text("Trimestre: ",
                                        fontWeight = FontWeight.Bold)
                                    Text("${asignatura.trimestre}")
                                }
                                Row {
                                    Row {
                                        Text("Nota: ",
                                            fontWeight = FontWeight.Bold)
                                        Text("Nota: ${asignatura.nota}")
                                    }
                                    Row (
                                        modifier = modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ){
                                        IconButton(
                                            onClick = {
                                                navController.navigate(Routes.EDITAR_ASIGNATURAS + "/${asignatura.id}")
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Editar gasto",
                                                tint = Color.Blue,
                                            )
                                        }
                                        IconButton(

                                            onClick = {
                                                vm.deleteAsignatura(asignatura)
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Eliminar gasto",
                                                tint = Color.Red,
                                            )
                                        }
                                    }

                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
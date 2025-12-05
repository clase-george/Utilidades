package es.fpsumma.dam2.utilidades.ui.screens.tareas

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotasAsignaturas(
    navController: NavController,
    vm: AsignaturasViewModel,
    modifier: Modifier = Modifier
) {
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
    ){  }
}
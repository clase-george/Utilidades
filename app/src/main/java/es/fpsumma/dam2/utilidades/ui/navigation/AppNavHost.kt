package es.fpsumma.dam2.utilidades.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.fpsumma.dam2.utilidades.ui.screens.home.HomeScreen
import es.fpsumma.dam2.utilidades.ui.screens.tareas.ListadoTareasScreen
import es.fpsumma.dam2.utilidades.ui.screens.tareas.NotasAsignaturas
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturasViewModel
import es.fpsumma.dam2.utilidades.ui.viewmodel.TareasViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    tareasViewModel: TareasViewModel,
    asignaturasViewModel: AsignaturasViewModel
) {
    NavHost(navController = navController, startDestination = Routes.NOTAS_ASIGNATURAS) {
        composable(Routes.HOME) {
            HomeScreen(
                navController
            )
        }
        composable(Routes.LISTADO_TAREAS) {
            ListadoTareasScreen(
                navController,
                vm = tareasViewModel
            )
        }
        composable(Routes.NOTAS_ASIGNATURAS) {
            NotasAsignaturas(
                vm = asignaturasViewModel,
                navController = navController,
                modifier = Modifier
            )
        }
    }
}
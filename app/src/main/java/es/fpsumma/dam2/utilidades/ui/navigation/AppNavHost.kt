package es.fpsumma.dam2.utilidades.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import es.fpsumma.dam2.utilidades.ui.screens.home.HomeScreen
import es.fpsumma.dam2.utilidades.ui.screens.tareas.EditarAsignaturas
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
        composable (
            route = "editar_asignaturas/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){
            backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            EditarAsignaturas(
                vm = asignaturasViewModel,
                navController = navController,
                modifier = Modifier,
                id = id
            )
        }
    }
}
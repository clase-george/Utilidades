package es.fpsumma.dam2.utilidades.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import es.fpsumma.dam2.utilidades.data.local.AppDatabase
import es.fpsumma.dam2.utilidades.model.Asignatura
import es.fpsumma.dam2.utilidades.model.Tarea
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AsignaturasViewModel(app: Application) : AndroidViewModel(app) {

    private val db = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "asignaturas.db"
    ).fallbackToDestructiveMigration(false).build()

    private val dao = db.asignaturaDao()

    val asignaturas: StateFlow<List<Asignatura>> =
        dao.getAllAsignaturas().stateIn(
            viewModelScope,              // se ejecuta mientras exista el ViewModel
            SharingStarted.Lazily,       // empieza cuando alguien lo está viendo
            emptyList()                  // valor inicial (al principio no hay nada)
        )

    fun addAsignatura(
        alumno: String,
        asignatura: String,
        trimestre: String,
        nota: Double
    ) = viewModelScope.launch {
        dao.insertAsignatura(
            Asignatura(
                alumno = alumno,
                asignatura = asignatura,
                trimestre = trimestre,
                nota = nota
            )
        )
    }

    fun deleteAsignatura(asignatura: Asignatura) = viewModelScope.launch {
        dao.deleteAsignatura(asignatura)
    }

    fun updateAsignatura(asignatura: Asignatura) = viewModelScope.launch {
        dao.updateAsignatura(asignatura)
    }


}
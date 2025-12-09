package es.fpsumma.dam2.utilidades.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asignaturas")
data class Asignatura(
    @PrimaryKey(autoGenerate = true)
     val id: Int = 0,

    @ColumnInfo(name = "alumno")
     val alumno: String? = null,

    @ColumnInfo(name = "asignatura")
     val asignatura: String? = null,

    @ColumnInfo(name = "trimestre")
     val trimestre: String? = null,

    @ColumnInfo(name = "nota")
     val nota: Double? = null)

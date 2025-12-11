package es.fpsumma.dam2.utilidades.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import es.fpsumma.dam2.utilidades.model.Asignatura
import kotlinx.coroutines.flow.Flow

@Dao
interface AsignaturaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAsignatura(asignatura: Asignatura)

    @Delete
    suspend fun deleteAsignatura(asignatura: Asignatura)

    @Update
    suspend fun updateAsignatura(asignatura: Asignatura)

    @Query("SELECT * FROM asignaturas")
    fun getAllAsignaturas(): Flow<List<Asignatura>>

    @Query("SELECT * FROM asignaturas WHERE id = :id")
    fun getAsignaturaById(id: Int): Flow<Asignatura>

}
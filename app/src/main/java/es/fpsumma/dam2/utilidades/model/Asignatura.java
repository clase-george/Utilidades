package es.fpsumma.dam2.utilidades.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "asignaturas")
public class Asignatura {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "alumno")
    private String alumno;
    @ColumnInfo(name = "asignatura")
    private String asignatura;
    @ColumnInfo(name = "trimestre")
    private String trimestre;
    @ColumnInfo(name = "nota")
    private float nota;
}

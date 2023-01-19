package com.opapruebas.tasklist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "Task")
data class task (
    @ColumnInfo(name = "nombre") var nombre:String,
    @ColumnInfo(name = "descripcion") var descripcion:String,
    @ColumnInfo(name = "fechat") var fechat:Long = 0,
    @ColumnInfo(name = "progreso") var progreso: Int = 0
        ){
    @PrimaryKey (autoGenerate = true)
    var id:Int = 0
}

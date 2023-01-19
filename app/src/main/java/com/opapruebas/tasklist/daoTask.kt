package com.opapruebas.tasklist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Date

@Dao
interface daoTask {
    @Query("SELECT * FROM TASK")

    suspend fun getTask(): MutableList<task>

    @Insert
    suspend fun addTask(task: task)

    @Query("UPDATE task set nombre = :nombre, descripcion = :descripcion, fechat = :fechat, progreso = :progreso WHERE id = :id")
    suspend fun updateTask(id: Int, nombre:String, descripcion:String,fechat:Long, progreso:Int, )

    @Query("DELETE FROM task WHERE id= :id")
    suspend fun deleteTask(id:Int)

}

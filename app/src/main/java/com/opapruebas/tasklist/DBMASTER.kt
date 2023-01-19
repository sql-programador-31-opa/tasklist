package com.opapruebas.tasklist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [task::class],
    version = 1
)
abstract class DBMASTER:RoomDatabase() {
    abstract fun daoTask(): daoTask
}
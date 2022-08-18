package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
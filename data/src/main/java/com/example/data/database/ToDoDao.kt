package com.example.data.database

import androidx.room.*
import com.example.data.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDoEntity)

    @Delete
    suspend fun deleteToDo(toDo: ToDoEntity)

    @Query("SELECT * FROM todoentity ORDER BY toDoTitle ASC ")
    fun getAllToDos(): Flow<List<ToDoEntity>>
}

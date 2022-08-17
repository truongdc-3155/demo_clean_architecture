package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.base.EntityMapper
import com.example.data.base.ModelEntity
import com.example.domain.model.ToDo
import javax.inject.Inject

@Entity
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val toDoTitle: String
) : ModelEntity()

class ToDoEntityMapper @Inject constructor() : EntityMapper<ToDo, ToDoEntity> {
    override fun mapToDomain(entity: ToDoEntity) = ToDo(
        id = entity.id,
        toDoTitle = entity.toDoTitle
    )

    override fun mapToEntity(model: ToDo) = ToDoEntity(
        id = model.id,
        toDoTitle = model.toDoTitle
    )
}

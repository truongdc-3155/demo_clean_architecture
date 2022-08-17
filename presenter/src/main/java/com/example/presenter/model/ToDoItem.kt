package com.example.presenter.model

import com.example.domain.model.Model
import com.example.domain.model.ToDo
import com.example.presenter.base.ItemMapper
import com.example.presenter.base.ModelItem
import javax.inject.Inject

data class ToDoItem(
    val id: Int,
    val toDoTitle: String
): ModelItem()

open class ToDoItemMapper @Inject constructor() : ItemMapper<ToDo, ToDoItem> {
    override fun mapToPresenter(model: ToDo) = ToDoItem(
        id = model.id,
        toDoTitle = model.toDoTitle
    )

    override fun mapToDoMain(modelItem: ToDoItem) = ToDo(
        id = modelItem.id,
        toDoTitle = modelItem.toDoTitle
    )
}

package com.example.presenter.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.ToDoEntity
import com.example.domain.model.ToDo
import com.example.presenter.databinding.ItemTodoLayoutBinding
import com.example.presenter.model.ToDoItem


class TodoAdapter (
    val deleteTodo : ((ToDoItem) -> Unit)? = null
): RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(ItemTodoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = mTodo[position]

        holder.binding.apply {
            textView.text = currentToDo.toDoTitle
        }

        holder.binding.cbTodo.apply {
            setOnClickListener {
                holder.binding.apply {
                    if (isChecked) {
                        textView.paintFlags =
                            textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        textView.paintFlags =
                            textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
            }
        }
        holder.binding.imgDelete.apply {
            setOnClickListener {
                deleteTodo?.invoke(currentToDo)
            }
        }
    }

    override fun getItemCount() = mTodo.size

    private val differCallback = object : DiffUtil.ItemCallback<ToDoItem>() {

        override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    var mTodo: List<ToDoItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
    inner class ToDoViewHolder(val binding: ItemTodoLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}
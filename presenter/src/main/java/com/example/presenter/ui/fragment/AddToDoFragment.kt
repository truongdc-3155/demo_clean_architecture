package com.example.presenter.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.domain.model.ToDo
import com.example.presenter.R
import com.example.presenter.databinding.FragmentAddToDoBinding
import com.example.presenter.model.ToDoItem
import com.example.presenter.utils.showToast
import com.example.presenter.viewmodel.TodoViewModel
import com.truongdc21.koinv2.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment : BaseFragment<FragmentAddToDoBinding>(FragmentAddToDoBinding::inflate) {

    private val mViewModel: TodoViewModel by viewModels()

    override fun initView() {
        binding.btnAddTask.setOnClickListener { mView ->
            saveNote(mView)
        }

        binding.btnCancel.setOnClickListener {
            binding.etTodoName.text.clear()
            this@AddToDoFragment.findNavController().navigate(
                R.id.action_addToDoFragment_to_toDoListFragment
            )
        }
    }

    override fun initData() {

    }

    private fun saveNote(view: View) {
        val todoName = binding.etTodoName.text.toString()

        if (todoName.isNotEmpty()) {

            val todo = ToDoItem(0, todoName)

            mViewModel.insertTodo(todo)

            this@AddToDoFragment.context?.showToast("ToDo Saved Successfully")

            view.findNavController().navigate(
                R.id.action_addToDoFragment_to_toDoListFragment
            )
        } else {
            this@AddToDoFragment.context?.showToast("Todo title can not be empty")
        }
    }
}


package com.example.presenter.ui.fragment

import android.view.View
import android.widget.Toast
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
            saveNote()
        }
        binding.btnCancel.setOnClickListener {
            binding.etTodoName.text.clear()
            this@AddToDoFragment.findNavController().navigate(
                R.id.action_addToDoFragment_to_toDoListFragment
            )
        }
        saveToDoSuccess()
    }

    override fun initData() {
    }

    private fun saveNote() {
        val todoName = binding.etTodoName.text.toString()
        if (todoName.isNotEmpty()) {
            mViewModel.insertTodo(ToDoItem(0 , todoName))
        } else {
            this@AddToDoFragment.context?.showToast("Todo title can not be empty")
        }
    }

    private fun saveToDoSuccess() {
        mViewModel.insertObserver.observe(viewLifecycleOwner) {
            Toast.makeText(this@AddToDoFragment.context, "Add todo success!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_addToDoFragment_to_toDoListFragment
            )
        }
    }
}


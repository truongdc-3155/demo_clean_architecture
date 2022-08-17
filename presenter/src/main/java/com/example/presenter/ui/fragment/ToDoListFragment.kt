package com.example.presenter.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presenter.R
import com.example.presenter.adapter.TodoAdapter
import com.example.presenter.databinding.FragmentToDoListBinding
import com.example.presenter.model.ToDoItem
import com.example.presenter.utils.showToast
import com.example.presenter.viewmodel.TodoViewModel
import com.truongdc21.koinv2.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListFragment:
    BaseFragment<FragmentToDoListBinding> (FragmentToDoListBinding::inflate){

    private lateinit var todoAdapter  : TodoAdapter

    private val mViewModel : TodoViewModel by viewModels()

    override fun initView() {
        setUpRecyclerview()
        clickFabTask()
    }

    override fun initData() {
    }

    private fun setUpRecyclerview() {
        todoAdapter = TodoAdapter(deleteTodo = { data ->
            mViewModel.removeTodo(data)
            this@ToDoListFragment.context?.showToast("Delete Successsfully..")
        })

        binding.rvTodoList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
        }

        mViewModel.getAll().observe(viewLifecycleOwner) { listTodo ->
            updateUi(listTodo)
            todoAdapter.mTodo = listTodo
        }
    }

    private fun clickFabTask() {
        binding.fabAddTask.setOnClickListener {
            this@ToDoListFragment.findNavController().navigate(
                R.id.action_toDoListFragment_to_addToDoFragment
            )
        }
    }

    private fun updateUi(list: List<ToDoItem>) {
        if (list.isNotEmpty()) {
            binding.rvTodoList.visibility = View.VISIBLE
            binding.cardView.visibility = View.GONE
        } else {
            binding.rvTodoList.visibility = View.GONE
            binding.cardView.visibility = View.VISIBLE
        }
    }
}

package com.klaudjoshkurta.todo.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.klaudjoshkurta.todo.TodoApplication
import com.klaudjoshkurta.todo.ui.home.HomeViewModel
import com.klaudjoshkurta.todo.ui.todo.NewTodoViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Todo app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(
                todoApplication().container.todosRepository
            )
        }

        initializer {
            NewTodoViewModel(
                todoApplication().container.todosRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TodoApplication].
 */
fun CreationExtras.todoApplication(): TodoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)
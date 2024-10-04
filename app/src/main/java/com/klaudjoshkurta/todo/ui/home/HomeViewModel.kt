package com.klaudjoshkurta.todo.ui.home

import androidx.lifecycle.ViewModel
import com.klaudjoshkurta.todo.model.Todo

/**
 * ViewModel to retrieve all todos in the Room database.
 */
class HomeViewModel : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(
    val todos: List<Todo> = listOf(),
)
package com.klaudjoshkurta.todo.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudjoshkurta.todo.model.Todo
import com.klaudjoshkurta.todo.repository.TodosRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve all todos in the Room database.
 */
class HomeViewModel(
    savedStateHandle: SavedStateHandle,
    private val todosRepository: TodosRepository
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        todosRepository.getAllTodosStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    suspend fun completeTodo(todo: Todo) {
        todosRepository.updateTodo(todo.copy(isCompleted = !todo.isCompleted))
    }

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
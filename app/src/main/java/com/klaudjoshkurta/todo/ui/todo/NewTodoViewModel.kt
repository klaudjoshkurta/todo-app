package com.klaudjoshkurta.todo.ui.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.klaudjoshkurta.todo.model.Todo
import com.klaudjoshkurta.todo.repository.TodosRepository

/**
 * ViewModel to validate and insert todos in the Room database.
 */
class NewTodoViewModel(
    private val todosRepository: TodosRepository
) : ViewModel() {

    /**
     * Holds current todo ui state
     */
    var todoUiState by mutableStateOf(TodoUiState())
        private set

    /**
     * Updates the [todoUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(todoDetails: TodoDetails) {
        todoUiState =
            TodoUiState(todoDetails = todoDetails, isEntryValid = validateInput(todoDetails))
    }

    private fun validateInput(uiState: TodoDetails = todoUiState.todoDetails): Boolean {
        return with(uiState) {
            title.isNotBlank()
        }
    }

    suspend fun saveTodo() {
        if (validateInput()) {
            todosRepository.insertTodo(todoUiState.todoDetails.toTodo())
        }
    }
}

/**
 * Represents Ui State for a [Todo].
 */
data class TodoUiState(
    val todoDetails: TodoDetails = TodoDetails(),
    val isEntryValid: Boolean = false
)

data class TodoDetails(
    val id: Int = 0,
    val title: String = "",
    val isCompleted: Boolean = false,
)

/**
 * Extension function to convert [TodoDetails] to [Todo].
 */
fun TodoDetails.toTodo(): Todo = Todo(
    id = id,
    title = title,
    isCompleted = isCompleted
)

/**
 * Extension function to convert [Todo] to [TodoUiState]
 */
fun Todo.toTodoUiState(isEntryValid: Boolean = false): TodoUiState = TodoUiState(
    todoDetails = this.toTodoDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Todo] to [TodoDetails]
 */
fun Todo.toTodoDetails(): TodoDetails = TodoDetails(
    id = id,
    title = title,
    isCompleted = isCompleted
)
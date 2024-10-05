package com.klaudjoshkurta.todo.repository

import com.klaudjoshkurta.todo.model.Todo
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Todo] from a given data source.
 */
interface TodosRepository {
    /**
     * Retrieve all the todos from the the given data source.
     */
    fun getAllTodosStream(): Flow<List<Todo>>

    /**
     * Retrieve an todo from the given data source that matches with the [id].
     */
    fun getTodoStream(id: Int): Flow<Todo?>

    /**
     * Insert todo in the db
     */
    suspend fun insertTodo(todo: Todo)

    /**
     * Delete todo from the db
     */
    suspend fun deleteTodo(todo: Todo)

    /**
     * Update todo in the db
     */
    suspend fun updateTodo(todo: Todo)
}
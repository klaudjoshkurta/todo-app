package com.klaudjoshkurta.todo.repository

import com.klaudjoshkurta.todo.dao.TodoDao
import com.klaudjoshkurta.todo.model.Todo
import kotlinx.coroutines.flow.Flow

class OfflineTodosRepository(
    private val todoDao: TodoDao
) : TodosRepository {

    override fun getAllTodosStream(): Flow<List<Todo>> = todoDao.getAllTodos()

    override fun getTodoStream(id: Int): Flow<Todo?> = todoDao.getTodo(id)

    override suspend fun insertTodo(todo: Todo) = todoDao.insert(todo)

    override suspend fun deleteTodo(todo: Todo) = todoDao.delete(todo)

    override suspend fun updateTodo(todo: Todo) = todoDao.update(todo)
}
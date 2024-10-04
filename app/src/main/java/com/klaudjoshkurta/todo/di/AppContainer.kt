package com.klaudjoshkurta.todo.di

import android.content.Context
import com.klaudjoshkurta.todo.repository.OfflineTodosRepository
import com.klaudjoshkurta.todo.repository.TodosRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val todosRepository: TodosRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineTodosRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [TodosRepository]
     */
    override val todosRepository: TodosRepository by lazy {
        OfflineTodosRepository()
    }
}
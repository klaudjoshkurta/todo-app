package com.klaudjoshkurta.todo

import android.app.Application
import com.klaudjoshkurta.todo.di.AppContainer
import com.klaudjoshkurta.todo.di.AppDataContainer

class TodoApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
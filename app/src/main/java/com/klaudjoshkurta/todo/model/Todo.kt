package com.klaudjoshkurta.todo.model

data class Todo(
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean = false
)

val mockTodos = listOf(
    Todo(id = 1, title = "Meeting with Thomas"),
    Todo(id = 2, title = "Book table at Red Lobster"),
    Todo(id = 3, title = "Send report to Mike"),
    Todo(id = 4, title = "Respond to emails", isCompleted = true),
)

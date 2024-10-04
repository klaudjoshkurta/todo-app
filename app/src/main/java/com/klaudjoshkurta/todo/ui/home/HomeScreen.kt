package com.klaudjoshkurta.todo.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klaudjoshkurta.todo.R
import com.klaudjoshkurta.todo.model.Todo
import com.klaudjoshkurta.todo.model.mockTodos
import com.klaudjoshkurta.todo.ui.theme.TodoTheme

@Composable
fun HomeScreen(
    onNewTodo: () -> Unit = {},
) {
    Scaffold(
        topBar = { HomeTopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNewTodo,
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                item {
                    IntroBox(
                        modifier = Modifier.padding(16.dp).fillMaxWidth()
                    )
                }
                items(mockTodos, key = { it.id }) { todo ->
                    TodoItem(
                        todo = todo,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )
}

@Composable
fun IntroBox(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Good morning, Klaudjo.",
            fontSize = 24.sp,
            lineHeight = 38.sp,
            color = Color(0xFF27272B)
        )
        Text(
            text = "It's Friday, October 4",
            fontSize = 20.sp,
            color = Color(0xFFA0A5AB)
        )
    }
}

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    todo: Todo
) {

    val icon = if (todo.isCompleted) R.drawable.ic_check_circle_fill else R.drawable.ic_circle
    val textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else TextDecoration.None

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = if (todo.isCompleted) Color(0xFF27272B) else Color(0xFFA0A5AB)
        )
        Text(
            text = todo.title,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            textDecoration = textDecoration
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TodoTheme {
        HomeScreen()
    }
}
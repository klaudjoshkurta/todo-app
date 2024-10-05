package com.klaudjoshkurta.todo.ui.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.klaudjoshkurta.todo.R
import com.klaudjoshkurta.todo.ui.AppViewModelProvider
import com.klaudjoshkurta.todo.ui.theme.TodoTheme
import kotlinx.coroutines.launch

@Composable
fun NewTodoScreen(
    navigateUp: () -> Unit = {},
    navigateBack: () -> Unit = {},
    viewModel: NewTodoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = { NewTodoTopBar(onNavigateUp = navigateUp) },
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            TodoForm(
                todoUiState = viewModel.todoUiState,
                onValueChange = viewModel::updateUiState,
                focusRequester = focusRequester
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveTodo()
                            navigateBack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTodoTopBar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        actions = {
            IconButton(
                onClick = onNavigateUp
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )
}

@Composable
fun TodoForm(
    modifier: Modifier = Modifier,
    todoUiState: TodoUiState,
    onValueChange: (TodoDetails) -> Unit = {},
    focusRequester: FocusRequester,
) {
    Column(
        modifier = modifier
    ) {
        TodoInput(
            todoDetails = todoUiState.todoDetails,
            onValueChange = onValueChange,
            focusRequester = focusRequester
        )
    }
}

@Composable
fun TodoInput(
    modifier: Modifier = Modifier,
    todoDetails: TodoDetails,
    onValueChange: (TodoDetails) -> Unit = {},
    focusRequester: FocusRequester
) {
    BasicTextField(
        modifier = modifier
            .padding(16.dp)
            .focusRequester(focusRequester),
        value = todoDetails.title,
        onValueChange = { onValueChange(todoDetails.copy(title = it)) },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 22.sp
        ),
        decorationBox = { innerTextField ->
            if (todoDetails.title.isEmpty()) {
                Text(
                    text = "What do you need to do?",
                    color = Color(0xFFA0A5AB),
                    fontSize = 22.sp
                )
            }
            innerTextField()
        }
    )
}

@Preview
@Composable
fun NewTodoScreenPreview() {
    TodoTheme {
        NewTodoScreen()
    }
}
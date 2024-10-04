package com.klaudjoshkurta.todo.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.klaudjoshkurta.todo.ui.home.HomeScreen
import com.klaudjoshkurta.todo.ui.todo.NewTodoScreen

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object NewTodo: Screen("new_todo")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Home
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNewTodo = { navController.navigate(Screen.NewTodo.route) }
            )
        }

        composable(Screen.NewTodo.route) {
            NewTodoScreen(
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}
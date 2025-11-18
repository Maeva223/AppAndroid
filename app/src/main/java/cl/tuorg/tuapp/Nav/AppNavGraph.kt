package cl.tuorg.tuapp.Nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import cl.tuorg.tuapp.Screens.HomeScreen
import cl.tuorg.tuapp.Screens.LoginScreen
import cl.tuorg.tuapp.Screens.RegisterScreen
import cl.tuorg.tuapp.screens.*

@Composable
fun AppNavGraph() {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Route.Login.path
    ) {
        composable(Route.Login.path)    { LoginScreen(nav) }
        composable(Route.Register.path) { RegisterScreen(nav) }
        composable(Route.Home.path)     { HomeScreen() }
    }
}

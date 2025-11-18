package cl.tuorg.tuapp.Nav

sealed class Route(val path: String) {
    data object Login : Route("login")
    data object Register : Route("register")
    data object Home : Route("home")
}

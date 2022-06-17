package com.example.navigationcomponent

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Login : NavigationItem("login", R.drawable.login, "Login")
    object Logout : NavigationItem("logout", R.drawable.logout, "Logout")
}

sealed class Screen(var route: String){
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args : String) : String{
        return buildString {
            append(route)
            args.forEach {
                arg -> append("/$arg")
            }
        }
    }

}


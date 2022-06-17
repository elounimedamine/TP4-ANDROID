package com.example.navigationcomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationcomponent.ui.theme.NavigationComponentTheme
import java.security.KeyStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponentTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {//feha les roots mte3i
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){

        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
               navArgument("name"){
                 type = NavType.StringType
                 defaultValue = "User111"
                 nullable = true
            }
        )
        ){
            Entry -> DetailScreen(navController = navController, name = Entry.arguments?.getString("name"))
        }

    }
}

@Composable
fun MainScreen(navController: NavController) {

    val items = listOf(
        NavigationItem.Login,
    )

    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tapez votre nom",
            textAlign = TextAlign.Center,
            modifier = Modifier.width(150.dp),
            style = MaterialTheme.typography.subtitle2
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Login") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {navController.navigate(route = Screen.DetailScreen.withArgs(text))}) {
            Icon(
                painter = painterResource(id = R.drawable.login),
                contentDescription = null // decorative element
            )

            Text(" Login")
        }

    }
}

@Composable
fun DetailScreen(navController: NavController, name: String?){

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       Text(text = "Bienvenue $name")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {navController.navigate(route = Screen.MainScreen.route)}){
            Icon(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = null // decorative element
            )
            Text(" Logout")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationComponentTheme {
        Navigation()
    }
}
























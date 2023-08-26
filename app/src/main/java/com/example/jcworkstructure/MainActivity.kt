package com.example.jcworkstructure

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jcworkstructure.ui.theme.JcWorkStructureTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JcWorkStructureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageNavigation()
                }
            }
        }
    }
}

@Composable
fun PageNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_page"){
        composable("main_page"){
            MainPage(navController = navController)
        }
        composable("page_a/{person}", arguments = listOf(
                navArgument("person"){ type = NavType.StringType },
        )){
            val personJson = it.arguments?.getString("person")!!
            val personObj = Gson().fromJson(personJson,Person::class.java)

            PageA(navController = navController,personObj)
        }
        composable("page_b/{name}/{age}/{length}/{isSingle}",arguments = listOf(
            navArgument("name"){ type = NavType.StringType },
            navArgument("age"){ type = NavType.IntType },
            navArgument("length"){ type = NavType.FloatType },
            navArgument("isSingle"){ type = NavType.BoolType }
        )){
            val name = it.arguments?.getString("name")!!
            val age = it.arguments?.getInt("age")!!
            val length = it.arguments?.getFloat("length")!!
            val isSingle = it.arguments?.getBoolean("isSingle")!!

            PageB(navController = navController,name,age,length,isSingle)
        }
    }
}

@Composable
fun MainPage(navController: NavController) {

    val counter = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {

        Text(text = "Main Page", fontSize = 50.sp)

        Button(onClick = {
            val person = Person("Oguzhan",24,1.90f,true)
            val personJson = Gson().toJson(person)

            navController.navigate("page_a/$personJson")
        }) {
            Text(text = "Next Page")
        }

        Text(text = "Counter : ${counter.value}")

        Button(onClick = {
            counter.value++
        }) {
            Text(text = "Click")
        }
    }

    LaunchedEffect(key1 = true){
        Log.e("MainPage","LaunchedEffect works")
    }
    SideEffect {
        Log.e("MainPage","SideEffect works")
    }
    DisposableEffect(Unit){
        onDispose {
            Log.e("MainPage","DisposableEffect works")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JcWorkStructureTheme {

    }
}
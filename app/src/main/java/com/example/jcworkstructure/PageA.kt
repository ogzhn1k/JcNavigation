package com.example.jcworkstructure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PageA(navController: NavController,person: Person) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {

        Text(text = "Page A", fontSize = 50.sp)
        Text(text = person.name)
        Text(text = person.age.toString())
        Text(text = person.length.toString())
        Text(text = person.isSingle.toString())

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                navController.navigate("page_b/Mustafa/49/1.91/false"){
                    popUpTo("page_a"){inclusive = true}
                }
            },modifier = Modifier.weight(50f)) {
                Text(text = "Next Page")
            }

            Button(onClick = {
                navController.popBackStack()
            },modifier = Modifier.weight(50f)) {
                Text(text = "Previous Page")
            }
        }


    }
}
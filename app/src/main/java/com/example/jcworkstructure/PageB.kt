package com.example.jcworkstructure

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PageB(navController: NavController,name:String,age:Int,length:Float,isSingle:Boolean) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {

        Text(text = "Page B", fontSize = 50.sp)
        Text(text = name)
        Text(text = age.toString())
        Text(text = length.toString())
        Text(text = isSingle.toString())

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Previous Page")
        }
    }

    val activity = (LocalContext.current as Activity)
    BackHandler(onBack = {
        Log.e("back_button","Back button is triggered!")
        activity.finish()
    })
}
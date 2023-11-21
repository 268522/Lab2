package com.example.lab2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "A")
    {
        composable(route = "A") {
            screen1(navController)
        }
    }
}
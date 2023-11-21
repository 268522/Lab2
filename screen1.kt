package com.example.lab2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController


@Composable
fun screen1(navController: NavController) {
    val ctx = LocalContext.current
    val sensorManager: SensorManager =
        ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val lightSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    val sensorStatus = remember {
        mutableStateOf("")
    }
    val lightSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_LIGHT) {
                if (event.values[0] > 100f) {
                    sensorStatus.value = "bardzo jasno i ${event.values[0]} lx"
                }
                if (event.values[0] < 100f) {
                    sensorStatus.value = "jasno i ${event.values[0]} lx"
                }
                if(event.values[0] < 8f) {
                    sensorStatus.value = "ciemno i ${event.values[0]} lx"
                }
            }
        }
    }
    DisposableEffect(Unit) {
        sensorManager.registerListener(
            lightSensorEventListener,
            lightSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        onDispose {
            sensorManager.unregisterListener(lightSensorEventListener)
        }
    }
    Text(text = "Jest ${sensorStatus.value}")
}
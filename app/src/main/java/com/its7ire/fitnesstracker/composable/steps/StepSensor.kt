package com.its7ire.fitnesstracker.composable.steps

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class StepSensor(
    private val context: Context,
    private val onStepChanged: (Int) -> Unit
) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val stepSensor =
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    override fun onSensorChanged(event: SensorEvent?) {

        event ?: return

        val totalSteps = event.values[0].toInt()
        onStepChanged(totalSteps)
    }

    override fun onAccuracyChanged(
        sensor: Sensor?,
        accuracy: Int
    ) {

    }

    fun startListening() {

        if (stepSensor != null) {

            sensorManager.registerListener(
                this,
                stepSensor,
                SensorManager.SENSOR_DELAY_UI
            )

        }

    }
    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

}
package com.its7ire.fitnesstracker.data.bmidata

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "BMI_table")
data class BMI_Data(

    @PrimaryKey
    val id: Int = 1,
    val age: Int,
    val height: Float,
    val weight: Float,
    val bmi: Float
)
package com.its7ire.fitnesstracker.data.stepdata

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Steps")
data class StepsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ,
    val day: String,
    val timestamp: Long,
    val steps: Int
)

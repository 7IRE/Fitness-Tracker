package com.its7ire.fitnesstracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp


@Entity (tableName = "Steps")
data class StepsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ,
    val day: String,
    val timestamp: Long,
    val steps: Int
)

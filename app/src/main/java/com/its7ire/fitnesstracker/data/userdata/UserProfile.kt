package com.its7ire.fitnesstracker.data.userdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile_table")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val age: Int = 25,
    val weight: Float = 70f,
    val height: Float = 175f,
    val stepGoal: Int = 10000,
    val isLoggedIn: Boolean = true
)

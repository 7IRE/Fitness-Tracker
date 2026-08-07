package com.its7ire.fitnesstracker.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: StepDatabase? = null

    fun getDatabase(context: Context): StepDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                StepDatabase::class.java,
                "steps_database"
            ).build()

            INSTANCE = instance

            instance
        }
    }
}
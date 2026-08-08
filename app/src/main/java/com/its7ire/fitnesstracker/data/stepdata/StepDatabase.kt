package com.its7ire.fitnesstracker.data.stepdata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StepsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StepDatabase : RoomDatabase() {

    abstract fun stepDao(): StepDao
}
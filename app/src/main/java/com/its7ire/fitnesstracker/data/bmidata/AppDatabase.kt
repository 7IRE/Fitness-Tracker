package com.its7ire.fitnesstracker.data.bmidata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.its7ire.fitnesstracker.data.settings.AppSettings
import com.its7ire.fitnesstracker.data.settings.SettingsDao
import com.its7ire.fitnesstracker.data.userdata.UserDao
import com.its7ire.fitnesstracker.data.userdata.UserProfile

@Database(
    entities = [BMI_Data::class, UserProfile::class, AppSettings::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bmiDao(): BMIDao
    abstract fun userDao(): UserDao
    abstract fun settingsDao(): SettingsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
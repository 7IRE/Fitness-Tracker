package com.its7ire.fitnesstracker.bmidata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BMI_Data::class],
    version = 1
)

abstract class BMIDataBase: RoomDatabase(){


    abstract fun bmiDao(): BMIDao


    companion object{


        @Volatile
        private var INSTANCE: BMIDataBase? = null


        fun getDatabase(context: Context): BMIDataBase{


            return INSTANCE ?: synchronized(this){


                val instance = Room.databaseBuilder(

                    context.applicationContext,

                    BMIDataBase::class.java,

                    "bmi_database"

                ).build()


                INSTANCE = instance

                instance

            }


        }

    }

}
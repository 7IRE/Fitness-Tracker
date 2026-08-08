package com.its7ire.fitnesstracker.data.bmidata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BMIDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBMI(bmi: BMI_Data)


    @Query("SELECT * FROM BMI_table WHERE id = 1")
    suspend fun getBMI(): BMI_Data?
}
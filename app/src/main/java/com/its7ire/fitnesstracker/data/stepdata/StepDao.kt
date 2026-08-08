package com.its7ire.fitnesstracker.data.stepdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StepDao {

    @Insert
    suspend fun insert(update: StepsEntity)

    @Query("SELECT * FROM Steps ORDER BY timestamp DESC")
    fun getAllUpdates(): Flow<List<StepsEntity>>
}

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

    @Query("SELECT * FROM Steps ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastStep(): StepsEntity?

    @Query("""
        SELECT * FROM Steps
        WHERE day = :day
        LIMIT 1
    """)
    suspend fun getStepsForDay(day: String): StepsEntity?

    @Query("""
        UPDATE Steps
        SET steps = :steps,
            timestamp = :timestamp
        WHERE id = :id
    """)
    suspend fun updateSteps(
        id: Int,
        steps: Int,
        timestamp: Long
    )

    @Query("""
        SELECT * FROM Steps
        WHERE timestamp BETWEEN :start AND :end
        ORDER BY timestamp ASC
    """)
    fun getStepsForWeek(
        start: Long,
        end: Long
    ): Flow<List<StepsEntity>>
}
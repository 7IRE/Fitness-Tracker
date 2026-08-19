package com.its7ire.fitnesstracker.data.stepdata

import kotlinx.coroutines.flow.Flow

class StepRepository(
    private val dao: StepDao
) {

    val history = dao.getAllUpdates()

    suspend fun save(update: StepsEntity) {
        dao.insert(update)
    }

    suspend fun getLastStep(): StepsEntity? {
        return dao.getLastStep()
    }

    suspend fun getStepForDay(day: String): StepsEntity? {
        return dao.getStepForDay(day)
    }

    suspend fun updateSteps(id: Int, steps: Int) {
        dao.updateSteps(
            id = id,
            steps = steps,
            timestamp = System.currentTimeMillis()
        )
    }

    fun getWeeklySteps(
        start: Long,
        end: Long
    ): Flow<List<StepsEntity>> {
        return dao.getStepsForWeek(start, end)
    }
}
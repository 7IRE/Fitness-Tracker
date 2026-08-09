package com.its7ire.fitnesstracker.data.stepdata

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
}
package com.its7ire.fitnesstracker.data.userdata

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val activeUserFlow: Flow<UserProfile?> = userDao.getActiveUserFlow()

    suspend fun saveUser(user: UserProfile) {
        userDao.logoutAll()
        userDao.insertOrUpdateUser(user.copy(isLoggedIn = true))
    }

    suspend fun getActiveUser(): UserProfile? {
        return userDao.getActiveUser()
    }

    suspend fun login(email: String, pass: String): UserProfile? {
        val user = userDao.getUserByCredentials(email.trim(), pass.trim())
        if (user != null) {
            userDao.logoutAll()
            userDao.setLoggedIn(user.id)
            return user.copy(isLoggedIn = true)
        }
        return null
    }

    suspend fun logout() {
        userDao.logoutAll()
    }

    suspend fun updateStepGoal(userId: Int, newGoal: Int) {
        userDao.updateStepGoal(userId, newGoal)
    }

    suspend fun updateUser(user: UserProfile) {
        userDao.updateUser(user)
    }

    suspend fun hasAnyUser(): Boolean {
        return userDao.getUserCount() > 0
    }
}

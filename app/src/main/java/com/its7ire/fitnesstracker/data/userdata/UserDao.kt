package com.its7ire.fitnesstracker.data.userdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: UserProfile): Long

    @Update
    suspend fun updateUser(user: UserProfile)

    @Query("SELECT * FROM user_profile_table WHERE isLoggedIn = 1 LIMIT 1")
    fun getActiveUserFlow(): Flow<UserProfile?>

    @Query("SELECT * FROM user_profile_table WHERE isLoggedIn = 1 LIMIT 1")
    suspend fun getActiveUser(): UserProfile?

    @Query("SELECT * FROM user_profile_table WHERE LOWER(TRIM(email)) = LOWER(TRIM(:email)) AND password = :password LIMIT 1")
    suspend fun getUserByCredentials(email: String, password: String): UserProfile?

    @Query("UPDATE user_profile_table SET isLoggedIn = 0")
    suspend fun logoutAll()

    @Query("UPDATE user_profile_table SET isLoggedIn = 1 WHERE id = :userId")
    suspend fun setLoggedIn(userId: Int)

    @Query("UPDATE user_profile_table SET stepGoal = :newGoal WHERE id = :userId")
    suspend fun updateStepGoal(userId: Int, newGoal: Int)

    @Query("SELECT COUNT(*) FROM user_profile_table")
    suspend fun getUserCount(): Int
}

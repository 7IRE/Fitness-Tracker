package com.its7ire.fitnesstracker.data.settings

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "app_settings")
data class AppSettings(
    @PrimaryKey
    val id: Int = 1,
    val themeIndex: Int = 1
)

@Dao
interface SettingsDao {

    @Query("SELECT themeIndex FROM app_settings WHERE id = 1 LIMIT 1")
    fun getThemeIndexFlow(): Flow<Int?>

    @Query("SELECT * FROM app_settings WHERE id = 1 LIMIT 1")
    suspend fun getSettings(): AppSettings?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSettings(settings: AppSettings)
}

package com.its7ire.fitnesstracker.data.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeRepository(private val settingsDao: SettingsDao) {

    val themeIndexFlow: Flow<Int> = settingsDao.getThemeIndexFlow()
        .map { it ?: 1 }

    suspend fun getThemeIndex(): Int {
        return settingsDao.getSettings()?.themeIndex ?: 1
    }

    suspend fun saveThemeIndex(index: Int) {
        settingsDao.saveSettings(AppSettings(id = 1, themeIndex = index))
    }

    suspend fun cycleNextTheme(): Int {
        val current = getThemeIndex()
        val next = (current + 1) % 10
        saveThemeIndex(next)
        return next
    }
}

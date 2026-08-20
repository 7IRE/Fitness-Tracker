package com.its7ire.fitnesstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.its7ire.fitnesstracker.data.bmidata.AppDatabase
import com.its7ire.fitnesstracker.data.settings.ThemeRepository
import com.its7ire.fitnesstracker.navigation.FitnessApp
import com.its7ire.fitnesstracker.ui.theme.AppTheme
import com.its7ire.fitnesstracker.viewmodel.ThemeViewModel
import com.its7ire.fitnesstracker.viewmodel.ThemeViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(applicationContext)
        val themeRepository = ThemeRepository(database.settingsDao())

        setContent {
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModelFactory(themeRepository)
            )
            val themeIndex by themeViewModel.themeIndex.collectAsStateWithLifecycle()

            WindowCompat.getInsetsController(window, window.decorView).apply {
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(WindowInsetsCompat.Type.navigationBars())
            }

            AppTheme(value = themeIndex) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FitnessApp(onThemeChanged = { themeViewModel.changeTheme() })
                }
            }
        }
    }
}

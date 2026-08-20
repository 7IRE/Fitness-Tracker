package com.its7ire.fitnesstracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.userdata.UserProfile
import com.its7ire.fitnesstracker.data.userdata.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _isSessionReady = MutableStateFlow(false)
    val isSessionReady: StateFlow<Boolean> = _isSessionReady.asStateFlow()

    private val _initialRoute = MutableStateFlow<String?>(null)
    val initialRoute: StateFlow<String?> = _initialRoute.asStateFlow()

    val user: StateFlow<UserProfile?> = repository.activeUserFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    var tempName = mutableStateOf("")
    var tempEmail = mutableStateOf("")
    var tempPassword = mutableStateOf("")

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val activeUser = repository.getActiveUser()
            if (activeUser != null && activeUser.isLoggedIn) {
                _initialRoute.value = "home"
            } else if (repository.hasAnyUser()) {
                _initialRoute.value = "login"
            } else {
                _initialRoute.value = "signup"
            }
            _isSessionReady.value = true
        }
    }

    fun setSignUpDetails(name: String, email: String, pass: String) {
        tempName.value = name
        tempEmail.value = email
        tempPassword.value = pass
    }

    fun completeProfileSetup(
        ageStr: String,
        weightStr: String,
        heightStr: String,
        stepGoalStr: String,
        onSuccess: () -> Unit
    ) {
        val age = ageStr.toIntOrNull() ?: 25
        val weight = weightStr.toFloatOrNull() ?: 70f
        val height = heightStr.toFloatOrNull() ?: 175f
        val stepGoal = stepGoalStr.toIntOrNull() ?: 10000

        viewModelScope.launch {
            val newUser = UserProfile(
                name = tempName.value.ifBlank { "User" },
                email = tempEmail.value.ifBlank { "user@example.com" },
                password = tempPassword.value,
                age = age,
                weight = weight,
                height = height,
                stepGoal = stepGoal,
                isLoggedIn = true
            )
            repository.saveUser(newUser)
            onSuccess()
        }
    }

    fun login(email: String, pass: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loginError.value = null
            val loggedInUser = repository.login(email, pass)
            if (loggedInUser != null) {
                onSuccess()
            } else {
                _loginError.value = "Invalid email or password"
            }
        }
    }

    fun clearLoginError() {
        _loginError.value = null
    }

    fun updateUser(updatedUser: UserProfile, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            repository.updateUser(updatedUser)
            onSuccess()
        }
    }

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            repository.logout()
            onLoggedOut()
        }
    }
}

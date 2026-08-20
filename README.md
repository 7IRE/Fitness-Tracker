# 🏃 FitPulse - Fitness Tracker

A modern, sleek, and intuitive Android Fitness Tracker application built natively with **Jetpack Compose**, **Material 3**, and **Room Database**.

---

## 🚀 Project Status: **Completed** 🟢

All core modules, on-device database persistence, authentication flows, Gemini AI coaching, and UI features have been fully implemented and verified.

| Module                             |      Status      | Description                                                                                                     |
|:-----------------------------------|:----------------:|:----------------------------------------------------------------------------------------------------------------|
| **UI Design & Layouts**            | 🟢 **Completed** | Full Material 3 Compose interface with dynamic palettes, animations, and custom progress arcs.                  |
| **Authentication & Profile Setup** | 🟢 **Completed** | Sign Up, Profile Setup, Login validation, session persistence, and editable User Info in Room DB.               |
| **Navigation**                     | 🟢 **Completed** | Jetpack Navigation Compose with session-aware start destination and floating bottom navigation bar.             |
| **MVVM Architecture**              | 🟢 **Completed** | Unidirectional Data Flow (UDF) with Kotlin StateFlow, Coroutines, and ViewModel Factories.                      |
| **Room Database Persistence**      | 🟢 **Completed** | Complete offline SQLite storage for user profiles, credentials, step logs, BMI records, and app theme settings. |
| **Hardware Step Tracking**         | 🟢 **Completed** | Real-time step counter sensor integration (`Sensor.TYPE_STEP_COUNTER`) with persistent step logging.            |
| **Gemini AI Coaching**             | 🟢 **Completed** | AI-driven fitness chat assistant with workout recommendations, diet tips, and quick suggestion chips.           |
| **Data & Privacy**                 | 🟢 **Completed** | 100% on-device architecture with zero cloud tracking and dedicated privacy breakdown screen.                    |

---

## ✨ Features

* **🔐 Authentication & User Onboarding:**
  * **Sign Up Screen:** Account creation with email/password validation.
  * **Profile Setup:** Collects age, weight, height, and custom daily step goals on first launch.
  * **Login Screen:** Authenticates against encrypted local Room DB credentials.
  * **Session Persistence:** Automatically restores logged-in session on app restarts.

* **🏠 Home Dashboard:**
  * Real-time step counter with dual animated progress arcs.
  * Dynamic daily step goal tracking based on user profile.
  * Calorie and distance calculations derived from user weight and height.
  * Interactive BMI metric card with custom status categories.

* **🤖 AI Fitness Coach:**
  * Powered by Google Gemini AI SDK.
  * Context-aware personalized greetings and fitness advice.
  * Interactive prompt suggestion chips and chat conversation history.

* **📊 History & Analytics:**
  * Weekly step statistics and daily averages.
  * Interactive 7-day bar chart with highlighting for the active day.
  * Chronological daily activity log with goal achievement badges (filtered to exclude future dates).
  * "Load Previous Week" pagination to browse past workout history.

* **👤 Profile & Preferences:**
  * **User Info:** Edit and update name, email, credentials, and physical metrics directly to Room DB.
  * **Appearance:** 10 dynamic Material 3 color themes (Light, Dark, High Contrast, Cyan, Pink, Red, Grey) stored in Room DB and updated instantly.
  * **Data & Privacy:** Dedicated transparency screen explaining local SQLite storage and zero cloud data sharing.
  * **Secure Logout:** Ends the active session and navigates back to the Login screen.

---

## 🛠️ Tech Stack & Architecture

* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Declarative UI)
* **Design System:** [Material 3](https://m3.material.io/) with Dynamic Color Palettes
* **Language:** Kotlin
* **Architecture:** MVVM (Model-View-ViewModel) + Repository Pattern
* **Local Storage:** [Room Database](https://developer.android.com/training/data-storage/room) (SQLite) with Coroutines Flow
* **Sensors:** Android Hardware Step Counter (`SensorManager`)
* **AI Engine:** Google Gemini AI API

---

## 🔐 Configuration & API Keys

The Gemini AI coach integration uses an API key stored in `local.properties` (excluded from version control):

```properties
GEMINI_API_KEY=your_api_key_here
```

The application accesses this key safely at build/run time through `BuildConfig.GEMINI_API_KEY`.

---

## 📂 Project Structure

```text
com.its7ire.fitnesstracker/
│
├── api.coach/
│   ├── GeminiApi.kt
│   ├── GeminiModels.kt
│   ├── GeminiRepo.kt
│   └── GeminiRetrofit.kt
│
├── composable/
│   ├── coach/
│   │   ├── ChatInputBar.kt
│   │   ├── CoachChatMessageData.kt
│   │   ├── CoachGreetingCard.kt
│   │   ├── CoachMessageBubble.kt
│   │   ├── CoachTopBar.kt
│   │   ├── EmptyState.kt
│   │   ├── ProgressCard.kt
│   │   ├── SuggestionChips.kt
│   │   ├── TypingIndicator.kt
│   │   └── UserMessageBubble.kt
│   │
│   ├── history/
│   │   ├── HistoryAvgCard.kt
│   │   ├── HistoryDailyLogCard.kt
│   │   ├── HistoryPrevWeekButton.kt
│   │   ├── HistoryStepSection.kt
│   │   └── HistoryTopBar.kt
│   │
│   ├── home/
│   │   ├── bmi/
│   │   │   ├── BMI_Top_Bar.kt
│   │   │   ├── BmiHeightField.kt
│   │   │   ├── BmiWeightField.kt
│   │   │   ├── CalculateBmi.kt
│   │   │   └── CalculateButton.kt
│   │   │
│   │   ├── calories/
│   │   │   └── CalorieCalculator.kt
│   │   │
│   │   ├── HomeBMICard.kt
│   │   ├── HomeCaloriesCard.kt
│   │   ├── HomeCreateWorkoutButton.kt
│   │   ├── HomeStatCard.kt
│   │   ├── HomeStepCard.kt
│   │   ├── HomeTopBar.kt
│   │   └── ProgressArc.kt
│   │
│   ├── profile/
│   │   ├── LogoutButton.kt
│   │   ├── ProfileInformation.kt
│   │   ├── ProfilePrefItem.kt
│   │   ├── ProfilePrefSection.kt
│   │   ├── ProfileStatCard.kt
│   │   ├── ProfileStatSection.kt
│   │   └── ProfileTopBar.kt
│   │
│   └── steps/
│       └── StepSensor.kt
│
├── data/
│   ├── bmidata/
│   │   ├── AppDatabase.kt
│   │   ├── BMIDao.kt
│   │   ├── BMIEntity.kt
│   │   └── BMIRepository.kt
│   │
│   ├── settings/
│   │   ├── AppSettings.kt
│   │   └── ThemeRepository.kt
│   │
│   ├── stepdata/
│   │   ├── DatabaseProvider.kt
│   │   ├── StepDao.kt
│   │   ├── StepDatabase.kt
│   │   ├── StepRepository.kt
│   │   └── StepsEntity.kt
│   │
│   └── userdata/
│       ├── UserDao.kt
│       ├── UserProfile.kt
│       └── UserRepository.kt
│
├── navigation/
│   └── NavHost.kt
│
├── screen/
│   ├── login/
│   │   ├── LoginScreen.kt
│   │   ├── ProfileSetupScreen.kt
│   │   └── SignUpScreen.kt
│   ├── BMIScreen.kt
│   ├── CoachScreen.kt
│   ├── DataPrivacyScreen.kt
│   ├── HistoryScreen.kt
│   ├── HomeScreen.kt
│   ├── ProfileScreen.kt
│   └── UserInfoScreen.kt
│
├── ui.theme/
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
├── viewmodel/
│   ├── BmiViewModel.kt
│   ├── BmiViewModelFactory.kt
│   ├── CoachViewModel.kt
│   ├── HistoryViewModel.kt
│   ├── HistoryViewModelFactory.kt
│   ├── StepViewModel.kt
│   ├── StepViewModelFactory.kt
│   ├── ThemeViewModel.kt
│   ├── ThemeViewModelFactory.kt
│   ├── UserViewModel.kt
│   └── UserViewModelFactory.kt
│
├── MainActivity.kt
└── Utils.kt
```

#  Fitness Tracker

> ⚠️ **WORK IN PROGRESS:** This project is currently under active development. At this stage, the UI design layer is implemented with mock data, while backend persistence, architecture, and AI integrations are actively being built.

A modern, sleek, and intuitive Android Fitness Tracker application built natively with **Jetpack Compose** and **Material 3**.

---

## 🚧 Development Status

| Module |      Status       | Description |
| :--- |:-----------------:| :--- |
| **UI Design & Layouts** | 🟢  **Completed** | Single page UI screens completed for Home, History, and Profile. Refactoring to Material 3 design tokens underway. |
| **Navigation** |   🟢  **Completed**   | Bottom Navigation Bar & Jetpack NavGraph implementation. |
| **MVVM Architecture** |  🟢  **Completed**   | ViewModels, StateFlow, and UI state management (Unidirectional Data Flow). |
| **Room Database** |  🔴 **Planned**   | Local offline persistence for step logs, workouts, and user streak data. |
| **Gemini AI Integration** |  🟢  **Completed**   | AI-driven fitness insights, diet suggestions, and custom workout plans. |

---

##  Features & Completed UI Screens (Mockups)

* **🏠 Home Screen:** Daily step progress ring, active minutes, calories burned cards, and quick workout creation button.
* **📊 History Screen:** Weekly step averages, interactive bar chart, daily activity logs, and week navigation controls.
* **👤 Profile Screen:** User membership status, streak counters, volume stats, preference options, and logout button.
* **🎨 Material 3 Theme:** Custom Light and Dark mode palette configuration.

---

## 🛠️ Tech Stack & Planned Libraries

* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
* **Design System:** [Material 3](https://m3.material.io/)
* **Language:** Kotlin
* **Local Storage :** Room Database
* **Architecture :** MVVM + Repository Pattern
* **AI Engine :** Google Gemini AI SDK

#🔐 **API Key Configuration**

    The Gemini API key is stored in local.properties and is excluded from version control.
        GEMINI_API_KEY=your_api_key_here
    The application accesses the key through BuildConfig.GEMINI_API_KEY.
    Each developer should use their own Gemini API key in their local local.properties file.

---

## 📂 Actual Project Structure

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
│   └── home/
│       ├── bmi/
│       │   ├── BMI_Top_Bar.kt
│       │   ├── BmiHeightField.kt
│       │   ├── BmiWeightField.kt
│       │   ├── CalculateBmi.kt
│       │   └── CalculateButton.kt
│       │
│       ├── calories/
│       │   └── CalorieCalculator.kt
│       │
│       ├── HomeBMICard.kt
│       ├── HomeCaloriesCard.kt
│       ├── HomeCreateWorkoutButton.kt
│       ├── HomeStatCard.kt
│       ├── HomeStepCard.kt
│       ├── HomeTopBar.kt
│       └── ProgressArc.kt
│
│   ├── profile/
│   │   ├── LogoutButton.kt
│   │   ├── ProfileInformation.kt
│   │   ├── ProfilePrefItem.kt
│   │   ├── ProfilePrefSection.kt
│   │   ├── ProfileStatCard.kt
│   │   ├── ProfileStatSection.kt
│   │   └── ProfileTopBar.kt
│   │
│   ├── stepgoal/
│   │   └── StepGoalDropDown.kt
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
│   └── stepdata/
│       ├── DatabaseProvider.kt
│       ├── StepDao.kt
│       ├── StepDatabase.kt
│       ├── StepRepository.kt
│       └── StepsEntity.kt
│
├── navigation/
│   └── NavHost.kt
│
├── screen/
│   ├── BMIScreen.kt
│   ├── CoachScreen.kt
│   ├── HistoryScreen.kt
│   ├── HomeScreen.kt
│   ├── ProfileScreen.kt
│   └── StepGoalScreen.kt
│
├── ui.theme/
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
├── viewmodel/
│   ├── BMIViewModel.kt
│   ├── BMIViewModelFactory.kt
│   ├── CoachViewModel.kt
│   ├── StepsHistory/
│   │   └── ViewModel.kt
│   ├── StepViewModel.kt
│   └── StepViewModelFactory.kt
│
├── MainActivity.kt
└── Utils.kt


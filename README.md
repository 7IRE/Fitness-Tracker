#  Fitness Tracker

> ⚠️ **WORK IN PROGRESS:** This project is currently under active development. At this stage, the UI design layer is implemented with mock data, while backend persistence, architecture, and AI integrations are actively being built.

A modern, sleek, and intuitive Android Fitness Tracker application built natively with **Jetpack Compose** and **Material 3**.

---

## 🚧 Development Status

| Module |      Status       | Description |
| :--- |:-----------------:| :--- |
| **UI Design & Layouts** | 🟢  **Completed** | Single page UI screens completed for Home, History, and Profile. Refactoring to Material 3 design tokens underway. |
| **Navigation** |   🟢  **Completed**   | Bottom Navigation Bar & Jetpack NavGraph implementation. |
| **MVVM Architecture** |  🔴 **Planned**   | ViewModels, StateFlow, and UI state management (Unidirectional Data Flow). |
| **Room Database** |  🔴 **Planned**   | Local offline persistence for step logs, workouts, and user streak data. |
| **Gemini AI Integration** |  🔴 **Planned**   | AI-driven fitness insights, diet suggestions, and custom workout plans. |

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
* **Local Storage (Planned):** Room Database
* **Architecture (Planned):** MVVM + Repository Pattern
* **AI Engine (Planned):** Google Gemini AI SDK

---

## 📂 Actual Project Structure

```text
com.its7ire.fitnesstracker/
│
├── composable/                 # UI Reusable Components
│   ├── history/
│   │   ├── HistoryAvgCard.kt
│   │   ├── HistoryDailyLogCard.kt
│   │   ├── HistoryPrevWeekButton.kt
│   │   ├── HistoryStepSection.kt
│   │   └── HistoryTopBar.kt
│   │
│   ├── home/
│   │   ├── HomeCaloriesCard.kt
│   │   ├── HomeCreateWorkoutButton.kt
│   │   ├── HomeExerciseCard.kt
│   │   ├── HomeStatCard.kt
│   │   ├── HomeStepCard.kt
│   │   └── HomeTopBar.kt
│   │
│   └── profile/
│       ├── LogoutButton.kt
│       ├── ProfileInformation.kt
│       ├── ProfilePrefItem.kt
│       ├── ProfilePrefSection.kt
│       ├── ProfileStatCard.kt
│       ├── ProfileStatSection.kt
│       └── ProfileTopBar.kt
│
├── screen/                     # Full Screen Composables
│   ├── HistoryScreen.kt
│   ├── HomeScreen.kt
│   └── ProfileScreen.kt
│
├── ui.theme/                   # Design Tokens & Theme Setup
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
├── data/                       # [Planned] Room DB, DAOs, & Repositories
│   ├── local/
│   ├── remote/
│   └── repository/
│
├── viewmodel/                  # [Planned] ViewModels & UI States
│
└── MainActivity.kt

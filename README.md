Sereniview – Serenity in Every Answer

A mental-wellness companion for interview confidence

⸻

🌟 Overview

Sereniview is a mobile application designed to support students and job seekers who experience interview anxiety, overthinking, self-doubt, or fear of failure.
The app provides grounding exercises, supportive guidance, breathing techniques, and quick mental warm-ups to help users feel calmer and more confident before interviews.

This project was built as a capstone submission using Kotlin, Jetpack Compose, and a fully functional Room database, with a clean UI and user-friendly design.

⸻

🎯 Features

🔐 Login Screen
•	Clean interface with logo
•	Email & Password fields
•	“Continue as Guest” mode

⸻

🏠 Home Screen

Modern card layout navigation to all wellness tools:
•	Calm Chat
•	Ground Me
•	Ground Me History
•	Breathing Exercise
•	Daily Affirmations
•	Interview Tip of the Day
•	Mini Interview Warmup

⸻

💬 Calm Chat (Supportive Chat Assistant)

A guided emotional reflection chat that responds supportively to:
•	Anxiety
•	Confidence issues
•	English/communication worries
•	Overthinking
•	Fear of rejection
•	Blanking out
•	Preparation questions
•	General emotional messages

Also includes 50 rotating interview wellness tips inside responses.

⸻

🌱 Ground Me (Cognitive Reframing Exercise)

Users type a worry (ex: “I feel scared I will blank out.”)
App provides a calm, supportive reframing response.

Includes:
•	Beautiful animated bubble UI
•	Save worry + response to database
•	Tracks emotional patterns over time

⸻

📚 Ground Me History (Room Database)

Stores:
•	worry
•	calming response
•	timestamp

Users can:
•	View list of all saved entries
•	Delete individual items
•	Clear entire history

This fulfills the backend + storage requirement.

⸻

🌬️ Breathing Exercise (4-7-8 Technique)
•	Animated breathing circle
•	Smooth transitions
•	Helps regulate anxiety before interviews

⸻

🌞 Daily Affirmation Generator

Tap to receive a new confidence-boosting affirmation:
•	“You are prepared.”
•	“Your voice matters.”
•	etc.

⸻

💡 Interview Tip of the Day

One short tip at a time pulled from a curated list of 50 tips.

⸻

🔥 Mini Interview Warmup

A fast mental warm-up where users select an emotion:
•	Not confident
•	Overthinking
•	Rejection fear
•	English worry
•	Social anxiety
•	Tired
•	Comparison stress
•	Imposter syndrome

App gives tailored guidance based on chosen emotion.

UI Layer (Jetpack Compose Screens)
↓
Navigation Layer (NavGraph)
↓
ViewModel Layer (GroundEntryViewModel)
↓
Data Layer (Room Database)
↓
Local Storage (GroundEntry, GroundDao, SereniviewDatabase)

🛠️ Tech Stack
•	Kotlin
•	Jetpack Compose (Material3)
•	Android Navigation Compose
•	Room Database
•	State Management (remember, mutableStateOf, Flow)
•	Compose Animations
•	MVVM Architecture

⸻

📂 Project Structure

com.example.sereniview
│
├── ui
│   ├── screens
│   ├── navigation
│   ├── components
│   └── theme
│
├── data
│   └── local
│       ├── GroundEntry.kt
│       ├── GroundDao.kt
│       └── SereniviewDatabase.kt
│
├── MainActivity.kt
└── NavGraph.kt

[Sereniview Documentation.docx](../../Desktop/Sereniview%20Documentation.docx)

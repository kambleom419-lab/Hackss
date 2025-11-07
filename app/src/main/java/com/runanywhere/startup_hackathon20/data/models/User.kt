package com.runanywhere.startup_hackathon20.data.models

import kotlinx.serialization.Serializable

enum class UserRole {
    STUDENT,
    TEACHER
}

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val profileImageUrl: String? = null,
    val xp: Int = 0,
    val level: Int = 1,
    val achievements: List<Achievement> = emptyList(),
    val completedQuests: List<String> = emptyList(),
    val streak: Int = 0,
    val subjects: List<String> = emptyList()
)

@Serializable
data class Achievement(
    val id: String,
    val name: String,
    val description: String,
    val iconName: String,
    val xpReward: Int,
    val unlockedAt: Long? = null
)

@Serializable
data class Quest(
    val id: String,
    val title: String,
    val description: String,
    val subject: String,
    val difficulty: QuestDifficulty,
    val xpReward: Int,
    val challenges: List<Challenge>,
    val isCompleted: Boolean = false,
    val progress: Float = 0f
)

enum class QuestDifficulty {
    EASY,
    MEDIUM,
    HARD,
    EXPERT
}

@Serializable
data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val type: ChallengeType,
    val content: String,
    val isCompleted: Boolean = false
)

enum class ChallengeType {
    QUIZ,
    FLASHCARD_REVIEW,
    PRACTICE_PROBLEM,
    READING,
    VIDEO
}

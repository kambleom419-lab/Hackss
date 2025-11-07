package com.runanywhere.startup_hackathon20.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TeacherCourse(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val difficulty: QuestDifficulty,
    val modules: List<CourseModule>,
    val xpReward: Int,
    val progress: Float = 0f,
    val isCompleted: Boolean = false
)

@Serializable
data class CourseModule(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val videoUrl: String? = null,
    val quiz: Quiz? = null,
    val isCompleted: Boolean = false
)

@Serializable
data class SharedResource(
    val id: String,
    val teacherId: String,
    val teacherName: String,
    val title: String,
    val description: String,
    val subject: String,
    val fileUrl: String,
    val type: ResourceType,
    val uploadedAt: Long,
    val downloads: Int = 0,
    val rating: Float = 0f,
    val reviews: List<ResourceReview> = emptyList()
)

enum class ResourceType {
    LESSON_PLAN,
    WORKSHEET,
    PRESENTATION,
    ASSESSMENT,
    ACTIVITY,
    OTHER
}

@Serializable
data class ResourceReview(
    val userId: String,
    val userName: String,
    val rating: Int,
    val comment: String,
    val createdAt: Long
)

@Serializable
data class ClassRoom(
    val id: String,
    val name: String,
    val teacherId: String,
    val subject: String,
    val students: List<StudentProgress>,
    val createdAt: Long
)

@Serializable
data class StudentProgress(
    val studentId: String,
    val studentName: String,
    val xp: Int,
    val level: Int,
    val completedQuests: Int,
    val averageScore: Float,
    val lastActive: Long,
    val strengths: List<String>,
    val weaknesses: List<String>,
    val recentActivity: List<ActivityLog>
)

@Serializable
data class ActivityLog(
    val type: ActivityType,
    val description: String,
    val timestamp: Long,
    val xpEarned: Int = 0
)

enum class ActivityType {
    QUEST_COMPLETED,
    QUIZ_TAKEN,
    FLASHCARD_REVIEW,
    ACHIEVEMENT_UNLOCKED,
    LEVEL_UP,
    CHAT_SESSION
}

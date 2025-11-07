package com.runanywhere.startup_hackathon20.data.models

import kotlinx.serialization.Serializable

@Serializable
data class StudyMaterial(
    val id: String,
    val userId: String,
    val title: String,
    val subject: String,
    val content: String,
    val type: MaterialType,
    val createdAt: Long,
    val notes: List<StudyNote> = emptyList(),
    val flashcards: List<Flashcard> = emptyList(),
    val quizzes: List<Quiz> = emptyList()
)

enum class MaterialType {
    TEXT,
    PDF,
    IMAGE,
    VIDEO
}

@Serializable
data class StudyNote(
    val id: String,
    val title: String,
    val content: String,
    val tags: List<String>,
    val createdAt: Long
)

@Serializable
data class Flashcard(
    val id: String,
    val question: String,
    val answer: String,
    val difficulty: Int = 1,
    val lastReviewed: Long? = null,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0
)

@Serializable
data class Quiz(
    val id: String,
    val title: String,
    val questions: List<QuizQuestion>,
    val subject: String,
    val difficulty: QuestDifficulty,
    val completedAt: Long? = null,
    val score: Int? = null
)

@Serializable
data class QuizQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String,
    val userAnswer: Int? = null
)

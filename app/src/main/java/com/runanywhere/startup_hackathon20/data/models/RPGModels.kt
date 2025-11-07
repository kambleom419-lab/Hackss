package com.runanywhere.startup_hackathon20.data.models

import kotlinx.serialization.Serializable

@Serializable
data class KnightProfile(
    val id: String,
    val knightName: String,
    val rank: KnightRank,
    val totalXP: Int,
    val currentHP: Int,
    val maxHP: Int,
    val defeatedEnemies: List<String>,
    val unlockedRoutes: List<String>,
    val badges: List<Badge>,
    val equippedWeapon: String = "Wooden Sword",
    val equippedArmor: String = "Leather Armor"
)

enum class KnightRank(val title: String, val xpRequired: Int) {
    NOVICE("Novice", 0),
    SQUIRE("Squire", 100),
    KNIGHT("Knight", 300),
    HERO("Hero", 600)
}

@Serializable
data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val unlockedAt: Long
)

@Serializable
data class LearningRoute(
    val id: String,
    val subject: String,
    val routeName: String,
    val description: String,
    val backgroundTheme: RouteTheme,
    val modules: List<QuestModule>,
    val finalBoss: String,
    val isUnlocked: Boolean = true
)

enum class RouteTheme {
    FOREST,
    MOUNTAIN,
    DESERT,
    CASTLE,
    DUNGEON,
    MYSTIC
}

@Serializable
data class QuestModule(
    val id: String,
    val moduleNumber: Int,
    val title: String,
    val topic: String,
    val enemyName: String,
    val enemyDescription: String,
    val enemyLevel: Int,
    val xpReward: Int,
    val videoUrl: String,
    val videoStartTime: Int = 0, // in seconds
    val videoEndTime: Int? = null, // in seconds, null means play till end
    val isCompleted: Boolean = false,
    val isBoss: Boolean = false
)

@Serializable
data class VideoLesson(
    val moduleId: String,
    val youtubeVideoId: String,
    val startTimeSeconds: Int,
    val endTimeSeconds: Int?,
    val title: String,
    val description: String
)

// Combat result data
@Serializable
data class CombatResult(
    val moduleId: String,
    val enemyName: String,
    val victory: Boolean,
    val xpEarned: Int,
    val damageDealt: Int,
    val damageTaken: Int
)

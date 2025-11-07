package com.runanywhere.startup_hackathon20.data.repository

import com.runanywhere.startup_hackathon20.data.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class RPGRepository {

    private val _knightProfile = MutableStateFlow<KnightProfile?>(null)
    val knightProfile: StateFlow<KnightProfile?> = _knightProfile

    private val _learningRoutes = MutableStateFlow<List<LearningRoute>>(emptyList())
    val learningRoutes: StateFlow<List<LearningRoute>> = _learningRoutes

    init {
        initializeKnight()
        initializeRoutes()
    }

    private fun initializeKnight() {
        _knightProfile.value = KnightProfile(
            id = UUID.randomUUID().toString(),
            knightName = "Brave Learner",
            rank = KnightRank.NOVICE,
            totalXP = 0,
            currentHP = 100,
            maxHP = 100,
            defeatedEnemies = emptyList(),
            unlockedRoutes = listOf("math_route"),
            badges = emptyList(),
            equippedWeapon = "Wooden Sword"
        )
    }

    private fun initializeRoutes() {
        _learningRoutes.value = listOf(createMathRoute())
    }

    private fun createMathRoute(): LearningRoute {
        return LearningRoute(
            id = "math_route",
            subject = "Mathematics",
            routeName = "The Path of Numbers",
            description = "Journey through the mystical realm of mathematics and defeat the dark forces that threaten the kingdom!",
            backgroundTheme = RouteTheme.MYSTIC,
            finalBoss = "The Demon Lord of Mathematics",
            modules = listOf(
                QuestModule(
                    id = "math_module_1",
                    moduleNumber = 1,
                    title = "Module 1: Sets",
                    topic = "Sets - The Foundation",
                    enemyName = "The Necromancer of Sets",
                    enemyDescription = "The Necromancer of Sets has been raiding the village, capturing townsfolk in his mysterious collections. Complete this module to save the villagers and learn the ancient art of Sets!",
                    enemyLevel = 5,
                    xpReward = 50,
                    videoUrl = "https://www.youtube.com/embed/jKUpw3TyjHI",
                    videoStartTime = 0,
                    videoEndTime = 1008
                ),
                QuestModule(
                    id = "math_module_2",
                    moduleNumber = 2,
                    title = "Module 2: Relations and Functions",
                    topic = "Relations and Functions",
                    enemyName = "The Sorceress of Relations",
                    enemyDescription = "The Sorceress has cursed the land, breaking all connections between the kingdoms. Master Relations and Functions to restore harmony and defeat her dark magic!",
                    enemyLevel = 10,
                    xpReward = 75,
                    videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_RELATIONS",
                    videoStartTime = 0,
                    videoEndTime = null
                ),
                QuestModule(
                    id = "math_module_3",
                    moduleNumber = 3,
                    title = "Module 3: Trigonometric Functions",
                    topic = "Trigonometric Functions",
                    enemyName = "The Triangle Titan",
                    enemyDescription = "A colossal Titan made of triangles terrorizes the mountain pass. Learn the secrets of Trigonometric Functions to measure your way to victory!",
                    enemyLevel = 15,
                    xpReward = 100,
                    videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_TRIGONOMETRY",
                    videoStartTime = 0,
                    videoEndTime = null
                ),
                QuestModule(
                    id = "math_module_4",
                    moduleNumber = 4,
                    title = "Module 4: Complex Numbers",
                    topic = "Complex Numbers",
                    enemyName = "The Phantom of Imaginary Realm",
                    enemyDescription = "A ghostly Phantom dwells in the realm between real and imaginary. Unravel the mysteries of Complex Numbers to banish this spectral menace!",
                    enemyLevel = 20,
                    xpReward = 125,
                    videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_COMPLEX_NUMBERS",
                    videoStartTime = 0,
                    videoEndTime = null
                ),
                QuestModule(
                    id = "math_module_5",
                    moduleNumber = 5,
                    title = "Module 5: Quadratic Functions",
                    topic = "Quadratic Functions",
                    enemyName = "The Parabola Dragon",
                    enemyDescription = "A mighty Dragon whose flight path curves through the sky. Master Quadratic Functions to predict its movements and strike it down!",
                    enemyLevel = 25,
                    xpReward = 150,
                    videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_QUADRATIC",
                    videoStartTime = 0,
                    videoEndTime = null
                ),
                QuestModule(
                    id = "math_module_6",
                    moduleNumber = 6,
                    title = "Module 6: Linear Inequalities",
                    topic = "Linear Inequalities",
                    enemyName = "The Demon Lord of Mathematics",
                    enemyDescription = "The ultimate evil has risen! The Demon Lord of Mathematics threatens to plunge the world into eternal darkness. Complete this final module and master Linear Inequalities to bring back light to the world!",
                    enemyLevel = 30,
                    xpReward = 200,
                    videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_LINEAR_INEQUALITIES",
                    videoStartTime = 0,
                    videoEndTime = null,
                    isBoss = true
                )
            )
        )
    }

    fun completeModule(moduleId: String) {
        val routes = _learningRoutes.value.toMutableList()
        val routeIndex = routes.indexOfFirst { route ->
            route.modules.any { it.id == moduleId }
        }

        if (routeIndex != -1) {
            val route = routes[routeIndex]
            val updatedModules = route.modules.map { module ->
                if (module.id == moduleId) {
                    earnXP(module.xpReward)
                    addDefeatedEnemy(module.enemyName)
                    updateWeapon() // Check for weapon upgrade
                    module.copy(isCompleted = true)
                } else {
                    module
                }
            }
            routes[routeIndex] = route.copy(modules = updatedModules)
            _learningRoutes.value = routes
        }
    }

    private fun earnXP(amount: Int) {
        _knightProfile.value?.let { knight ->
            val newXP = knight.totalXP + amount
            val newRank = calculateRank(newXP)
            _knightProfile.value = knight.copy(
                totalXP = newXP,
                rank = newRank
            )
        }
    }

    private fun calculateRank(xp: Int): KnightRank {
        return when {
            xp >= 600 -> KnightRank.HERO
            xp >= 300 -> KnightRank.KNIGHT
            xp >= 100 -> KnightRank.SQUIRE
            else -> KnightRank.NOVICE
        }
    }

    private fun updateWeapon() {
        _knightProfile.value?.let { knight ->
            val completedCount = knight.defeatedEnemies.size
            val newWeapon = when {
                completedCount >= 5 -> "Diamond Sword"
                completedCount >= 3 -> "Golden Sword"
                completedCount >= 1 -> "Iron Sword"
                else -> "Wooden Sword"
            }
            _knightProfile.value = knight.copy(equippedWeapon = newWeapon)
        }
    }

    private fun addDefeatedEnemy(enemyName: String) {
        _knightProfile.value?.let { knight ->
            if (!knight.defeatedEnemies.contains(enemyName)) {
                _knightProfile.value = knight.copy(
                    defeatedEnemies = knight.defeatedEnemies + enemyName
                )
            }
        }
    }

    fun unlockBadge(badge: Badge) {
        _knightProfile.value?.let { knight ->
            _knightProfile.value = knight.copy(
                badges = knight.badges + badge
            )
        }
    }

    companion object {
        @Volatile
        private var instance: RPGRepository? = null

        fun getInstance(): RPGRepository {
            return instance ?: synchronized(this) {
                instance ?: RPGRepository().also { instance = it }
            }
        }
    }
}

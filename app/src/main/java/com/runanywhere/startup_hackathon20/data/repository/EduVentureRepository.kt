package com.runanywhere.startup_hackathon20.data.repository

import com.runanywhere.startup_hackathon20.data.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class EduVentureRepository {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _quests = MutableStateFlow<List<Quest>>(emptyList())
    val quests: StateFlow<List<Quest>> = _quests

    private val _studyMaterials = MutableStateFlow<List<StudyMaterial>>(emptyList())
    val studyMaterials: StateFlow<List<StudyMaterial>> = _studyMaterials

    private val _teacherCourses = MutableStateFlow<List<TeacherCourse>>(emptyList())
    val teacherCourses: StateFlow<List<TeacherCourse>> = _teacherCourses

    private val _sharedResources = MutableStateFlow<List<SharedResource>>(emptyList())
    val sharedResources: StateFlow<List<SharedResource>> = _sharedResources

    private val _classRooms = MutableStateFlow<List<ClassRoom>>(emptyList())
    val classRooms: StateFlow<List<ClassRoom>> = _classRooms

    init {
        // Initialize with mock data
        initializeMockData()
    }

    fun loginAsStudent() {
        _currentUser.value = createMockStudent()
        _quests.value = createMockQuests()
    }

    fun loginAsTeacher() {
        _currentUser.value = createMockTeacher()
        _teacherCourses.value = createMockTeacherCourses()
        _sharedResources.value = createMockSharedResources()
        _classRooms.value = createMockClassRooms()
    }

    fun logout() {
        _currentUser.value = null
        _quests.value = emptyList()
        _teacherCourses.value = emptyList()
        _sharedResources.value = emptyList()
        _classRooms.value = emptyList()
    }

    fun earnXP(amount: Int) {
        _currentUser.value?.let { user ->
            val newXP = user.xp + amount
            val newLevel = calculateLevel(newXP)
            _currentUser.value = user.copy(xp = newXP, level = newLevel)
        }
    }

    fun completeQuest(questId: String) {
        _quests.value = _quests.value.map { quest ->
            if (quest.id == questId) {
                earnXP(quest.xpReward)
                quest.copy(isCompleted = true, progress = 1f)
            } else {
                quest
            }
        }

        _currentUser.value?.let { user ->
            _currentUser.value = user.copy(
                completedQuests = user.completedQuests + questId
            )
        }
    }

    fun updateQuestProgress(questId: String, progress: Float) {
        _quests.value = _quests.value.map { quest ->
            if (quest.id == questId) {
                quest.copy(progress = progress)
            } else {
                quest
            }
        }
    }

    fun addStudyMaterial(material: StudyMaterial) {
        _studyMaterials.value = _studyMaterials.value + material
    }

    fun unlockAchievement(achievement: Achievement) {
        _currentUser.value?.let { user ->
            val updatedAchievement = achievement.copy(unlockedAt = System.currentTimeMillis())
            _currentUser.value = user.copy(
                achievements = user.achievements + updatedAchievement,
                xp = user.xp + achievement.xpReward
            )
        }
    }

    private fun calculateLevel(xp: Int): Int {
        // Simple level calculation: 100 XP per level
        return (xp / 100) + 1
    }

    private fun initializeMockData() {
        _quests.value = createMockQuests()
    }

    private fun createMockStudent(): User {
        return User(
            id = UUID.randomUUID().toString(),
            name = "Alex Student",
            email = "alex@eduventure.com",
            role = UserRole.STUDENT,
            xp = 450,
            level = 5,
            achievements = listOf(
                Achievement(
                    id = "first_quest",
                    name = "First Steps",
                    description = "Complete your first quest",
                    iconName = "🎯",
                    xpReward = 50,
                    unlockedAt = System.currentTimeMillis()
                ),
                Achievement(
                    id = "quiz_master",
                    name = "Quiz Master",
                    description = "Score 100% on a quiz",
                    iconName = "🏆",
                    xpReward = 100,
                    unlockedAt = System.currentTimeMillis()
                )
            ),
            streak = 7,
            subjects = listOf("Mathematics", "Science", "History")
        )
    }

    private fun createMockTeacher(): User {
        return User(
            id = UUID.randomUUID().toString(),
            name = "Prof. Sarah Johnson",
            email = "sarah@eduventure.com",
            role = UserRole.TEACHER,
            xp = 1250,
            level = 13,
            achievements = listOf(
                Achievement(
                    id = "resource_sharer",
                    name = "Resource Contributor",
                    description = "Share 10 resources with community",
                    iconName = "📚",
                    xpReward = 200,
                    unlockedAt = System.currentTimeMillis()
                )
            ),
            subjects = listOf("Mathematics", "Physics")
        )
    }

    private fun createMockQuests(): List<Quest> {
        return listOf(
            Quest(
                id = "quest_1",
                title = "Master Linear Equations",
                description = "Learn and practice solving linear equations",
                subject = "Mathematics",
                difficulty = QuestDifficulty.EASY,
                xpReward = 50,
                challenges = listOf(
                    Challenge(
                        id = "c1",
                        title = "Watch Introduction Video",
                        description = "Learn the basics of linear equations",
                        type = ChallengeType.VIDEO,
                        content = "linear_equations_intro"
                    ),
                    Challenge(
                        id = "c2",
                        title = "Practice Problems",
                        description = "Solve 10 practice problems",
                        type = ChallengeType.PRACTICE_PROBLEM,
                        content = "practice_set_1"
                    ),
                    Challenge(
                        id = "c3",
                        title = "Quiz Time",
                        description = "Test your knowledge",
                        type = ChallengeType.QUIZ,
                        content = "quiz_linear_eq"
                    )
                )
            ),
            Quest(
                id = "quest_2",
                title = "Photosynthesis Deep Dive",
                description = "Explore the process of photosynthesis",
                subject = "Science",
                difficulty = QuestDifficulty.MEDIUM,
                xpReward = 75,
                challenges = listOf(
                    Challenge(
                        id = "c4",
                        title = "Read Chapter",
                        description = "Study the photosynthesis chapter",
                        type = ChallengeType.READING,
                        content = "photosynthesis_chapter"
                    ),
                    Challenge(
                        id = "c5",
                        title = "Flashcard Review",
                        description = "Review 20 flashcards",
                        type = ChallengeType.FLASHCARD_REVIEW,
                        content = "photosynthesis_flashcards"
                    )
                )
            ),
            Quest(
                id = "quest_3",
                title = "World War II Analysis",
                description = "Study the causes and effects of WWII",
                subject = "History",
                difficulty = QuestDifficulty.HARD,
                xpReward = 100,
                challenges = listOf(
                    Challenge(
                        id = "c6",
                        title = "Documentary Review",
                        description = "Watch and analyze historical documentary",
                        type = ChallengeType.VIDEO,
                        content = "wwii_documentary"
                    ),
                    Challenge(
                        id = "c7",
                        title = "Essay Quiz",
                        description = "Answer comprehensive questions",
                        type = ChallengeType.QUIZ,
                        content = "wwii_quiz"
                    )
                )
            ),
            Quest(
                id = "quest_4",
                title = "Advanced Calculus",
                description = "Master derivatives and integrals",
                subject = "Mathematics",
                difficulty = QuestDifficulty.EXPERT,
                xpReward = 150,
                challenges = listOf(
                    Challenge(
                        id = "c8",
                        title = "Theory Study",
                        description = "Understand calculus fundamentals",
                        type = ChallengeType.READING,
                        content = "calculus_theory"
                    ),
                    Challenge(
                        id = "c9",
                        title = "Problem Solving",
                        description = "Solve complex calculus problems",
                        type = ChallengeType.PRACTICE_PROBLEM,
                        content = "calculus_problems"
                    )
                )
            )
        )
    }

    private fun createMockTeacherCourses(): List<TeacherCourse> {
        return listOf(
            TeacherCourse(
                id = "tc_1",
                title = "Effective Classroom Management",
                description = "Learn strategies for managing diverse classrooms",
                category = "Pedagogy",
                difficulty = QuestDifficulty.MEDIUM,
                xpReward = 100,
                modules = listOf(
                    CourseModule(
                        id = "m1",
                        title = "Introduction to Classroom Dynamics",
                        description = "Understanding student behavior patterns",
                        content = "Course content about classroom dynamics..."
                    ),
                    CourseModule(
                        id = "m2",
                        title = "Conflict Resolution Techniques",
                        description = "Practical strategies for handling conflicts",
                        content = "Course content about conflict resolution..."
                    )
                )
            ),
            TeacherCourse(
                id = "tc_2",
                title = "Digital Tools for Education",
                description = "Master modern educational technology",
                category = "Technology",
                difficulty = QuestDifficulty.EASY,
                xpReward = 75,
                modules = listOf(
                    CourseModule(
                        id = "m3",
                        title = "Interactive Presentations",
                        description = "Creating engaging digital content",
                        content = "Course content about presentations..."
                    )
                )
            )
        )
    }

    private fun createMockSharedResources(): List<SharedResource> {
        return listOf(
            SharedResource(
                id = "sr_1",
                teacherId = "t1",
                teacherName = "Dr. Emily Chen",
                title = "Algebra Worksheet Bundle",
                description = "Comprehensive algebra practice worksheets",
                subject = "Mathematics",
                fileUrl = "algebra_bundle.pdf",
                type = ResourceType.WORKSHEET,
                uploadedAt = System.currentTimeMillis(),
                downloads = 156,
                rating = 4.8f
            ),
            SharedResource(
                id = "sr_2",
                teacherId = "t2",
                teacherName = "Prof. Michael Brown",
                title = "Cell Biology Lesson Plan",
                description = "Complete lesson plan with activities",
                subject = "Science",
                fileUrl = "cell_biology.pdf",
                type = ResourceType.LESSON_PLAN,
                uploadedAt = System.currentTimeMillis(),
                downloads = 98,
                rating = 4.5f
            )
        )
    }

    private fun createMockClassRooms(): List<ClassRoom> {
        return listOf(
            ClassRoom(
                id = "class_1",
                name = "Mathematics 101 - Morning Section",
                teacherId = "t1",
                subject = "Mathematics",
                students = listOf(
                    StudentProgress(
                        studentId = "s1",
                        studentName = "Emma Wilson",
                        xp = 650,
                        level = 7,
                        completedQuests = 8,
                        averageScore = 92.5f,
                        lastActive = System.currentTimeMillis(),
                        strengths = listOf("Algebra", "Geometry"),
                        weaknesses = listOf("Calculus"),
                        recentActivity = listOf(
                            ActivityLog(
                                type = ActivityType.QUEST_COMPLETED,
                                description = "Completed Linear Equations quest",
                                timestamp = System.currentTimeMillis(),
                                xpEarned = 50
                            )
                        )
                    ),
                    StudentProgress(
                        studentId = "s2",
                        studentName = "James Rodriguez",
                        xp = 480,
                        level = 5,
                        completedQuests = 5,
                        averageScore = 85.0f,
                        lastActive = System.currentTimeMillis() - 86400000,
                        strengths = listOf("Problem Solving"),
                        weaknesses = listOf("Word Problems"),
                        recentActivity = listOf(
                            ActivityLog(
                                type = ActivityType.QUIZ_TAKEN,
                                description = "Scored 85% on Algebra quiz",
                                timestamp = System.currentTimeMillis() - 86400000,
                                xpEarned = 25
                            )
                        )
                    )
                ),
                createdAt = System.currentTimeMillis()
            )
        )
    }

    companion object {
        @Volatile
        private var instance: EduVentureRepository? = null

        fun getInstance(): EduVentureRepository {
            return instance ?: synchronized(this) {
                instance ?: EduVentureRepository().also { instance = it }
            }
        }
    }
}

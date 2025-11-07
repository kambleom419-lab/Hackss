package com.runanywhere.startup_hackathon20.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runanywhere.sdk.public.RunAnywhere
import com.runanywhere.sdk.public.extensions.listAvailableModels
import com.runanywhere.sdk.models.ModelInfo
import com.runanywhere.startup_hackathon20.data.models.*
import com.runanywhere.startup_hackathon20.data.repository.EduVentureRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID

class EduVentureViewModel : ViewModel() {

    private val repository = EduVentureRepository.getInstance()

    // User & Auth
    val currentUser: StateFlow<User?> = repository.currentUser

    // Student Data
    val quests: StateFlow<List<Quest>> = repository.quests
    val studyMaterials: StateFlow<List<StudyMaterial>> = repository.studyMaterials

    // Teacher Data
    val teacherCourses: StateFlow<List<TeacherCourse>> = repository.teacherCourses
    val sharedResources: StateFlow<List<SharedResource>> = repository.sharedResources
    val classRooms: StateFlow<List<ClassRoom>> = repository.classRooms

    // AI Chat State
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private val _isAIChatLoading = MutableStateFlow(false)
    val isAIChatLoading: StateFlow<Boolean> = _isAIChatLoading

    // AI Model State
    private val _availableModels = MutableStateFlow<List<ModelInfo>>(emptyList())
    val availableModels: StateFlow<List<ModelInfo>> = _availableModels

    private val _downloadProgress = MutableStateFlow<Float?>(null)
    val downloadProgress: StateFlow<Float?> = _downloadProgress

    private val _currentModelId = MutableStateFlow<String?>(null)
    val currentModelId: StateFlow<String?> = _currentModelId

    private val _statusMessage = MutableStateFlow<String>("Initializing AI...")
    val statusMessage: StateFlow<String> = _statusMessage

    // UI State
    private val _selectedQuest = MutableStateFlow<Quest?>(null)
    val selectedQuest: StateFlow<Quest?> = _selectedQuest

    private val _selectedCourse = MutableStateFlow<TeacherCourse?>(null)
    val selectedCourse: StateFlow<TeacherCourse?> = _selectedCourse

    private val _showAchievementDialog = MutableStateFlow<Achievement?>(null)
    val showAchievementDialog: StateFlow<Achievement?> = _showAchievementDialog

    init {
        loadAvailableModels()
    }

    // Auth Functions
    fun loginAsStudent() {
        repository.loginAsStudent()
    }

    fun loginAsTeacher() {
        repository.loginAsTeacher()
    }

    fun logout() {
        repository.logout()
    }

    // Student Functions
    fun selectQuest(quest: Quest) {
        _selectedQuest.value = quest
    }

    fun completeChallenge(questId: String, challengeId: String) {
        viewModelScope.launch {
            val quest = quests.value.find { it.id == questId }
            quest?.let {
                val completedChallenges = it.challenges.count { c -> c.isCompleted } + 1
                val progress = completedChallenges.toFloat() / it.challenges.size

                repository.updateQuestProgress(questId, progress)

                if (progress >= 1f) {
                    repository.completeQuest(questId)
                    checkAndUnlockAchievements()
                }
            }
        }
    }

    fun generateStudyNotes(materialContent: String) {
        viewModelScope.launch {
            _isAIChatLoading.value = true
            try {
                val prompt = """
                    Generate concise study notes from the following content. 
                    Format the notes with clear headings and bullet points.
                    
                    Content:
                    $materialContent
                """.trimIndent()

                var response = ""
                RunAnywhere.generateStream(prompt).collect { token ->
                    response += token
                }

                val note = StudyNote(
                    id = UUID.randomUUID().toString(),
                    title = "AI-Generated Notes",
                    content = response,
                    tags = listOf("ai-generated"),
                    createdAt = System.currentTimeMillis()
                )

                // Add to current material (simplified - in real app would update specific material)
                _statusMessage.value = "Study notes generated!"
            } catch (e: Exception) {
                _statusMessage.value = "Error generating notes: ${e.message}"
            }
            _isAIChatLoading.value = false
        }
    }

    fun generateFlashcards(materialContent: String, count: Int = 10) {
        viewModelScope.launch {
            _isAIChatLoading.value = true
            try {
                val prompt = """
                    Generate exactly $count flashcards from the following content.
                    Format each flashcard as:
                    Q: [Question]
                    A: [Answer]
                    ---
                    
                    Content:
                    $materialContent
                """.trimIndent()

                var response = ""
                RunAnywhere.generateStream(prompt).collect { token ->
                    response += token
                }

                _statusMessage.value = "Flashcards generated!"
            } catch (e: Exception) {
                _statusMessage.value = "Error generating flashcards: ${e.message}"
            }
            _isAIChatLoading.value = false
        }
    }

    fun generateQuiz(materialContent: String) {
        viewModelScope.launch {
            _isAIChatLoading.value = true
            try {
                val prompt = """
                    Generate a quiz with 5 multiple-choice questions from the following content.
                    Format each question as:
                    Q: [Question]
                    A) [Option 1]
                    B) [Option 2]
                    C) [Option 3]
                    D) [Option 4]
                    Correct: [A/B/C/D]
                    Explanation: [Brief explanation]
                    ---
                    
                    Content:
                    $materialContent
                """.trimIndent()

                var response = ""
                RunAnywhere.generateStream(prompt).collect { token ->
                    response += token
                }

                _statusMessage.value = "Quiz generated!"
            } catch (e: Exception) {
                _statusMessage.value = "Error generating quiz: ${e.message}"
            }
            _isAIChatLoading.value = false
        }
    }

    // Teacher Functions
    fun selectCourse(course: TeacherCourse) {
        _selectedCourse.value = course
    }

    fun completeModule(courseId: String, moduleId: String) {
        // Implementation for completing teacher course modules
    }

    // AI Chat Functions
    fun sendChatMessage(message: String, context: String = "") {
        if (_currentModelId.value == null) {
            _statusMessage.value = "Please load an AI model first"
            return
        }

        _chatMessages.value += ChatMessage(message, isUser = true)

        viewModelScope.launch {
            _isAIChatLoading.value = true
            try {
                val fullPrompt = if (context.isNotEmpty()) {
                    """
                    You are an educational AI assistant helping students learn.
                    
                    Context: $context
                    
                    Student question: $message
                    
                    Provide a clear, educational response.
                    """.trimIndent()
                } else {
                    message
                }

                var assistantResponse = ""
                RunAnywhere.generateStream(fullPrompt).collect { token ->
                    assistantResponse += token

                    val currentMessages = _chatMessages.value.toMutableList()
                    if (currentMessages.lastOrNull()?.isUser == false) {
                        currentMessages[currentMessages.lastIndex] =
                            ChatMessage(assistantResponse, isUser = false)
                    } else {
                        currentMessages.add(ChatMessage(assistantResponse, isUser = false))
                    }
                    _chatMessages.value = currentMessages
                }

                repository.earnXP(5) // Earn XP for using chat
            } catch (e: Exception) {
                _chatMessages.value += ChatMessage("Error: ${e.message}", isUser = false)
            }
            _isAIChatLoading.value = false
        }
    }

    fun clearChat() {
        _chatMessages.value = emptyList()
    }

    // AI Model Management
    private fun loadAvailableModels() {
        viewModelScope.launch {
            try {
                val models = listAvailableModels()
                _availableModels.value = models
                _statusMessage.value = "AI Ready - Please download and load a model"
            } catch (e: Exception) {
                _statusMessage.value = "Error loading models: ${e.message}"
            }
        }
    }

    fun downloadModel(modelId: String) {
        viewModelScope.launch {
            try {
                _statusMessage.value = "Downloading AI model..."
                RunAnywhere.downloadModel(modelId).collect { progress ->
                    _downloadProgress.value = progress
                    _statusMessage.value = "Downloading: ${(progress * 100).toInt()}%"
                }
                _downloadProgress.value = null
                _statusMessage.value = "Download complete! Please load the model."
            } catch (e: Exception) {
                _statusMessage.value = "Download failed: ${e.message}"
                _downloadProgress.value = null
            }
        }
    }

    fun loadModel(modelId: String) {
        viewModelScope.launch {
            try {
                _statusMessage.value = "Loading AI model..."
                val success = RunAnywhere.loadModel(modelId)
                if (success) {
                    _currentModelId.value = modelId
                    _statusMessage.value = "AI Ready! Start chatting or generate content."
                } else {
                    _statusMessage.value = "Failed to load model"
                }
            } catch (e: Exception) {
                _statusMessage.value = "Error loading model: ${e.message}"
            }
        }
    }

    fun refreshModels() {
        loadAvailableModels()
    }

    // Achievement System
    private fun checkAndUnlockAchievements() {
        viewModelScope.launch {
            val user = currentUser.value ?: return@launch
            val completedQuestsCount = user.completedQuests.size

            // Check for achievements
            val newAchievements = mutableListOf<Achievement>()

            if (completedQuestsCount == 1 && user.achievements.none { it.id == "first_quest" }) {
                newAchievements.add(
                    Achievement(
                        id = "first_quest",
                        name = "First Steps",
                        description = "Complete your first quest",
                        iconName = "🎯",
                        xpReward = 50
                    )
                )
            }

            if (completedQuestsCount >= 5 && user.achievements.none { it.id == "quest_warrior" }) {
                newAchievements.add(
                    Achievement(
                        id = "quest_warrior",
                        name = "Quest Warrior",
                        description = "Complete 5 quests",
                        iconName = "⚔️",
                        xpReward = 100
                    )
                )
            }

            if (completedQuestsCount >= 10 && user.achievements.none { it.id == "quest_master" }) {
                newAchievements.add(
                    Achievement(
                        id = "quest_master",
                        name = "Quest Master",
                        description = "Complete 10 quests",
                        iconName = "👑",
                        xpReward = 200
                    )
                )
            }

            newAchievements.forEach { achievement ->
                repository.unlockAchievement(achievement)
                _showAchievementDialog.value = achievement
            }
        }
    }

    fun dismissAchievementDialog() {
        _showAchievementDialog.value = null
    }
}

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

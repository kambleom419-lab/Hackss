package com.runanywhere.startup_hackathon20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.runanywhere.startup_hackathon20.data.models.UserRole
import com.runanywhere.startup_hackathon20.data.repository.RPGRepository
import com.runanywhere.startup_hackathon20.ui.screens.*
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme
import com.runanywhere.startup_hackathon20.viewmodel.EduVentureViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Startup_hackathon20Theme {
                EduVentureApp()
            }
        }
    }
}

@Composable
fun EduVentureApp(viewModel: EduVentureViewModel = viewModel()) {
    val currentUser by viewModel.currentUser.collectAsState()
    var currentScreen by remember { mutableStateOf("login") }

    // RPG Repository for new knight-themed interface
    val rpgRepository = remember { RPGRepository.getInstance() }
    val knightProfile by rpgRepository.knightProfile.collectAsState()
    val learningRoutes by rpgRepository.learningRoutes.collectAsState()
    var selectedModule by remember {
        mutableStateOf<com.runanywhere.startup_hackathon20.data.models.QuestModule?>(
            null
        )
    }

    when {
        currentUser == null -> {
            LoginScreen(
                viewModel = viewModel,
                onLoginAsStudent = {
                    currentScreen = "rpg_student_home"
                },
                onLoginAsTeacher = {
                    currentScreen = "teacher_home"
                }
            )
        }
        currentUser?.role == UserRole.STUDENT -> {
            when (currentScreen) {
                "module_video" -> {
                    selectedModule?.let { module ->
                        ModuleVideoScreen(
                            module = module,
                            onBack = {
                                selectedModule = null
                                currentScreen = "rpg_student_home"
                            },
                            onComplete = {
                                rpgRepository.completeModule(module.id)
                                selectedModule = null
                                currentScreen = "rpg_student_home"
                            }
                        )
                    }
                }
                "ai_chat" -> {
                    AIChatScreen(
                        viewModel = viewModel,
                        onBack = { currentScreen = "rpg_student_home" }
                    )
                }
                else -> {
                    // New RPG-themed student home with bottom navigation
                    RPGStudentScreenWithNav(
                        knightProfile = knightProfile,
                        routes = learningRoutes,
                        onModuleClick = { module ->
                            selectedModule = module
                            currentScreen = "module_video"
                        },
                        viewModel = viewModel,
                        onLogout = {
                            viewModel.logout()
                            currentScreen = "login"
                        }
                    )
                }
            }
        }
        currentUser?.role == UserRole.TEACHER -> {
            when (currentScreen) {
                "ai_chat" -> {
                    AIChatScreen(
                        viewModel = viewModel,
                        onBack = { currentScreen = "teacher_home" }
                    )
                }
                else -> {
                    TeacherHomeScreen(
                        viewModel = viewModel,
                        onNavigateToChat = {
                            currentScreen = "ai_chat"
                        },
                        onNavigateToProfile = {
                            // Could navigate to profile
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Startup_hackathon20Theme {
        EduVentureApp()
    }
}
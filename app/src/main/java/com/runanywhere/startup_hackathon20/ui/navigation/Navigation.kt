package com.runanywhere.startup_hackathon20.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object StudentHome : Screen("student_home")
    object TeacherHome : Screen("teacher_home")
    object Quests : Screen("quests")
    object QuestDetail : Screen("quest_detail/{questId}") {
        fun createRoute(questId: String) = "quest_detail/$questId"
    }

    object Profile : Screen("profile")
    object AIChat : Screen("ai_chat")
    object StudyMaterials : Screen("study_materials")
    object TeacherCourses : Screen("teacher_courses")
    object ClassDashboard : Screen("class_dashboard")
    object ResourceHub : Screen("resource_hub")
}

package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runanywhere.startup_hackathon20.data.models.*
import com.runanywhere.startup_hackathon20.viewmodel.EduVentureViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen(
    viewModel: EduVentureViewModel,
    onNavigateToQuest: (String) -> Unit,
    onNavigateToChat: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val user by viewModel.currentUser.collectAsState()
    val quests by viewModel.quests.collectAsState()

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "EduVenture",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToChat) {
                        Icon(Icons.Default.Email, "AI Chat")
                    }
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(Icons.Default.Person, "Profile")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6366F1)
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, "Home") },
                    label = { Text("Home") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, "Quests") },
                    label = { Text("Quests") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, "Materials") },
                    label = { Text("Materials") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> DashboardTab(user, quests, onNavigateToQuest, modifier = Modifier.padding(padding))
            1 -> QuestsTab(quests, onNavigateToQuest, modifier = Modifier.padding(padding))
            2 -> MaterialsTab(modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun DashboardTab(
    user: User?,
    quests: List<Quest>,
    onNavigateToQuest: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // User Stats Card
            user?.let {
                UserStatsCard(it)
            }
        }

        item {
            // Daily Streak Card
            user?.let {
                StreakCard(it.streak)
            }
        }

        item {
            Text(
                "Active Quests",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937)
            )
        }

        items(quests.filter { !it.isCompleted }.take(3)) { quest ->
            QuestCard(quest = quest, onClick = { onNavigateToQuest(quest.id) })
        }

        item {
            Text(
                "Recent Achievements",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            user?.let {
                AchievementsRow(it.achievements.take(5))
            }
        }
    }
}

@Composable
fun UserStatsCard(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Welcome back,",
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280)
                    )
                    Text(
                        user.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF6366F1),
                                    Color(0xFF8B5CF6)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Lv ${user.level}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // XP Progress
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "XP: ${user.xp}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1F2937)
                    )
                    Text(
                        "${user.xp % 100}/100 to Level ${user.level + 1}",
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { (user.xp % 100) / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF6366F1),
                    trackColor = Color(0xFFE5E7EB)
                )
            }
        }
    }
}

@Composable
fun StreakCard(streak: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFEF3C7)
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🔥", fontSize = 32.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        "$streak Day Streak!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF92400E)
                    )
                    Text(
                        "Keep up the great work!",
                        fontSize = 14.sp,
                        color = Color(0xFFA16207)
                    )
                }
            }
        }
    }
}

@Composable
fun QuestCard(quest: Quest, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        quest.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        quest.subject,
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280)
                    )
                }

                DifficultyBadge(quest.difficulty)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Bar
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "${(quest.progress * 100).toInt()}% Complete",
                        fontSize = 12.sp,
                        color = Color(0xFF6B7280)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "+${quest.xpReward} XP",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6366F1)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                LinearProgressIndicator(
                    progress = { quest.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = when (quest.difficulty) {
                        QuestDifficulty.EASY -> Color(0xFF10B981)
                        QuestDifficulty.MEDIUM -> Color(0xFFF59E0B)
                        QuestDifficulty.HARD -> Color(0xFFEF4444)
                        QuestDifficulty.EXPERT -> Color(0xFF8B5CF6)
                    },
                    trackColor = Color(0xFFE5E7EB)
                )
            }
        }
    }
}

@Composable
fun DifficultyBadge(difficulty: QuestDifficulty) {
    val (color, text) = when (difficulty) {
        QuestDifficulty.EASY -> Color(0xFF10B981) to "Easy"
        QuestDifficulty.MEDIUM -> Color(0xFFF59E0B) to "Medium"
        QuestDifficulty.HARD -> Color(0xFFEF4444) to "Hard"
        QuestDifficulty.EXPERT -> Color(0xFF8B5CF6) to "Expert"
    }

    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = color
        )
    }
}

@Composable
fun AchievementsRow(achievements: List<Achievement>) {
    if (achievements.isEmpty()) {
        Text(
            "Complete quests to earn achievements!",
            fontSize = 14.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(vertical = 8.dp)
        )
    } else {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(achievements) { achievement ->
                AchievementCard(achievement)
            }
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFEF3C7)
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                achievement.iconName,
                fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                achievement.name,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF92400E),
                maxLines = 2
            )
        }
    }
}

@Composable
fun QuestsTab(
    quests: List<Quest>,
    onNavigateToQuest: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                "All Quests",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(quests) { quest ->
            QuestCard(quest = quest, onClick = { onNavigateToQuest(quest.id) })
        }
    }
}

@Composable
fun MaterialsTab(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text("📚", fontSize = 64.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Study Materials",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Upload your materials and let AI generate notes, flashcards, and quizzes!",
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
        }
    }
}

package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runanywhere.startup_hackathon20.data.models.KnightProfile

data class ProfileOption(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDashboardScreen(
    knightProfile: KnightProfile?,
    onEditProfile: () -> Unit = {},
    onChangePassword: () -> Unit = {},
    onContactUs: () -> Unit = {},
    onShop: () -> Unit = {},
    onNotifications: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A0F0A),
                        Color(0xFF2C1810),
                        Color(0xFF3D2417)
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile Header Card
            item {
                knightProfile?.let { knight ->
                    ProfileHeaderCard(knight)
                }
            }

            // Profile Options
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4A2F1F)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    ) {
                        Text(
                            "⚙️ Account Settings",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD700),
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Edit,
                            title = "Edit Profile",
                            subtitle = "Update your knight information",
                            onClick = onEditProfile
                        )

                        Divider(
                            color = Color(0xFF2C1810),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Lock,
                            title = "Change Password",
                            subtitle = "Secure your account",
                            onClick = onChangePassword
                        )

                        Divider(
                            color = Color(0xFF2C1810),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Notifications,
                            title = "Notifications",
                            subtitle = "Manage your notification preferences",
                            onClick = onNotifications
                        )
                    }
                }
            }

            // Additional Options
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4A2F1F)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    ) {
                        Text(
                            "🛡️ Kingdom Services",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD700),
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.ShoppingCart,
                            title = "Shop",
                            subtitle = "Purchase weapons and armor",
                            onClick = onShop
                        )

                        Divider(
                            color = Color(0xFF2C1810),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Email,
                            title = "Contact Us",
                            subtitle = "Get help from the guild",
                            onClick = onContactUs
                        )
                    }
                }
            }

            // Logout Button
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF8B0000)
                    ),
                    onClick = onLogout
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Logout",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            // Spacer at bottom
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProfileHeaderCard(knight: KnightProfile) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4A2F1F)
        )
    ) {
        Box {
            // Background gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF5C3D2E),
                                Color(0xFF4A2F1F),
                                Color(0xFF5C3D2E)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFFFD700),
                                    Color(0xFFDAA520),
                                    Color(0xFF8B4513)
                                )
                            )
                        )
                        .border(4.dp, Color(0xFF2C1810), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "🛡️",
                        fontSize = 48.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    knight.knightName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFD700)
                )

                Text(
                    knight.rank.title,
                    fontSize = 16.sp,
                    color = Color(0xFFC0C0C0),
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Stats Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileStat("⭐", "XP", "${knight.totalXP}")
                    ProfileStat("⚔️", "Victories", "${knight.defeatedEnemies.size}")
                    ProfileStat("🏅", "Badges", "${knight.badges.size}")
                }
            }
        }
    }
}

@Composable
fun ProfileStat(icon: String, label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(icon, fontSize = 24.sp)
        Text(
            value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFD700)
        )
        Text(
            label,
            fontSize = 12.sp,
            color = Color(0xFFC0C0C0)
        )
    }
}

@Composable
fun ProfileOptionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    subtitle,
                    fontSize = 13.sp,
                    color = Color(0xFFC0C0C0)
                )
            }

            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Go",
                tint = Color(0xFF8B4513),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

# ⚔️ EduVenture RPG - Quick Reference Card

## 🎯 Where to Paste Video URLs

**File**: `app/src/main/java/com/runanywhere/startup_hackathon20/data/repository/RPGRepository.kt`

### Find These 6 Lines:

1. **Line ~57**: `videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_SETS"`
2. **Line ~69**: `videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_RELATIONS"`
3. **Line ~78**: `videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_TRIGONOMETRY"`
4. **Line ~87**: `videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_COMPLEX_NUMBERS"`
5. **Line ~96**: `videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_QUADRATIC"`
6. **Line ~114**: `videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_LINEAR_INEQUALITIES"`

### Replace With Your URLs:

```kotlin
videoUrl = "https://www.youtube.com/watch?v=YOUR_VIDEO_ID"
```

## 📁 New Files Created

| File | Purpose |
|------|---------|
| `RPGModels.kt` | Knight & quest data models |
| `RPGRepository.kt` | Math route with 6 modules |
| `RPGStudentScreen.kt` | Medieval-themed home screen |
| `ModuleVideoScreen.kt` | Video player & enemy battles |

## 🎨 Color Theme

```
Medieval Brown:  #2C1810, #4A2F1F
Gold Accents:    #FFD700
Silver Text:     #C0C0C0
Health Red:      #FF6B6B
Success Green:   #4CAF50
Boss Red:        #8B0000
```

## ⚔️ 6 Enemies to Defeat

1. **Necromancer of Sets** (Lv 5) - 50 XP
2. **Sorceress of Relations** (Lv 10) - 75 XP
3. **Triangle Titan** (Lv 15) - 100 XP
4. **Phantom of Imaginary Realm** (Lv 20) - 125 XP
5. **Parabola Dragon** (Lv 25) - 150 XP
6. **Demon Lord of Mathematics** (Lv 30) - 200 XP ⚡ BOSS

## 🏆 Knight Ranks

| Rank | XP Required |
|------|-------------|
| Novice Knight | 0 |
| Squire | 100 |
| Knight | 300 |
| Elite Knight | 600 |
| Champion | 1000 |
| Hero | 1500 |
| Legendary Hero | 2500 |

## 🚀 Setup Steps

1. **Sync Gradle** - Build → Clean Project, then Build → Rebuild Project
2. **Open RPGRepository.kt**
3. **Replace 6 video URL placeholders**
4. **Save file**
5. **Run app**
6. **Login as Student**
7. **Start your quest!**

## 📹 Video Format Examples

### Full video:

```kotlin
videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
videoStartTime = 0,
videoEndTime = null
```

### With timestamps:

```kotlin
videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
videoStartTime = 120,   // 2:00
videoEndTime = 300      // 5:00
```

## 🎮 User Flow

```
Login (Select Student)
    ↓
Knight Dashboard
(View Profile: Rank, XP, HP, Defeats)
    ↓
Math Route: "The Path of Numbers"
(6 modules in quest path)
    ↓
Click Module 1
    ↓
Module Video Screen
(Watch video, read enemy description)
    ↓
Click "DEFEAT NECROMANCER"
    ↓
+50 XP Earned!
    ↓
Module turns GREEN ✓
    ↓
Proceed to Module 2
    ↓
Repeat until all 6 defeated
    ↓
RANK UP to Squire!
```

## 📱 Screen Elements

### Knight Profile Card

- 🛡️ Name & Rank
- ⭐ Total XP
- ❤️ HP Bar
- ⚔️ Defeats
- 🏅 Badges
- 🗡️ Weapon

### Module Card

- Numbered badge (1-6)
- Enemy name in red
- Level badge
- XP reward badge
- Enemy description
- Green when complete ✓
- Red for boss 👹

### Video Screen

- Video player
- Enemy battle card
- Learning topic
- XP reward
- Defeat button

## 🔧 Technical Notes

### Dependencies Added:

```gradle
implementation("androidx.media3:media3-exoplayer:1.2.0")
implementation("androidx.media3:media3-ui:1.2.0")
```

### After adding videos:

- Clean and rebuild project
- Video will load in player
- Tap video to show/hide controls
- Click defeat button to complete

### If video doesn't play:

- Check URL is correct
- Try direct MP4 URL
- Check internet connection
- See Android Logcat for errors

## 📚 Documentation Files

- `VIDEO_SETUP_GUIDE.md` - Detailed video setup instructions
- `RPG_TRANSFORMATION_SUMMARY.md` - Complete feature list
- `QUICK_REFERENCE.md` - This file

## 🎯 Key Features

✅ Medieval knight RPG theme
✅ 6 math modules with epic enemies
✅ XP and rank progression system
✅ Video lessons integrated
✅ Visual quest path
✅ Enemy battle narratives
✅ Completion tracking
✅ Dark medieval UI design

## 🎨 Module Themes

Each module has:

- **Enemy**: Unique boss to defeat
- **Level**: Difficulty rating (5-30)
- **XP**: Reward for completion (50-200)
- **Story**: Epic narrative description
- **Video**: Learning content
- **Topic**: Math concept to master

## 🗺️ Route Structure

```
🗺️ The Path of Numbers (Mathematics)
│
├── Module 1: Sets
│   └── 🧙 Necromancer (Lv 5)
│
├── Module 2: Relations & Functions
│   └── 🔮 Sorceress (Lv 10)
│
├── Module 3: Trigonometry
│   └── 🗿 Triangle Titan (Lv 15)
│
├── Module 4: Complex Numbers
│   └── 👻 Phantom (Lv 20)
│
├── Module 5: Quadratic Functions
│   └── 🐉 Parabola Dragon (Lv 25)
│
└── Module 6: Linear Inequalities
    └── 👹 DEMON LORD (Lv 30) ⚡
```

---

## 🎉 Ready to Begin!

1. Add your video URLs to `RPGRepository.kt`
2. Rebuild the project
3. Login as Student
4. Embark on your mathematical adventure!

**The kingdom awaits your heroic learning journey!** ⚔️📚✨

# 🚀 Quick Reference Guide - EduVenture RPG

## Common Tasks

### Adding a New Video Module

**Location**:
`app/src/main/java/com/runanywhere/startup_hackathon20/data/repository/RPGRepository.kt`

**Steps**:

1. Find the module in the `createMathRoute()` function
2. Replace the placeholder URL with actual YouTube embed URL
3. Format: `https://www.youtube.com/embed/VIDEO_ID`

**Example**:

```kotlin
QuestModule(
    id = "math_module_2",
    moduleNumber = 2,
    title = "Module 2: Relations and Functions",
    // ... other fields ...
    videoUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ", // Replace this
    videoStartTime = 0,
    videoEndTime = null
)
```

---

### Changing Theme Colors

All screens use these color values. Search and replace across files:

| Current Color       | Purpose          | Used For         |
|---------------------|------------------|------------------|
| `Color(0xFF1A0F0A)` | Dark background  | Gradient top     |
| `Color(0xFF2C1810)` | Mid background   | Cards, top bars  |
| `Color(0xFF3D2417)` | Light background | Gradient bottom  |
| `Color(0xFF4A2F1F)` | Card color       | Main cards       |
| `Color(0xFFFFD700)` | Gold accent      | Headers, buttons |
| `Color(0xFFC0C0C0)` | Silver text      | Secondary text   |

**Files to update**:

- `RPGStudentScreen.kt`
- `ProfileDashboardScreen.kt`
- `LeaderboardScreen.kt`
- `ResourcesScreen.kt`
- `AIChatScreen.kt`
- `ModuleVideoScreen.kt`

---

### Adjusting XP/Level Thresholds

**Location**:
`app/src/main/java/com/runanywhere/startup_hackathon20/data/repository/RPGRepository.kt`

**Function**: `calculateRank()`

```kotlin
private fun calculateRank(xp: Int): KnightRank {
    return when {
        xp >= 600 -> KnightRank.HERO      // Change this threshold
        xp >= 300 -> KnightRank.KNIGHT    // Change this threshold
        xp >= 100 -> KnightRank.SQUIRE    // Change this threshold
        else -> KnightRank.NOVICE
    }
}
```

---

### Modifying Weapon Upgrade System

**Location**: Same file as above

**Function**: `updateWeapon()`

```kotlin
private fun updateWeapon() {
    _knightProfile.value?.let { knight ->
        val completedCount = knight.defeatedEnemies.size
        val newWeapon = when {
            completedCount >= 5 -> "Diamond Sword"    // Change threshold
            completedCount >= 3 -> "Golden Sword"     // Change threshold
            completedCount >= 1 -> "Iron Sword"       // Change threshold
            else -> "Wooden Sword"
        }
        _knightProfile.value = knight.copy(equippedWeapon = newWeapon)
    }
}
```

---

### Adding a New Bottom Tab

**Location**: `app/src/main/java/com/runanywhere/startup_hackathon20/ui/screens/RPGStudentScreen.kt`

**Steps**:

1. Add new `NavigationBarItem` in the `NavigationBar` section:

```kotlin
NavigationBarItem(
    icon = { Icon(Icons.Default.YourIcon, "Label") },
    label = { Text("Label") },
    selected = selectedTab == 5, // Next tab number
    onClick = { selectedTab = 5 }
)
```

2. Add screen in the `when` statement:

```kotlin
when (selectedTab) {
    // ... existing tabs ...
    5 -> YourNewScreen()
}
```

---

### Customizing Leaderboard Data

**Location**:
`app/src/main/java/com/runanywhere/startup_hackathon20/ui/screens/LeaderboardScreen.kt`

**Function**: Inside `LeaderboardScreen` composable

```kotlin
val leaderboardData = remember {
    listOf(
        LeaderboardEntry(1, "Your Name", "Your Region", 850, 9, "Diamond Sword"),
        // Add more entries...
    )
}
```

---

### Adding New Resources

**Location**: `app/src/main/java/com/runanywhere/startup_hackathon20/ui/screens/ResourcesScreen.kt`

```kotlin
val resources = remember {
    listOf(
        ResourceItem(
            title = "Resource Title",
            description = "Description here",
            category = "Category",
            url = "https://example.com",
            icon = "📖" // Choose emoji
        ),
        // Add more resources...
    )
}
```

---

### Modifying Profile Options

**Location**:
`app/src/main/java/com/runanywhere/startup_hackathon20/ui/screens/ProfileDashboardScreen.kt`

**Adding a new option**:

```kotlin
ProfileOptionItem(
    icon = Icons.Default.YourIcon,
    title = "Option Title",
    subtitle = "Option description",
    onClick = { /* Handle click */ }
)
```

---

## Troubleshooting

### Video Not Loading

**Possible Causes**:

1. ❌ Wrong URL format
2. ❌ Placeholder URL still in place
3. ❌ Internet connection issue
4. ❌ YouTube video restrictions

**Solutions**:

1. ✅ Verify URL format: `https://www.youtube.com/embed/VIDEO_ID`
2. ✅ Check `RPGRepository.kt` for actual video ID
3. ✅ Test internet connection
4. ✅ Try different video (ensure it's embeddable)

**Debug Steps**:

```kotlin
// In ModuleVideoScreen.kt, check:
if (module.videoUrl.contains("PASTE_YOUTUBE") || module.videoUrl.isEmpty()) {
    // This means URL hasn't been updated yet
}
```

---

### Bottom Navigation Not Switching

**Check**:

1. Is `selectedTab` state being updated?
2. Are all screens imported correctly?
3. Check for compilation errors

**Debug**:

```kotlin
var selectedTab by remember { mutableStateOf(0) }

// Add logging
Log.d("Navigation", "Selected tab: $selectedTab")
```

---

### XP Not Updating

**Check**:

1. Is `completeModule()` being called?
2. Is the module ID correct?
3. Check `RPGRepository` state flow

**Debug in RPGRepository.kt**:

```kotlin
fun completeModule(moduleId: String) {
    println("Completing module: $moduleId") // Add this
    // ... rest of code
}
```

---

### Logout Not Working

**Check**:

1. Is `logout()` function in ViewModel?
2. Is it calling Repository's `logout()`?
3. Is `currentScreen` being set to "login"?

**Verify in MainActivity.kt**:

```kotlin
onLogout = {
    viewModel.logout()
    currentScreen = "login" // Make sure this is set
}
```

---

### Sir Remi Chat Not Responding

**Possible Causes**:

1. No AI model loaded
2. Model not downloaded
3. RunAnywhere SDK issue

**Solutions**:

1. Tap "AI Model" button in top bar
2. Download a model (e.g., phi-2)
3. Load the model
4. Try sending a message

---

## Build & Run

### Clean Build

```bash
# In project root
./gradlew clean
./gradlew build
```

### Run on Device

```bash
./gradlew installDebug
```

### Check for Issues

```bash
./gradlew check
```

---

## Useful Snippets

### Get Video ID from YouTube URL

```kotlin
fun extractVideoId(url: String): String {
    return when {
        url.contains("youtube.com/watch?v=") -> 
            url.substringAfter("v=").substringBefore("&")
        url.contains("youtu.be/") -> 
            url.substringAfter("youtu.be/").substringBefore("?")
        else -> ""
    }
}
```

### Format XP with Commas

```kotlin
fun formatXP(xp: Int): String {
    return NumberFormat.getNumberInstance().format(xp)
}
```

### Calculate Progress Percentage

```kotlin
fun calculateProgress(current: Int, total: Int): Float {
    return if (total > 0) current.toFloat() / total else 0f
}
```

---

## Support

### For Development Issues

1. Check IMPLEMENTATION_STATUS.md
2. Review IMPLEMENTATION_SUMMARY.md
3. Check this guide
4. Review code comments
5. Debug with print statements

### For Feature Requests

Document in a new file or update IMPLEMENTATION_STATUS.md with:

- Feature description
- Files that need modification
- Design mockups if applicable

---

## File Locations Quick Reference

```
📁 Project Root
│
├── 📁 app/src/main/java/com/runanywhere/startup_hackathon20/
│   │
│   ├── 📁 data/
│   │   ├── 📁 models/
│   │   │   └── 📄 RPGModels.kt ← Rank definitions
│   │   └── 📁 repository/
│   │       ├── 📄 RPGRepository.kt ← Video URLs, XP logic
│   │       └── 📄 EduVentureRepository.kt ← User data
│   │
│   ├── 📁 ui/screens/
│   │   ├── 📄 RPGStudentScreen.kt ← Home + Navigation
│   │   ├── 📄 ModuleVideoScreen.kt ← Video player
│   │   ├── 📄 ProfileDashboardScreen.kt ← Profile
│   │   ├── 📄 LeaderboardScreen.kt ← Rankings
│   │   ├── 📄 ResourcesScreen.kt ← Resources
│   │   ├── 📄 AIChatScreen.kt ← Sir Remi chat
│   │
│   ├── 📁 viewmodel/
│   │   └── 📄 EduVentureViewModel.kt ← App logic
│   │
│   └── 📄 MainActivity.kt ← App entry point
│
└── 📄 IMPLEMENTATION_STATUS.md ← Full status
```

---

## Quick Commands

### Find All Color Definitions

```bash
# PowerShell
Select-String -Path "app/src/main/java/**/*.kt" -Pattern "Color\(0x"
```

### Count Total Lines

```bash
# PowerShell
(Get-ChildItem -Recurse -Include *.kt | Get-Content | Measure-Object -Line).Lines
```

### Find TODOs

```bash
# PowerShell
Select-String -Path "app/src/main/java/**/*.kt" -Pattern "TODO"
```

---

## Testing Checklist

Before each release:

- [ ] ✅ Login as Student works
- [ ] ✅ All 5 bottom tabs load
- [ ] ✅ Video plays in Module 1
- [ ] ✅ Module completion increases XP
- [ ] ✅ Weapon upgrades at thresholds
- [ ] ✅ Rank changes at XP milestones
- [ ] ✅ Leaderboard displays
- [ ] ✅ Resources screen loads
- [ ] ✅ Sir Remi chat responds
- [ ] ✅ Profile shows correct stats
- [ ] ✅ Logout returns to login
- [ ] ✅ No crashes on back button

---

## Performance Tips

### For Smooth Navigation

- Keep composable functions small
- Use `remember` for expensive calculations
- Avoid nested LazyColumns

### For Video Performance

- Use WebView's hardware acceleration
- Enable DOM storage for better caching
- Consider video quality settings

### For Chat Performance

- Limit chat history size
- Use streaming for AI responses
- Clear old messages periodically

---

## Last Updated

Implementation Complete
**Version**: 1.0
**Status**: Production Ready 

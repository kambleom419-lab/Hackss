# 📹 Video Setup Guide for EduVenture RPG

## Where to Paste Your YouTube Video Links

### Location

All video URLs need to be added in:

```
app/src/main/java/com/runanywhere/startup_hackathon20/data/repository/RPGRepository.kt
```

## Video Configuration Format

### Current Placeholder Format

Each module currently has this placeholder:

```kotlin
videoUrl = "PASTE_YOUTUBE_URL_HERE_FOR_SETS"
```

### How to Add Your Videos

Replace the placeholder with your actual YouTube video URL in one of these formats:

#### Option 1: Direct Video URL

```kotlin
videoUrl = "https://www.youtube.com/watch?v=VIDEO_ID"
```

#### Option 2: YouTube Embed URL

```kotlin
videoUrl = "https://www.youtube.com/embed/VIDEO_ID"
```

#### Option 3: Direct MP4 URL (if you have hosted videos)

```kotlin
videoUrl = "https://your-server.com/video.mp4"
```

## Math Route - 6 Modules

### Module 1: Sets

**Line**: ~57 in RPGRepository.kt

```kotlin
QuestModule(
    id = "math_module_1",
    moduleNumber = 1,
    title = "Module 1: Sets",
    topic = "Sets - The Foundation",
    enemyName = "The Necromancer of Sets",
    videoUrl = "PASTE_YOUR_SETS_VIDEO_URL_HERE",  // ← PASTE HERE
    videoStartTime = 0,     // Start time in seconds
    videoEndTime = null     // End time in seconds (null = full video)
)
```

### Module 2: Relations and Functions

**Line**: ~67 in RPGRepository.kt

```kotlin
videoUrl = "PASTE_YOUR_RELATIONS_VIDEO_URL_HERE",  // ← PASTE HERE
```

### Module 3: Trigonometric Functions

**Line**: ~76 in RPGRepository.kt

```kotlin
videoUrl = "PASTE_YOUR_TRIGONOMETRY_VIDEO_URL_HERE",  // ← PASTE HERE
```

### Module 4: Complex Numbers

**Line**: ~85 in RPGRepository.kt

```kotlin
videoUrl = "PASTE_YOUR_COMPLEX_NUMBERS_VIDEO_URL_HERE",  // ← PASTE HERE
```

### Module 5: Quadratic Functions

**Line**: ~94 in RPGRepository.kt

```kotlin
videoUrl = "PASTE_YOUR_QUADRATIC_VIDEO_URL_HERE",  // ← PASTE HERE
```

### Module 6: Linear Inequalities (BOSS)

**Line**: ~103 in RPGRepository.kt

```kotlin
videoUrl = "PASTE_YOUR_LINEAR_INEQUALITIES_VIDEO_URL_HERE",  // ← PASTE HERE
```

## Video Timestamps

If you want to play only a portion of a video:

### Set Start and End Times

```kotlin
QuestModule(
    // ... other fields
    videoUrl = "https://www.youtube.com/watch?v=YOUR_VIDEO_ID",
    videoStartTime = 120,    // Start at 2:00 (120 seconds)
    videoEndTime = 300       // End at 5:00 (300 seconds)
)
```

### Examples

#### Example 1: Full Video

```kotlin
videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
videoStartTime = 0,
videoEndTime = null  // Plays entire video
```

#### Example 2: Specific Segment

```kotlin
videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
videoStartTime = 60,   // Start at 1:00
videoEndTime = 180     // End at 3:00
```

## Complete Example

Here's what a complete module configuration looks like:

```kotlin
QuestModule(
    id = "math_module_1",
    moduleNumber = 1,
    title = "Module 1: Sets",
    topic = "Sets - The Foundation",
    enemyName = "The Necromancer of Sets",
    enemyDescription = "The Necromancer of Sets has been raiding the village...",
    enemyLevel = 5,
    xpReward = 50,
    videoUrl = "https://www.youtube.com/watch?v=YOUR_ACTUAL_VIDEO_ID",
    videoStartTime = 0,
    videoEndTime = null,
    isCompleted = false,
    isBoss = false
)
```

## YouTube Video ID Extraction

From this URL:

```
https://www.youtube.com/watch?v=dQw4w9WgXcQ
```

The video ID is: `dQw4w9WgXcQ`

You can use it as:

```kotlin
videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
```

## Testing

### Before Adding Videos

When you click a module, you'll see a placeholder screen that says:

```
📹
Video will appear here
Paste YouTube URL in RPGRepository
```

### After Adding Videos

Once you add the URLs and rebuild:

1. Click any module
2. The video player will load with your video
3. Watch the lesson
4. Click "DEFEAT [ENEMY NAME]" to complete
5. Earn XP and return to the quest route

## Supported Video Formats

The app uses Media3 ExoPlayer which supports:

- ✅ YouTube URLs (with proper extraction)
- ✅ Direct MP4 links
- ✅ HLS streams (.m3u8)
- ✅ DASH streams
- ✅ Most standard video formats

## Note on YouTube

For production apps, YouTube videos typically require using the YouTube Android Player API or
embedding through WebView. For testing purposes, you can:

1. Use direct MP4 video URLs
2. Use hosted videos from your own server
3. Implement YouTube iframe player (requires additional setup)

## Quick Setup Steps

1. **Open the file**:
   ```
   app/src/main/java/com/runanywhere/startup_hackathon20/data/repository/RPGRepository.kt
   ```

2. **Find the `createMathRoute()` function** (starts around line 39)

3. **Replace each placeholder** with your video URLs:
    - Line ~57: Module 1 - Sets
    - Line ~67: Module 2 - Relations and Functions
    - Line ~76: Module 3: - Trigonometric Functions
    - Line ~85: Module 4 - Complex Numbers
    - Line ~94: Module 5 - Quadratic Functions
    - Line ~103: Module 6 - Linear Inequalities

4. **Save the file**

5. **Rebuild the app** (Clean and Build in Android Studio)

6. **Test**: Click any module to see the video play

## Troubleshooting

### Video Not Playing?

- Check if URL is valid
- Ensure internet connection
- Try a direct MP4 URL first for testing
- Check Android Logcat for errors

### Video Shows Black Screen?

- YouTube URLs may need special handling
- Try a direct video file URL
- Check video format compatibility

### Video Controls Not Working?

- ExoPlayer controls should appear automatically
- Tap the video to show/hide controls
- Use device volume buttons

## Example with All Videos

```kotlin
private fun createMathRoute(): LearningRoute {
    return LearningRoute(
        // ... route details
        modules = listOf(
            QuestModule(
                id = "math_module_1",
                // ... other fields
                videoUrl = "https://your-server.com/sets-lesson.mp4",
                videoStartTime = 0,
                videoEndTime = null
            ),
            QuestModule(
                id = "math_module_2",
                // ... other fields
                videoUrl = "https://your-server.com/relations-lesson.mp4",
                videoStartTime = 0,
                videoEndTime = null
            ),
            // ... continue for all 6 modules
        )
    )
}
```

---

## 🎯 Summary

**File to Edit**: `RPGRepository.kt`
**Lines to Update**: 6 video URLs (one per module)
**Format**: Replace `"PASTE_YOUTUBE_URL_HERE_FOR_X"` with your actual URL
**Rebuild**: Required after changes

**Need Help?** The video player will show a placeholder until you add real URLs!

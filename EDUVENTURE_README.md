# 🎓 EduVenture - Gamified AI-Assisted Learning Ecosystem

EduVenture is a comprehensive gamified learning platform that reimagines education through
interactive design, psychology-based motivation systems, and real-time analytics powered by
on-device AI.

## 🌟 Overview

EduVenture merges the best of gamification, artificial intelligence, and educational psychology to
create an engaging learning experience for both students and teachers. The platform runs entirely
on-device using the RunAnywhere SDK, ensuring privacy and offline capability.

## ✨ Key Features

### 👨‍🎓 For Students

#### 🎮 Gamified Quest System

- **Learning Quests**: Structured learning paths with multiple challenges
- **Experience Points (XP)**: Earn XP by completing quests, challenges, and engaging with content
- **Level Progression**: Watch your level increase as you master subjects
- **Difficulty Tiers**: Easy, Medium, Hard, and Expert quests for progressive learning
- **Progress Tracking**: Visual progress bars for each quest and challenge

#### 🏆 Achievement System

- **Unlockable Achievements**: Earn badges and rewards for milestones
- **Streaks**: Build daily learning streaks to maintain motivation
- **Leaderboards**: Compete with peers (future feature)
- **Profile Statistics**: Comprehensive stats dashboard

#### 🤖 AI Study Companion

- **Personalized Chat**: Ask questions and get educational responses
- **Study Note Generation**: Upload materials and get AI-generated notes
- **Flashcard Creation**: Automatically generate flashcards from your content
- **Quiz Generation**: Create practice quizzes from study materials
- **Context-Aware Assistance**: AI understands your learning context

#### 📚 Study Materials Management

- Upload text, PDFs, images, and video materials
- AI-powered content analysis
- Organize by subject and topic
- Spaced repetition for flashcard reviews

### 👩‍🏫 For Teachers

#### 📊 Student Progress Dashboard

- **Real-Time Analytics**: Monitor student engagement and performance
- **Individual Student Profiles**: Detailed progress for each student
- **Class Performance Metrics**: Average scores, completion rates, active students
- **Strength & Weakness Identification**: AI-powered insights into student performance
- **Activity Logs**: Track recent student activities and achievements

#### 🎓 Professional Development

- **Gamified Courses**: Teachers earn XP through professional development
- **Module-Based Learning**: Structured courses with multiple modules
- **Progress Tracking**: Monitor your own professional growth
- **XP & Levels**: Advance your teaching level

#### 📤 Resource Hub

- **Shared Resources**: Access a library of teaching materials
- **Community Contributions**: Share lesson plans, worksheets, and activities
- **Ratings & Reviews**: Community-driven quality assessment
- **Subject Organization**: Browse resources by subject and type

#### 🏫 Class Management

- Create and manage multiple classes
- Track student engagement
- Monitor class-wide statistics
- Identify students needing additional support

## 🏗️ Architecture

### Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **AI Engine**: RunAnywhere SDK with LLaMA.cpp
- **Async Programming**: Kotlin Coroutines & Flow
- **Serialization**: kotlinx.serialization

### Project Structure

```
app/src/main/java/com/runanywhere/startup_hackathon20/
├── data/
│   ├── models/          # Data models for all entities
│   │   ├── User.kt      # User, Achievement, Quest models
│   │   ├── StudyMaterial.kt  # Materials, Notes, Flashcards, Quizzes
│   │   └── TeacherModels.kt  # Teacher courses, resources, analytics
│   └── repository/
│       └── EduVentureRepository.kt  # Central data management
├── viewmodel/
│   └── EduVentureViewModel.kt  # Main ViewModel with AI integration
├── ui/
│   ├── navigation/
│   │   └── Navigation.kt  # Navigation definitions
│   ├── screens/
│   │   ├── LoginScreen.kt        # Role selection
│   │   ├── StudentHomeScreen.kt  # Student dashboard
│   │   ├── TeacherHomeScreen.kt  # Teacher dashboard
│   │   └── AIChatScreen.kt       # AI chat interface
│   └── theme/           # App theming
└── MainActivity.kt      # App entry point
```

## 🎨 Design Philosophy

### Gamification Elements

1. **XP System**: Immediate reward for learning activities
2. **Level Progression**: Visual representation of mastery
3. **Achievements**: Milestone-based recognition
4. **Streaks**: Encourage consistent engagement
5. **Progress Bars**: Clear visualization of completion
6. **Difficulty Badges**: Color-coded challenge indicators

### Psychology-Based Motivation

- **Intrinsic Motivation**: Focus on mastery and growth
- **Immediate Feedback**: Real-time progress updates
- **Goal Setting**: Clear, achievable objectives
- **Social Recognition**: Achievements and leaderboards
- **Autonomy**: Self-paced learning paths

### Modern UX Design

- **Material Design 3**: Modern, accessible interface
- **Gradient Accents**: Visually appealing color schemes
- **Card-Based Layout**: Organized, scannable content
- **Responsive Design**: Adaptive to different screen sizes
- **Smooth Animations**: Polished user experience

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 24+ (Target SDK 36)
- Kotlin 2.0.21+
- Minimum 4GB RAM on device (for AI model)

### Installation

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd Hackss
   ```

2. **Open in Android Studio**
    - Open Android Studio
    - Select "Open an Existing Project"
    - Navigate to the project directory

3. **Sync Gradle**
    - Android Studio will automatically sync dependencies
    - Wait for the process to complete

4. **Download AI Model** (First Run)
    - Launch the app
    - Select a role (Student or Teacher)
    - Tap "AI Model" in the chat screen
    - Download the AI model (374MB)
    - Load the model

5. **Run the App**
    - Connect an Android device or start an emulator
    - Click "Run" (Shift+F10)

## 📱 Usage Guide

### For Students

1. **Login**: Select "Student" on the login screen
2. **Dashboard**: View your XP, level, streak, and active quests
3. **Start a Quest**: Tap on any quest to view details
4. **Complete Challenges**: Work through videos, readings, practice problems, and quizzes
5. **Earn XP**: Complete challenges to earn experience points
6. **Unlock Achievements**: Reach milestones to unlock badges
7. **Use AI Chat**: Ask questions or request study materials
8. **Generate Content**: Upload materials to create notes, flashcards, or quizzes

### For Teachers

1. **Login**: Select "Teacher" on the login screen
2. **Dashboard**: View your classes and student statistics
3. **Monitor Students**: Check individual progress and performance
4. **Professional Development**: Complete courses to earn XP
5. **Browse Resources**: Access shared teaching materials
6. **Use AI Assistant**: Get help with lesson planning or content creation

## 🔮 Future Enhancements

### Planned Features

- **Real Backend Integration**: Firebase/Supabase for data persistence
- **Real-Time Collaboration**: Live quizzes and group challenges
- **Video Integration**: Embedded educational videos
- **File Upload**: Full PDF and document processing
- **Advanced Analytics**: ML-powered insights and predictions
- **Leaderboards**: Global and class-based rankings
- **Social Features**: Student interaction and peer learning
- **Parental Dashboard**: Progress monitoring for parents
- **Custom Quest Creation**: Teachers create custom learning paths
- **Adaptive Learning**: AI-adjusted difficulty based on performance
- **Offline Mode**: Full functionality without internet
- **Multi-Language Support**: Internationalization

### Technical Improvements

- Navigation Component integration
- Room Database for local persistence
- WorkManager for background tasks
- Comprehensive testing suite
- Performance optimizations
- Accessibility enhancements

## 🎯 Data Models

### Core Models

#### User

```kotlin
data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole,  // STUDENT or TEACHER
    val xp: Int,
    val level: Int,
    val achievements: List<Achievement>,
    val completedQuests: List<String>,
    val streak: Int,
    val subjects: List<String>
)
```

#### Quest

```kotlin
data class Quest(
    val id: String,
    val title: String,
    val description: String,
    val subject: String,
    val difficulty: QuestDifficulty,  // EASY, MEDIUM, HARD, EXPERT
    val xpReward: Int,
    val challenges: List<Challenge>,
    val isCompleted: Boolean,
    val progress: Float
)
```

#### StudyMaterial

```kotlin
data class StudyMaterial(
    val id: String,
    val userId: String,
    val title: String,
    val subject: String,
    val content: String,
    val type: MaterialType,  // TEXT, PDF, IMAGE, VIDEO
    val notes: List<StudyNote>,
    val flashcards: List<Flashcard>,
    val quizzes: List<Quiz>
)
```

## 🔐 Privacy & Security

- **On-Device AI**: All AI processing happens locally - no data sent to servers
- **Local Data**: Current implementation uses in-memory storage
- **No Analytics Tracking**: No user behavior tracking
- **Open Source**: Transparent codebase

## 🤝 Contributing

Contributions are welcome! Areas for contribution:

- New quest content
- Achievement ideas
- UI/UX improvements
- Bug fixes
- Feature enhancements
- Documentation improvements

## 📄 License

This project is built using the RunAnywhere SDK. Please refer to SDK documentation for licensing
terms.

## 🙏 Acknowledgments

- **RunAnywhere SDK**: For providing on-device AI capabilities
- **Material Design**: For design guidelines and components
- **Jetpack Compose**: For modern Android UI framework
- **LLaMA**: For the underlying AI model architecture

## 📞 Support

For issues, questions, or suggestions:

- Create an issue in the repository
- Review the RunAnywhere SDK documentation
- Check the QUICK_START_ANDROID.md guide

## 🎓 Educational Philosophy

EduVenture is built on the principle that learning should be:

- **Engaging**: Gamification keeps students motivated
- **Personalized**: AI adapts to individual needs
- **Empowering**: Students control their learning pace
- **Data-Driven**: Teachers get actionable insights
- **Accessible**: On-device AI works offline

---

**Made with ❤️ for educators and learners everywhere**

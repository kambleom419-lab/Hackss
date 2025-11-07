# EduVenture - Application Flow

## 🎬 User Journey

### Initial Launch

```
App Launch
    ↓
Login Screen (Role Selection)
    ├─→ Student Path
    └─→ Teacher Path
```

## 👨‍🎓 Student Flow

### 1. Login Screen

**Visual Description:**

- Beautiful gradient background (Indigo → Purple → Pink)
- Large graduation cap emoji (🎓)
- "EduVenture" title in large, bold white text
- Tagline: "Gamified Learning Reimagined"
- Two large white cards with shadows:
    - 📚 Student card with description
    - 👩‍🏫 Teacher card with description

**Actions:**

- Tap Student card → Navigate to Student Home

---

### 2. Student Home Screen

#### Tab 1: Dashboard (Default)

**Top Bar:**

- "EduVenture" title (white text on indigo background)
- Chat icon (AI Study Companion)
- Profile icon

**Content:**

- **User Stats Card** (White card with shadow)
    - "Welcome back, [Name]"
    - Circular gradient badge showing Level
    - XP progress bar with color gradient
    - Progress text: "XP: 450" and "45/100 to Level 6"

- **Streak Card** (Yellow/amber background)
    - 🔥 Fire emoji
    - "7 Day Streak!"
    - "Keep up the great work!"

- **Active Quests Section**
    - Section title: "Active Quests"
    - Quest cards showing:
        - Quest title and subject
        - Difficulty badge (color-coded)
        - Progress bar
        - XP reward

- **Recent Achievements**
    - Horizontal scrollable row
    - Small cards with emoji icons
    - Achievement names

**Bottom Navigation:**

- 🏠 Home (selected)
- ⭐ Quests
- 📄 Materials

---

#### Tab 2: Quests

**Content:**

- "All Quests" title
- Full list of available quests
- Each quest card shows:
    - Title (e.g., "Master Linear Equations")
    - Subject tag
    - Difficulty badge (Easy/Medium/Hard/Expert)
    - Progress percentage
    - XP reward amount
    - Progress bar visualization

**Interaction:**

- Tap any quest card → View quest details (placeholder)

---

#### Tab 3: Materials

**Content:**

- Centered placeholder screen
- 📚 Large emoji
- "Study Materials" title
- Description text explaining AI features

---

### 3. AI Chat Screen

**Top Bar:**

- "AI Study Companion" title
- Status message (model status)
- Back button
- "AI Model" button

**Model Selector Panel** (Collapsible)

- Shows available AI models
- Download button (if not downloaded)
- Load button (if downloaded)
- Progress bar during download
- Current model indicator

**Chat Area:**

- Empty state:
    - 🤖 Robot emoji
    - "Hi! I'm your AI Study Companion"
    - Welcome message
- Messages displayed in bubbles:
    - User messages: Indigo background, right-aligned
    - AI messages: White background, left-aligned
    - Rounded corners with chat-bubble style

**Input Area:**

- Text field: "Ask me anything about your studies..."
- Send button (indigo circular button with send icon)
- Disabled if no model loaded

**Features:**

- Real-time streaming responses
- Auto-scroll to latest message
- XP earned for chat interactions

---

## 👩‍🏫 Teacher Flow

### 1. Teacher Home Screen

#### Tab 1: Dashboard

**Top Bar:**

- "EduVenture - Teacher" title (white on purple background)
- AI Assistant icon
- Profile icon

**Content:**

- **Teacher Stats Card**
    - "Welcome, Prof. Sarah Johnson"
    - Circular gradient badge (Purple → Pink) showing Level
    - Professional Development XP display

- **Your Classes Section**
    - Class cards showing:
        - Class name
        - Student count
        - Quick stats boxes:
            - Average Level
            - Average Score
            - Active Students (last 24h)

**Bottom Navigation:**

- 🏠 Dashboard (selected)
- ⚙️ Courses
- 👤 Students
- ℹ️ Resources

---

#### Tab 2: Courses (Professional Development)

**Content:**

- "Professional Development" title
- Course cards showing:
    - Course title
    - Category tag
    - Progress bar
    - XP reward
    - Module count
    - Completion percentage

**Example Courses:**

- "Effective Classroom Management" (Pedagogy)
- "Digital Tools for Education" (Technology)

---

#### Tab 3: Students

**Content:**

- "Student Progress" title
- Organized by class
- Each class section shows:
    - Class name header
    - Student progress cards with:
        - Student name
        - Level and XP
        - Average score badge (green)
        - Stats: Quests, Strengths, Focus Areas

**Student Details Include:**

- Performance metrics
- Learning strengths
- Areas needing focus
- Recent activity

---

#### Tab 4: Resources

**Content:**

- "Shared Resources" title
- Resource cards showing:
    - 📄 Document emoji
    - Resource title
    - Creator name ("by Dr. Emily Chen")
    - Subject tag (purple badge)
    - Rating (⭐ 4.8)
    - Download count (↓ 156)

**Resource Types:**

- Lesson Plans
- Worksheets
- Presentations
- Assessments

---

## 🎨 Visual Design Language

### Color System

- **Student Mode**: Indigo (#6366F1) primary color
- **Teacher Mode**: Purple (#8B5CF6) primary color
- **Success/Positive**: Green (#10B981)
- **Warning**: Amber (#F59E0B)
- **Error**: Red (#EF4444)
- **Background**: Light gray (#F9FAFB)
- **Cards**: White with subtle shadows

### Typography

- **Titles**: Bold, 24-48sp
- **Headings**: Bold, 16-20sp
- **Body**: Regular, 14sp
- **Captions**: Regular, 12sp

### Components

- **Cards**: Rounded corners (12-16dp), elevated shadows
- **Badges**: Pill-shaped with colored backgrounds
- **Progress Bars**: Rounded, gradient colors
- **Buttons**: Rounded, filled or outlined
- **Icons**: Material Design icons

### Spacing

- Card padding: 16-20dp
- Section spacing: 16dp
- List item spacing: 8-12dp
- Screen padding: 16dp

---

## 🔄 State Transitions

### Login → Student Home

1. Tap "Student" card
2. ViewModel calls `loginAsStudent()`
3. Repository creates student user
4. Current user state updates
5. UI recomposes to show Student Home

### Student Home → AI Chat

1. Tap chat icon in top bar
2. `currentScreen` state updates to "ai_chat"
3. UI recomposes to show AI Chat screen
4. Model selector available if no model loaded

### AI Model Download Flow

1. Tap "AI Model" button
2. Model selector panel expands
3. Tap "Download" on model
4. Progress bar shows download percentage
5. "Load" button appears when complete
6. Tap "Load"
7. Status updates to "AI Ready!"
8. Chat input becomes enabled

### Quest Interaction

1. Browse quests in Dashboard or Quests tab
2. Tap quest card
3. Quest details screen (placeholder)
4. Complete challenges
5. Progress bar updates
6. XP awarded
7. Level up if threshold reached
8. Achievement unlocked (if milestone reached)

---

## 📊 Data Flow

### User State Management

```
Repository (Single Source of Truth)
    ↓
StateFlow<User?>
    ↓
ViewModel observes
    ↓
Composables observe StateFlow
    ↓
UI recomposes on state changes
```

### XP & Level System

```
User Action (Complete Quest)
    ↓
ViewModel.completeQuest()
    ↓
Repository.completeQuest()
    ↓
Repository.earnXP()
    ↓
Calculate new level (XP / 100 + 1)
    ↓
Update user state
    ↓
Check for achievements
    ↓
UI updates with animation
```

### AI Chat Flow

```
User types message
    ↓
Tap send button
    ↓
Add user message to chat
    ↓
ViewModel.sendChatMessage()
    ↓
RunAnywhere.generateStream()
    ↓
Tokens stream in
    ↓
Update AI message in real-time
    ↓
Complete response
    ↓
Award XP for interaction
```

---

## 🎯 User Engagement Loops

### Daily Engagement Loop

1. User logs in → See daily streak
2. Check dashboard → Active quests visible
3. Start a quest → Progress toward XP goal
4. Complete challenges → Immediate XP feedback
5. Level up or unlock achievement → Dopamine hit
6. Return tomorrow → Maintain streak

### Learning Loop

1. View quest → Clear learning objective
2. Complete video/reading → Absorb knowledge
3. Practice problems → Apply learning
4. Quiz → Test understanding
5. AI chat → Clarify doubts
6. Achievement unlocked → Mastery recognition

### Teacher Engagement Loop

1. Check dashboard → See class overview
2. Review student progress → Identify needs
3. Access resources → Find materials
4. Complete PD course → Earn XP
5. Monitor impact → Student progress improves

---

## 🔐 Privacy Features

- **Local AI**: All processing on-device
- **No Login Required**: Mock data for demonstration
- **No Data Collection**: No analytics or tracking
- **Offline Capable**: Works without internet (after model download)

---

## 🚀 Performance Characteristics

- **Fast Navigation**: Instant screen transitions
- **Smooth Scrolling**: Optimized LazyColumn/LazyRow
- **Real-time Updates**: StateFlow ensures immediate UI updates
- **Efficient Memory**: Composables only recompose when needed
- **Battery Friendly**: AI only runs when actively used

---

**Status**: Complete and functional application flow with beautiful, modern UI and engaging
gamification features.

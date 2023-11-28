package com.example.learningapp.data

import androidx.compose.ui.graphics.Color


data class Conversation(
    var color: Color,
    var title: String,
    var emoji: String,
)

var dummy = listOf(Conversation(
    color = Color(190, 173, 250),
    title = "Job",
    emoji = "☕",
),Conversation(
    color = Color(255, 128, 128),
    title = "Family",
    emoji = "\uD83D\uDE80",
),Conversation(
    color = Color(218, 221, 177),
    title = "Travel",
    emoji = "❤️",
),Conversation(
    color = Color(210, 224, 251),
    title = "School",
    emoji = "⚽",
))


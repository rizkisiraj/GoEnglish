package com.example.learningapp.data

import androidx.compose.ui.graphics.Color


data class Conversation(
    var color: Color,
    var title: String,
    var category: Array<String>,
    var emoji: String,
    var description: String,
    var author: String,
)

var dummy = listOf(Conversation(
    color = Color(190, 173, 250),
    title = "Speaking",
    category = arrayOf<String>("Casual", "Relaxing"),
    emoji = "☕",
    description = "Cafe talk is a delightful social activity where you and your friend engage in conversation over a cup of coffee or tea in a cozy cafe setting. The ambiance is often relaxed, filled with the comforting aroma of freshly brewed beverages and the gentle hum of background chatter. As you sip your drinks, you share stories, discuss life events, and enjoy each other's company in a laid-back atmosphere, creating a warm and inviting space for friendship and conversation.",
    author = "Nabil Najmudin"
),Conversation(
    color = Color(255, 128, 128),
    title = "Listening",
    category = arrayOf<String>("Sci-Fi", "Space"),
    emoji = "\uD83D\uDE80",
    description = "Astro Boy, also known as Tetsuwan Atomu or Mighty Atom, is a fictional character and the protagonist of Osamu Tezuka's manga series of the same name. First introduced in 1952, Astro Boy is a robot boy with incredible strength, advanced artificial intelligence, and a heart that mirrors human emotions.",
    author = "Naela Fauzul"
),Conversation(
    color = Color(218, 221, 177),
    title = "Reading",
    category = arrayOf<String>("Romance", "Nature"),
    emoji = "❤️",
    description = "Astro Boy, also known as Tetsuwan Atomu or Mighty Atom, is a fictional character and the protagonist of Osamu Tezuka's manga series of the same name. First introduced in 1952, Astro Boy is a robot boy with incredible strength, advanced artificial intelligence, and a heart that mirrors human emotions.",
    author = "Jane Dawson"
),Conversation(
    color = Color(210, 224, 251),
    title = "Writing",
    category = arrayOf<String>("Sport", "Playful"),
    emoji = "⚽",
    description = "Astro Boy, also known as Tetsuwan Atomu or Mighty Atom, is a fictional character and the protagonist of Osamu Tezuka's manga series of the same name. First introduced in 1952, Astro Boy is a robot boy with incredible strength, advanced artificial intelligence, and a heart that mirrors human emotions.",
    author = "John Dawson"
))


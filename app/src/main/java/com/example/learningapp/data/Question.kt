package com.example.learningapp.data

import com.example.learningapp.R

data class Question(
    val lottieAnimation: String? = null,
    val question: String = "",
    val answer: String = "",
    val choices: List<String> = listOf(),
    val sound: String? = null,
    val type: String = "",
)
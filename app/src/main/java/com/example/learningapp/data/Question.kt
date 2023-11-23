package com.example.learningapp.data

import com.example.learningapp.R

data class Question(
    val lottieAnimation: Int? = null,
    val question: String,
    val answer: String,
    val choices: Array<String> = arrayOf(),
    val sound: Int? = null,
    val type: QuestionType,

)

enum class QuestionType {
    LISTENING,READING,SPEAKING
}

var questionsDummy = listOf<Question>(
    Question(
        R.raw.lottie,
        question = "She lives in New York",
        answer = "Dia tinggal di New York",
        choices = arrayOf<String>("Dia bekerja di New York", "Dia tinggal di New York", "Saya tinggal di New York", "Kamu bersekolah di New York"),
        type = QuestionType.READING
    ),
    Question(
        question = "She is a girl",
        answer = "She is a girl",
        choices = arrayOf<String>(),
        sound = R.raw.sentence_1,
        type = QuestionType.LISTENING
    ),
    Question(
        R.raw.cat,
        question = "I live with My Grandmother",
        answer = "I live with My Grandmother",
        choices = arrayOf<String>(),
        type = QuestionType.SPEAKING
    ),
)
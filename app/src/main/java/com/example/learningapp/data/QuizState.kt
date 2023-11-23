package com.example.learningapp.data

data class QuizState(
    var answerRightnow: String = "",
    var index: Int = 0,
    var questions: List<Question> = questionsDummy,
    var questionRightNow: Question = questions[index]
)

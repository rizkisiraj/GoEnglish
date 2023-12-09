package com.example.learningapp.data

import androidx.compose.runtime.snapshots.SnapshotStateList

data class QuizState(
    var answerRightnow: String = "",
    var index: Int = 0,
    var questions: SnapshotStateList<Question?> = SnapshotStateList(),
    var questionRightNow: Question? = Question(),

    var listeningQuestions: Int = 0,
    var speakingQuestions: Int = 0,
    var readingQuestions: Int = 0,

    var listeningScore: Int = 0,
    var readingScore: Int = 0,
    var speakingScore: Int = 0,

    var chapter: String = "",
    var title: String = ""
)

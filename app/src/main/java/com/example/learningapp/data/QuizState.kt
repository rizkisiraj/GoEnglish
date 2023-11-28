package com.example.learningapp.data

import androidx.compose.runtime.snapshots.SnapshotStateList

data class QuizState(
    var answerRightnow: String = "",
    var index: Int = 0,
    var questions: SnapshotStateList<Question?> = SnapshotStateList(),
    var questionRightNow: Question? = Question(),
    var score: Int = 0
)

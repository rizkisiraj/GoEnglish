package com.example.learningapp

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.learningapp.data.Question
import com.example.learningapp.data.QuizState
import com.example.learningapp.utils.VoiceToTextParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel(voiceToTextParser: VoiceToTextParser) {
    var application = voiceToTextParser

    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    fun OnAnswerClick() {
        if(_quizState.value.index >= _quizState.value.questions.size - 1 ) {
            _quizState.update { uiState ->
                uiState.copy(
                    index = uiState.index + 1,
                )
            }
            return
        } else {
            _quizState.update { uiState ->
                uiState.copy(
                    index = uiState.index + 1,
                    answerRightnow = "",
                    questionRightNow = uiState.questions[uiState.index + 1],
                )
            }
        }
    }

    fun updateScore() {
        _quizState.update { uiState ->
            uiState.copy(
                score = uiState.score + 1
            )
        }
    }

    fun getIndex(): Int {
        if(_quizState.value.index >= _quizState.value.questions.size ) {
            return -1
        } else {
            return _quizState.value.index
        }
    }

    fun updateQuestionList(snapshotStateList: SnapshotStateList<Question?>) {
        _quizState.update {uiState ->
            uiState.copy(
                questions = snapshotStateList,
            )
        }

        _quizState.update {uiState ->
            uiState.copy(
                questionRightNow = uiState.questions[0],
            )
        }
    }

    fun getQuestion(): Question? {
        return _quizState.value.questionRightNow
    }

    fun getScore(): Int {
        return _quizState.value.score
    }

    fun getTotalQuestion(): Int {
        return _quizState.value.questions.size
    }

//    fun getApplication(): VoiceToTextParser {
//        return application
//    }
}
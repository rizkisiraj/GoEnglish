package com.example.learningapp.viewmodel

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

    fun updateScore(type: String) {
        when(type) {
            "Listening" -> _quizState.update { uiState ->
                uiState.copy(
                    listeningScore = uiState.listeningScore + 1
                )
            }
            "Speaking" -> _quizState.update { uiState ->
                uiState.copy(
                    speakingScore = uiState.speakingScore + 1
                )
            }
            "Reading" -> _quizState.update { uiState ->
                uiState.copy(
                    readingScore = uiState.readingScore + 1
                )
            }
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
        var totalReadingQuestions = 0
        var totalSpeakingQuestions = 0
        var totalListeningQuestions = 0

        for(question in snapshotStateList) {
            when(question?.type) {
                "Reading" -> totalReadingQuestions++
                "Listening" -> totalListeningQuestions++
                "Speaking" -> totalSpeakingQuestions++
            }
        }

        _quizState.update {uiState ->
            uiState.copy(
                questions = snapshotStateList,
                listeningQuestions = totalListeningQuestions,
                readingQuestions = totalReadingQuestions,
                speakingQuestions = totalSpeakingQuestions
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

    fun getScore(): Array<Int> {
        val listeningScore: Int = _quizState.value.listeningScore
        val speakingScore: Int = _quizState.value.speakingScore
        val readingScore : Int = _quizState.value.readingScore

        return arrayOf(listeningScore, speakingScore, readingScore)
    }

    fun getTotalQuestion(): Array<Int> {
        val listeningQuestions: Int = _quizState.value.listeningQuestions
        val speakingQuestions: Int = _quizState.value.speakingQuestions
        val readingQuestions : Int = _quizState.value.readingQuestions

        return arrayOf(listeningQuestions, speakingQuestions, readingQuestions)
    }

//    fun getApplication(): VoiceToTextParser {
//        return application
//    }
}
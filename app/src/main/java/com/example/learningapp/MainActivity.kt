package com.example.learningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.learningapp.components.Coba
import com.example.learningapp.screen.ProfileScreen
import com.example.learningapp.screen.SuccessScreen
import com.example.learningapp.screen.quiz.QuizSpeaking
import com.example.learningapp.ui.theme.LearningAppTheme
import com.example.learningapp.utils.VoiceToTextParser

class MainActivity : ComponentActivity() {

    val voiceToTextParser by lazy {
        VoiceToTextParser(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningAppTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    MainScreen(voiceToTextParser = voiceToTextParser)
                }
            }
        }
    }
}
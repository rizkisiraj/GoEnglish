package com.example.learningapp.screen

import android.media.MediaPlayer
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learningapp.BottomNavScreen
import com.example.learningapp.viewmodel.QuizViewModel
import com.example.learningapp.R
import com.example.learningapp.components.BoxCard
import com.example.learningapp.components.DuolingoButton

@Composable
fun SuccessScreen(navController: NavController, quizViewModel: QuizViewModel) {
    val scoreList: Array<Int> = quizViewModel.getScore()
    val totalQuestion: Array<Int> = quizViewModel.getTotalQuestion()
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.success_dance))
    var scoreListening by remember { mutableStateOf(0) }
    var scoreReading by remember { mutableStateOf(0) }
    var scoreSpeaking by remember { mutableStateOf(0) }
    val listeningCounter by animateIntAsState(
        targetValue = scoreListening,
        animationSpec = tween(
            durationMillis = 5000,
            easing = FastOutSlowInEasing
        )
    )
    val speakingCounter by animateIntAsState(
        targetValue = scoreSpeaking,
        animationSpec = tween(
            durationMillis = 5000,
            easing = FastOutSlowInEasing
        )
    )
    val readingCounter by animateIntAsState(
        targetValue = scoreReading,
        animationSpec = tween(
            durationMillis = 5000,
            easing = FastOutSlowInEasing
        )
    )

    val nMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.finished)

    LaunchedEffect(Unit) {
        scoreListening = (scoreList[0]/totalQuestion[0])*100
        scoreReading = (scoreList[1]/totalQuestion[1])*100
        scoreSpeaking = (scoreList[2]/totalQuestion[2])*100
        nMediaPlayer.start()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(composition = composition, modifier = Modifier.size(400.dp))
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Kuis Telah Selesai!",
                color = Color(0xFFFFB000),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Ayo rayakan ini!",
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(24.dp))
                BoxCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = listeningCounter.toString())
                Spacer(modifier = Modifier.width(24.dp))
                BoxCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = "\uD83D\uDCD8 ${readingCounter}")
                Spacer(modifier = Modifier.width(16.dp))
                BoxCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = "\uD83D\uDDE3️ ${speakingCounter}")
                Spacer(modifier = Modifier.width(24.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            DuolingoButton(modifier = Modifier.fillMaxWidth(0.9f), text = "SELESAI", type = "jawaban") {
                navController.navigate(BottomNavScreen.Home.route) {
                        popUpTo("Finished") {
                            inclusive = true
                        }
                }
            }
        }

    }
}
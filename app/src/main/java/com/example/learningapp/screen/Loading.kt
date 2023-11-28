package com.example.learningapp.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learningapp.QuizViewModel
import com.example.learningapp.R
import com.example.learningapp.utils.FirebaseRepositories
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(quizViewModel: QuizViewModel, navController: NavController) {
    val repository = FirebaseRepositories()
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_animation))

    LaunchedEffect(key1 = Unit) {
        // this is a suspend function
        var listQuestion = repository.getQuestions("aVzdekthMXdyKDsWW3Vp")
        quizViewModel.updateQuestionList(listQuestion)

        Log.d("Question", quizViewModel.getQuestion()!!.type)

        if(quizViewModel.getQuestion()?.type == "Reading") {
            navController.navigate("Reading")
        } else if(quizViewModel.getQuestion()?.type == "Listening") {
            navController.navigate("Listening")
        } else {
            navController.navigate("Speaking")
        }
    }

//    LaunchedEffect(true) {
//        // Introduce a delay of 3 seconds
//        delay(3000)
//        navController.navigate("Reading")
//    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(300.dp)
        )
    }
}
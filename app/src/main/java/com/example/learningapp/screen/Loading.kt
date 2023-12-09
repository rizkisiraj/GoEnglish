package com.example.learningapp.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learningapp.viewmodel.QuizViewModel
import com.example.learningapp.R
import com.example.learningapp.utils.FirebaseRepositories

@Composable
fun LoadingScreen(quizViewModel: QuizViewModel, navController: NavController, refId: String) {
    val repository = FirebaseRepositories()
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_animation))

    LaunchedEffect(key1 = Unit) {
        // this is a suspend function
        var listQuestion = repository.getQuestions(refId)
        quizViewModel.updateQuestionList(listQuestion)

//        Log.d("anjay", "${refId} l");
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
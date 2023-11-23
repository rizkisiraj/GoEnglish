package com.example.learningapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learningapp.BottomNavScreen
import com.example.learningapp.R
import com.example.learningapp.components.DuolingoButton
import com.example.learningapp.ui.theme.BlueVolume

@Composable
fun SuccessScreen(navController: NavController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.success_animation))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueVolume)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.size(300.dp))
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Selesai", fontSize = MaterialTheme.typography.headlineLarge.fontSize, fontWeight = FontWeight.SemiBold, color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
            DuolingoButton(modifier = Modifier.fillMaxWidth(0.8f), text = "LANJUT", type = "jawaban") {
                navController.navigate(BottomNavScreen.Home.route) {
                        popUpTo("Finished") {
                            inclusive = true
                        }
                }
            }
        }

    }
}
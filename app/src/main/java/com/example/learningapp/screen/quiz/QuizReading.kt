package com.example.learningapp.screen.quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learningapp.BottomNavScreen
import com.example.learningapp.QuizViewModel
import com.example.learningapp.components.BoxCard
import com.example.learningapp.components.DuolingoButton
import com.example.learningapp.R
import com.example.learningapp.data.Question
import com.example.learningapp.ui.theme.GreenBackground
import com.example.learningapp.ui.theme.RedBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizReading(quizViewModel: QuizViewModel, navController: NavController) {
    val questionObj: Question? = quizViewModel.getQuestion()
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie))
    var answerRightNow by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pilih terjemahan yang benar",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(150.dp)
                )
                BoxCard(modifier = Modifier.fillMaxWidth(), text = questionObj!!.question)
            }
            Divider()
            Spacer(modifier = Modifier.height(24.dp))
            Spacer(modifier = Modifier.height(32.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(questionObj!!.choices) { choice ->
                    DuolingoButton(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        text = choice,
                        type = if (answerRightNow == choice) "jawaban" else "",
                        onClick = {
                            answerRightNow = choice
                        })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            DuolingoButton(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = "PERIKSA",
                type = "periksa"
            ) {
                showBottomSheet = true
            }
        }
        if(showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = if(questionObj!!.answer.lowercase() == answerRightNow.lowercase()) GreenBackground else RedBackground
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (questionObj.answer.lowercase() == answerRightNow.lowercase()) {
                        Text(
                            "Selamat, jawabanmu benar!",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DuolingoButton(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            text = "Lanjut",
                            type = "periksa"
                        ) {
                            quizViewModel.updateScore(questionObj.type)
                            quizViewModel.OnAnswerClick()
                            var indexSekarang = quizViewModel.getIndex()
                            showBottomSheet = false

                            if(indexSekarang < 0) {
                                navController.navigate("Finished")
                                return@DuolingoButton
                            } else {
                                var type = quizViewModel.getQuestion()?.type
                                navController.navigate(type!!) {
                                    popUpTo("Finished") {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            "Sayang sekali, kamu belum berhasil :(",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DuolingoButton(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            text = "Coba Lagi",
                            type = "gagal"
                        ) {
                            showBottomSheet = false
                        }
                    }

                }
            }
        }
    }

}
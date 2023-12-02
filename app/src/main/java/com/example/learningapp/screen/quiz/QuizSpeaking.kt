package com.example.learningapp.screen.quiz

import android.media.MediaPlayer
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learningapp.QuizViewModel
import com.example.learningapp.R
import com.example.learningapp.components.BlueButton
import com.example.learningapp.components.BoxCard
import com.example.learningapp.components.DuolingoButton
import com.example.learningapp.data.Question
import com.example.learningapp.ui.theme.GreenBackground
import com.example.learningapp.ui.theme.RedBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizSpeaking(quizViewModel: QuizViewModel, navController: NavController) {
    var voiceToTextParser = quizViewModel.application
    val questionObj: Question? = quizViewModel.getQuestion()
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.cat))

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var canRecord by remember {
        mutableStateOf(false)
    }
    val mContext = LocalContext.current
    val nMediaPlayer: MediaPlayer = MediaPlayer.create(mContext, R.raw.success)

    var recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {isGranted ->
            canRecord = isGranted
        })

    DisposableEffect(key1 = voiceToTextParser) {
        onDispose {
            voiceToTextParser.resetState()
        }
    }

    LaunchedEffect(key1 = recordAudioLauncher) {
        recordAudioLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
    }

    val state by voiceToTextParser.state.collectAsState()

    LaunchedEffect(state.spokenText) {
        if(state.spokenText.isNotBlank()) {
            showBottomSheet = true
        }
        if(state.spokenText.lowercase() == questionObj?.answer?.lowercase()) {
            nMediaPlayer.start()
        }
    }

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
                text = "Ucapkan kalimat yang tepat",
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
                LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.size(150.dp))
                BoxCard(modifier = Modifier.fillMaxWidth(), text = questionObj!!.answer)
            }
            Divider()
            Spacer(modifier = Modifier.height(32.dp))
            BlueButton(modifier = Modifier.fillMaxWidth(0.8f), isSpeaking = state.isSpeaking) {
                if(state.isSpeaking) {
                    voiceToTextParser.stopListening()
                } else {
                    voiceToTextParser.startListening()
                }
            }
        }
        if(showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = if(state.spokenText.lowercase() == questionObj!!.answer.lowercase()) GreenBackground else RedBackground
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(state.spokenText.lowercase() == questionObj.answer.lowercase()) {
                        Text("Selamat, jawabanmu benar!", fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        DuolingoButton(modifier = Modifier.fillMaxWidth(0.8f), text = "Lanjut", type = "periksa") {
                            quizViewModel.updateScore(questionObj.type)
                            quizViewModel.OnAnswerClick()
                            var indexSekarang = quizViewModel.getIndex()
                            showBottomSheet = false

                            if(indexSekarang < 0) {
                                nMediaPlayer.release()
                                navController.navigate("Finished")
                                return@DuolingoButton
                            } else {
                                voiceToTextParser.resetState()
                                var type = quizViewModel.getQuestion()?.type
                                navController.navigate(type!!) {
                                    popUpTo("Finished") {
                                        inclusive = true
                                    }
                                }
                            }


                        }
                    } else {
                        Text("Sayang sekali, kamu belum berhasil :(", fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        DuolingoButton(modifier = Modifier.fillMaxWidth(0.8f), text = "Coba Lagi", type = "gagal") {
                            showBottomSheet = false
                        }
                    }
                }
            }
        }
    }
}

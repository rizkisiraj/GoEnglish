package com.example.learningapp.screen.quiz

import android.content.res.AssetManager
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learningapp.QuizViewModel
import com.example.learningapp.components.DuolingoButton
import com.example.learningapp.components.VolumeButton
import com.example.learningapp.ui.theme.BlueShadow
import com.example.learningapp.ui.theme.GreenBackground
import com.example.learningapp.ui.theme.RedBackground
import java.io.FileInputStream
import java.io.InputStream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizListening(quizViewModel: QuizViewModel, navController: NavController) {
    val questionObj = quizViewModel.getQuestion()
    val soundPath: String? = if(questionObj?.sound != null) questionObj.sound else ""

    var answerRightNow by remember { mutableStateOf("") }
    val mContext = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var nMediaPlayer = MediaPlayer()

    var resId = LocalContext.current.resources.getIdentifier(soundPath,"raw","com.example.learningapp")

    if(questionObj?.sound != null) {
        nMediaPlayer = MediaPlayer.create(mContext, resId)
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
                text = "Tulislah kalimat yang tepat",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                VolumeButton(modifier = Modifier, size = 80.dp, icon = Icons.Filled.VolumeUp) {
                    nMediaPlayer.setPlaybackParams(nMediaPlayer.getPlaybackParams().setSpeed(1f))
                    nMediaPlayer.start()
                }
                Spacer(Modifier.width(16.dp))
                VolumeButton(modifier = Modifier, size = 48.dp, icon = Icons.Filled.VolumeDown) {
                    nMediaPlayer.setPlaybackParams(nMediaPlayer.getPlaybackParams().setSpeed(0.5f))
                    nMediaPlayer.start()
                }

            }
            Spacer(modifier = Modifier.height(24.dp))
            Divider()
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = answerRightNow,
                onValueChange = { answerRightNow = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { showBottomSheet = true }
                ),
                label = { Text("Your Answer") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = BlueShadow
                ),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            DuolingoButton(modifier = Modifier.fillMaxWidth(0.9f), text = "PERIKSA", type = "periksa") {
                showBottomSheet = true
            }
        }
        if(showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = if(questionObj?.answer?.lowercase() == answerRightNow.lowercase()) GreenBackground else RedBackground
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (questionObj?.answer?.lowercase() == answerRightNow.lowercase()) {
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
                            quizViewModel.updateScore()
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
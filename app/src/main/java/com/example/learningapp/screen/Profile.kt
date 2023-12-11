package com.example.learningapp.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learningapp.R
import com.example.learningapp.components.OutlineBox
import com.example.learningapp.data.Activity
import com.example.learningapp.viewmodel.AppViewModelProvider
import com.example.learningapp.viewmodel.GPTViewModel
import com.example.learningapp.viewmodel.HomeViewModel
import java.util.Date

@Composable
@Preview
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Analisa()
    }
}

@Composable
fun Analisa(
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    gptViewModel: GPTViewModel = viewModel()
){

    val text1 = listOf("7+", "A+", "8")
    val homeUiState by homeViewModel.allActivites.collectAsState()
    val sortedActivities = homeUiState.itemList.sortedBy { it.createdDate }
    val userStreak = calculateStreak(sortedActivities)
    val datas: () -> List<String>  = {
        listOf<String>(userStreak.toString(), calculateGrades(userActivities = homeUiState.itemList), calculateChapters(userActivities = homeUiState.itemList).toString())
    }
    val myState: String = gptViewModel.myMutableState.value

    val dataResults = datas.invoke()

    DisposableEffect(homeUiState.itemList) {
        if (homeUiState.itemList.isNotEmpty()) {
            val PRE_PROMPT =
                "Supposed that you are a friendly assistant, that gives suggestions based on user English proficiency score. GIVE SIMPLE SUGGESTIONS AND FOCUS ON THE SECTION THE USER NEEDS TO IMPROVE"
            var combinedListeningScore = ""
            var combinedReadingScore = ""
            var combinedSpeakingScore = ""

            for ((index, activity) in homeUiState.itemList.withIndex()) {
                if (index == homeUiState.itemList.size - 1) {
                    combinedListeningScore += "${activity.listeningScore}"
                    combinedReadingScore += "${activity.readingScore}"
                    combinedSpeakingScore += "${activity.speakingScore}"
                } else {
                    combinedListeningScore += "${activity.listeningScore},"
                    combinedReadingScore += "${activity.readingScore},"
                    combinedSpeakingScore += "${activity.speakingScore},"
                }
            }

            gptViewModel.sendMessage(PRE_PROMPT + "\n$combinedListeningScore\n$combinedReadingScore\n$combinedSpeakingScore")
        }

        onDispose {  }
    }

    val text2 = listOf("Day Streak", "Grade", "Completed")
    val colors = listOf<CardColor>(
        CardColor(Color(0xFFE2F2FF), Color(0xFF53ADF0)),
        CardColor(Color(0xFFE2FFE3), Color(0xFF7CF053)),
        CardColor(Color(0xFFFFFCE2), Color(0xFFF0EA53))
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(25.dp)
            .fillMaxSize()
            .padding(top = 65.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        //Ikon Kucink
        Image(
            painter = painterResource(id = R.drawable.kucing_profil),
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .align(Alignment.CenterHorizontally),
        )

        //Tulisan Atas
        Text(
            text = "Statistik Saya",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 17.dp)
        )

        //Kotak 3
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 23.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(text1.size) { index ->
                OutlineBox(
                    modifier = Modifier.weight(1f),
                    color = colors[index].container,
                    shadowColor = colors[index].shadowColor
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            dataResults[index],
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text2[index],
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }

        //Garis
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp) // Lebar garis vertikal
                .background(Color.Gray)
        )


        //Tulisan Bawah
        Text(
            text = gptViewModel.myMutableState.value,
            fontSize = 13.sp,
            textAlign = TextAlign.Justify,
            lineHeight = 25.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 65.dp)
        )
    }
}

fun calculateStreak(activities: List<Activity>): Int {
    var streak = 0
    var currentStreak = 0
    var previousDate: Date? = null

    for (activity in activities) {
        val currentDate = activity.createdDate

        // Check if the current date is consecutive with the previous date
        if (previousDate != null && isConsecutiveDays(previousDate, currentDate)) {
            currentStreak++
        } else {
            // Reset streak if the current date is not consecutive
            currentStreak = 1
        }

        // Update the streak if the current streak is greater than the overall streak
        if (currentStreak > streak) {
            streak = currentStreak
        }

        previousDate = currentDate
    }

    return streak
}

fun calculateGrades(userActivities: List<Activity>): String {
    if(userActivities.isEmpty()) {
        return "D"
    }

    var totalRate = 0
    for(activity in userActivities) {
        val totalScore = (activity.listeningScore + activity.speakingScore + activity.readingScore)/3
        totalRate += totalScore
    }
    totalRate /= userActivities.size
    if(totalRate >= 90) {
        return "A"
    } else if(totalRate >= 80) {
        return "B"
    } else if(totalRate >= 60) {
        return "C"
    } else {
        return "D"
    }
}

fun isConsecutiveDays(previousDate: Date, currentDate: Date): Boolean {
    val oneDayInMillis = 24 * 60 * 60 * 1000 // 1 day in milliseconds
    return currentDate.time - previousDate.time <= oneDayInMillis
}
fun calculateChapters(userActivities: List<Activity>): Int {
    val completedChapters = mutableSetOf<Pair<String, String>>()
    for (activity in userActivities) {
        val chapterName = activity.chapter
        val topicName = activity.name

        // Check if the chapter and topic pair has already been completed
        val chapterTopicPair = Pair(chapterName, topicName)
        if (!completedChapters.contains(chapterTopicPair)) {
           completedChapters.add(chapterTopicPair)
        }
    }
    return completedChapters.size
}

data class CardColor(
    val container: Color,
    val shadowColor: Color,
)
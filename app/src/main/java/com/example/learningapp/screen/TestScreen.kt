package com.example.learningapp.screen
//
//import android.util.Log
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.runtime.snapshots.SnapshotStateList
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.learningapp.data.Activity
//import com.example.learningapp.data.Question
//import com.example.learningapp.utils.FirebaseRepositories
//import com.example.learningapp.viewmodel.ActivityViewModel
//import com.example.learningapp.viewmodel.AppViewModelProvider
//import com.example.learningapp.viewmodel.HomeViewModel
//import kotlinx.coroutines.launch
//import java.util.Date
//
//@Composable
//fun SaveScreen() {
//    val activityViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
//    val homeUiState by activityViewModel.allActivites.collectAsState()
//    val coroutineScope = rememberCoroutineScope()
//
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Button(
//            onClick = {
//                coroutineScope.launch {
//                    activityViewModel.saveActivity(
//                        Activity(
//                            createdDate = Date(),
//                            listeningScore = 100,
//                            readingScore = 100,
//                            speakingScore = 100,
//                        )
//                    )
//                }
//            }
//        ) {
//            Text(text = "Siraj")
//        }
//        LazyColumn() {
//            if(homeUiState.itemList.isEmpty()) {
//                item {
//                    Text("Aduh kosong...")
//                }
//            } else {
//                items(homeUiState.itemList) { activity ->
//                    Text(activity.listeningScore.toString())
//                }
//            }
//        }
//    }
//}
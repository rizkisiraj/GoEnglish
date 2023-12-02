package com.example.learningapp.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.learningapp.data.Question
import com.example.learningapp.utils.FirebaseRepositories

//@Composable
//fun CobaFirestore() {
//    val repository = FirebaseRepositories()
//    var listAngjay = remember {SnapshotStateList<Question?>()}
//
//    LaunchedEffect(key1 = Unit) {
//        // this is a suspend function
//        listAngjay = repository.getQuestions("9tyIMF8xZ6dLSbHQwVZg")
//        for(i in listAngjay) {
//            Log.d("siraj", i!!.answer)
//        }
//    }
//
//    LazyColumn {
//        item {
//            Text("Bapakkao")
//        }
//        items(listAngjay) {document ->
//            document?.let { Text(it.answer) }
//        }
//    }
//}
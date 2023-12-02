package com.example.learningapp.utils

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.learningapp.data.Question
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseRepositories {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getQuestions(topicId: String): SnapshotStateList<Question?> {
        val collectionPath = "topics/${topicId}/questions"
        var courseList = mutableStateListOf<Question?>()

        try {
            val snapshot = firestore.collection(collectionPath).get().await()
            for (document in snapshot) {
                val doc: Question? = document.toObject(Question::class.java)
                courseList.add(doc)
            }
            return courseList
        } catch (e: Exception) {
            Log.e("Error", "Firebase Error", e)
            // Handle exceptions (e.g., log or throw a custom exception)
            return courseList
        }
    }
}
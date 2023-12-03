package com.example.learningapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Activities")
data class Activity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var listeningScore: Int = 0,
    var readingScore: Int = 0,
    var speakingScore: Int = 0,
)

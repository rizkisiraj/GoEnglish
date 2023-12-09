package com.example.learningapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "Activities")
data class Activity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var listeningScore: Int = 0,
    var readingScore: Int = 0,
    var speakingScore: Int = 0,
    var createdDate: Date = Date(),
    var chapter: String = "",
    var name: String = "",
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
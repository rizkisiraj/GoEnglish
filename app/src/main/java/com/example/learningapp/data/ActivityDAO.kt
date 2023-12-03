package com.example.learningapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Activity)

    @Update
    suspend fun update(item: Activity)

    @Delete
    suspend fun delete(item: Activity)

    @Query("SELECT * from activities WHERE id = :id")
    fun getItem(id: Int): Flow<Activity>

    @Query("SELECT * from activities ORDER BY id ASC")
    fun getAllItems(): Flow<List<Activity>>
}
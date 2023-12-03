package com.example.learningapp.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Activity] from a given data source.
 */
interface ActivitiesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllActivitiesStream(): Flow<List<Activity>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getActivityStream(id: Int): Flow<Activity?>

    /**
     * Insert item in the data source
     */
    suspend fun insertActivity(item: Activity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteActivity(item: Activity)

    /**
     * Update item in the data source
     */
    suspend fun updateActivity(item: Activity)
}
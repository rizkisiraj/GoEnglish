package com.example.learningapp.data

import kotlinx.coroutines.flow.Flow

class OfflineActivitiesRepository(private val ActivityDao: ActivityDao) : ActivitiesRepository {
    override fun getAllActivitiesStream(): Flow<List<Activity>> = ActivityDao.getAllItems()

    override fun getActivityStream(id: Int): Flow<Activity?> = ActivityDao.getItem(id)

    override suspend fun insertActivity(item: Activity) = ActivityDao.insert(item)

    override suspend fun deleteActivity(item: Activity) = ActivityDao.delete(item)

    override suspend fun updateActivity(item: Activity) = ActivityDao.update(item)
}
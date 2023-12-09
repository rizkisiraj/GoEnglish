package com.example.learningapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningapp.data.ActivitiesRepository
import com.example.learningapp.data.Activity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ResultViewModel(private val activitiesRepository: ActivitiesRepository): ViewModel() {
    suspend fun saveItem(activity: Activity) {
            activitiesRepository.insertActivity(activity)
    }

}

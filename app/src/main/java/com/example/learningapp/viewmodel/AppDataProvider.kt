package com.example.learningapp.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.learningapp.LearningApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            HomeViewModel(learningApplication().container.activitiesRepository)
        }

        initializer {
            ResultViewModel(learningApplication().container.activitiesRepository)
        }
        // Initializer for ItemEntryViewModel
    }
}
fun CreationExtras.learningApplication(): LearningApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LearningApplication)

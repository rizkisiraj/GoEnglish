package com.example.learningapp

import android.app.Application
import com.example.learningapp.data.AppContainer
import com.example.learningapp.data.AppDataContainer

class LearningApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
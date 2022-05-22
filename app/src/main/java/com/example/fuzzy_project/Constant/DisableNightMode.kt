package com.example.fuzzy_project.Constant

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class DisableNightMode : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
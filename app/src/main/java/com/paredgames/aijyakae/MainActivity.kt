package com.paredgames.aijyakae

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Text

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.paredgames.aijyakae.ui.composables.BeforeLoginComposable
import com.paredgames.aijyakae.ui.screens.HomeScreen.StartScreen
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            AijyakaeTheme {
                BeforeLoginComposable()
            }
        }
    }
}
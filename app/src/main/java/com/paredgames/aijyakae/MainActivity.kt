package com.paredgames.aijyakae

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.paredgames.aijyakae.ui.composables.beforelogin.StartScreenBeforeLogin

import com.paredgames.aijyakae.ui.theme.AijyakaeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            AijyakaeTheme {
                StartScreenBeforeLogin()
            }
        }
    }
}
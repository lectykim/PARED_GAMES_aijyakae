package com.paredgames.aijyakae

import android.content.Context
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

import com.paredgames.aijyakae.ui.composables.beforelogin.StartScreenBeforeLogin

import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel


class MainActivity : ComponentActivity() {

    val pref = getSharedPreferences("key",0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        val beforeLoginViewModel:BeforeLoginViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[BeforeLoginViewModel::class.java]
        setContent {
            AijyakaeTheme {
                StartScreenBeforeLogin(beforeLoginViewModel)
            }
        }
    }
}
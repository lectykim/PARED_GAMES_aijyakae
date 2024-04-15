package com.paredgames.aijyakae

import android.content.Context
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent

import com.paredgames.aijyakae.ui.composables.beforelogin.StartScreenBeforeLogin
import com.paredgames.aijyakae.ui.composables.makejyakae.StartScreenMakeJyakae

import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import java.util.prefs.Preferences


class MainActivity : ComponentActivity() {


    private val beforeLoginViewModel:BeforeLoginViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[BeforeLoginViewModel::class.java]
    val sharedPref = getSharedPreferences("isFirst",0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        val isFirst = sharedPref.getBoolean("isFirst",false)
        setContent {
            AijyakaeTheme {
                if(isFirst)
                    StartScreenBeforeLogin(beforeLoginViewModel)
                else
                    StartScreenMakeJyakae()
            }
        }
    }
}
package com.paredgames.aijyakae

import android.app.Application
import android.content.Context
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.util.ScreenInfo

import com.paredgames.aijyakae.ui.composables.beforelogin.StartScreenBeforeLogin
import com.paredgames.aijyakae.ui.composables.makejyakae.StartScreenMakeJyakae
import com.paredgames.aijyakae.ui.nav.AijyakaeNavHost

import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {



    private val beforeLoginViewModel : BeforeLoginViewModel by viewModels { BeforeLoginViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("isFirst",0)
        installSplashScreen()
        val isFirst = sharedPref.getBoolean("isFirst",true)

        setContent {
            AijyakaeTheme {
                if(isFirst){
                    AijyakaeNavHost(
                        startDestination = ScreenInfo.BeforeLogin,
                        beforeLoginViewModel = beforeLoginViewModel
                    )
                }
                else{
                    AijyakaeNavHost(
                        startDestination = ScreenInfo.MakeJyakae,
                        beforeLoginViewModel = beforeLoginViewModel
                    )
                }

            }
        }
    }
}
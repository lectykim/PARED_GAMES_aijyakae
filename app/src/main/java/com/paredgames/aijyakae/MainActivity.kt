package com.paredgames.aijyakae

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.paredgames.aijyakae.data.api.ApiService
import com.paredgames.aijyakae.data.config.ApiConfig
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.repository.BeforeLoginRepository
import com.paredgames.aijyakae.data.util.ScreenInfo
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys

import com.paredgames.aijyakae.ui.composables.beforelogin.StartScreenBeforeLogin
import com.paredgames.aijyakae.ui.composables.makejyakae.StartScreenMakeJyakae
import com.paredgames.aijyakae.ui.nav.AijyakaeNavHost

import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModelFactory
import retrofit2.Retrofit
import retrofit2.create
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {



    private lateinit var beforeLoginViewModel:BeforeLoginViewModel;
    private lateinit var beforeLoginViewModelFactory: BeforeLoginViewModelFactory
    private lateinit var apiService: ApiService
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofit=ApiConfig.getInstance()
        apiService=retrofit.create(ApiService::class.java)
        beforeLoginViewModelFactory  = BeforeLoginViewModelFactory(BeforeLoginRepository(apiService,this))
        beforeLoginViewModel = ViewModelProvider(this,beforeLoginViewModelFactory)[BeforeLoginViewModel::class.java]



        installSplashScreen()
        val isFirst= beforeLoginViewModel.getPreferenceData(SharedPreferenceDataKeys.IS_LOGIN_KEY,"false")

        setContent {
            AijyakaeTheme {
                if(isFirst == "false"){
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
package com.paredgames.aijyakae

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import com.paredgames.aijyakae.data.repository.MakeJyakaeRepository
import com.paredgames.aijyakae.data.util.ScreenInfo
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys

import com.paredgames.aijyakae.ui.composables.beforelogin.StartScreenBeforeLogin
import com.paredgames.aijyakae.ui.composables.makejyakae.StartScreenMakeJyakae
import com.paredgames.aijyakae.ui.nav.AijyakaeNavHost

import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModelFactory
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModelFactory
import retrofit2.Retrofit
import retrofit2.create
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {


    private val multiplePermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach { (permission, isGranted) ->
            when {
                isGranted -> {
                    // 권한이 승인된 경우 처리할 작업
                }
                !isGranted -> {
                    finish()
                    // 권한이 거부된 경우 처리할 작업
                }
                else -> {
                    finish()
                    // 사용자가 "다시 묻지 않음"을 선택한 경우 처리할 작업
                }
            }
        }
        // multiple permission 처리에 대한 선택적 작업
        // - 모두 허용되었을 경우에 대한 code
        // - 허용되지 않은 Permission에 대한 재요청 code
    }

    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    private lateinit var beforeLoginViewModel:BeforeLoginViewModel;
    private lateinit var beforeLoginViewModelFactory: BeforeLoginViewModelFactory
    private lateinit var makeJyakaeViewModel: MakeJyakaeViewModel;
    private lateinit var makeJyakaeViewModelFactory: MakeJyakaeViewModelFactory
    private lateinit var apiService: ApiService
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofit=ApiConfig.getInstance()
        apiService=retrofit.create(ApiService::class.java)
        beforeLoginViewModelFactory  = BeforeLoginViewModelFactory(BeforeLoginRepository(apiService,this))
        beforeLoginViewModel = ViewModelProvider(this,beforeLoginViewModelFactory)[BeforeLoginViewModel::class.java]
        makeJyakaeViewModelFactory = MakeJyakaeViewModelFactory(MakeJyakaeRepository(apiService,this))
        makeJyakaeViewModel = ViewModelProvider(this,makeJyakaeViewModelFactory)[MakeJyakaeViewModel::class.java]


        installSplashScreen()
        val isFirst= beforeLoginViewModel.getPreferenceData(SharedPreferenceDataKeys.IS_LOGIN_KEY,"false")

        multiplePermissionsLauncher.launch(permissions)
        setContent {
            AijyakaeTheme {
                if(isFirst == "false"){
                    AijyakaeNavHost(
                        startDestination = ScreenInfo.BeforeLogin,
                        beforeLoginViewModel = beforeLoginViewModel,
                        makeJyakaeViewModel = makeJyakaeViewModel
                    )
                }
                else{
                    AijyakaeNavHost(
                        startDestination = ScreenInfo.MakeJyakae,
                        beforeLoginViewModel = beforeLoginViewModel,
                        makeJyakaeViewModel = makeJyakaeViewModel
                    )
                }

            }
        }
    }
}
package com.paredgames.aijyakae

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.paredgames.aijyakae.data.api.ApiService
import com.paredgames.aijyakae.data.config.ApiConfig
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.repository.BeforeLoginRepository
import com.paredgames.aijyakae.data.repository.ImageDownloadManager
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
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback

class MainActivity : ComponentActivity() {


    private lateinit var beforeLoginViewModel:BeforeLoginViewModel;
    private lateinit var beforeLoginViewModelFactory: BeforeLoginViewModelFactory
    private lateinit var makeJyakaeViewModel: MakeJyakaeViewModel;
    private lateinit var makeJyakaeViewModelFactory: MakeJyakaeViewModelFactory
    private lateinit var apiService: ApiService
    private lateinit var retrofit: Retrofit
    private var rewardedAd: RewardedAd? = null
    private final var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofit=ApiConfig.getInstance()
        apiService=retrofit.create(ApiService::class.java)
        beforeLoginViewModelFactory  = BeforeLoginViewModelFactory(BeforeLoginRepository(apiService,this,
            ImageDownloadManager(this)
        ))
        beforeLoginViewModel = ViewModelProvider(this,beforeLoginViewModelFactory)[BeforeLoginViewModel::class.java]
        makeJyakaeViewModelFactory = MakeJyakaeViewModelFactory(MakeJyakaeRepository(apiService,this))
        makeJyakaeViewModel = ViewModelProvider(this,makeJyakaeViewModelFactory)[MakeJyakaeViewModel::class.java]


        installSplashScreen()
        val isFirst= beforeLoginViewModel.getPreferenceData(SharedPreferenceDataKeys.IS_LOGIN_KEY,"false")

        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(this,"ca-app-pub-1036203527902832/3427992892",adRequest,object:RewardedAdLoadCallback(){

            override fun onAdLoaded(ad: RewardedAd) {
                Log.d(TAG, "Ad was loaded.")
                rewardedAd = ad
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString().let { Log.d(TAG, it) }
                rewardedAd = null
            }
        })

        rewardedAd?.let { ad ->
            ad.show(this, OnUserEarnedRewardListener { rewardItem ->
                // Handle the reward.
                val rewardAmount = rewardItem.amount
                val rewardType = rewardItem.type
                Log.d(TAG, "User earned the reward.")
            })
        } ?: run {
            Log.d(TAG, "The rewarded ad wasn't ready yet.")
        }

        setContent {
            AijyakaeTheme {
                if(isFirst == "false"){
                    AijyakaeNavHost(
                        startDestination = ScreenInfo.BeforeLogin,
                        beforeLoginViewModel = beforeLoginViewModel,
                        makeJyakaeViewModel = makeJyakaeViewModel,

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
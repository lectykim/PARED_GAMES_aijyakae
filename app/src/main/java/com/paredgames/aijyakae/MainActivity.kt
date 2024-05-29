package com.paredgames.aijyakae

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.queryProductDetails
import com.google.android.gms.ads.AdRequest
import com.paredgames.aijyakae.data.api.AijyakaeServerApiService
import com.paredgames.aijyakae.data.api.DeepLApiService
import com.paredgames.aijyakae.data.api.ModelsLabApiService
import com.paredgames.aijyakae.data.billing.BillingManager
import com.paredgames.aijyakae.data.config.ApiConfig
import com.paredgames.aijyakae.data.repository.ArtBoardRepository
import com.paredgames.aijyakae.data.repository.BeforeLoginRepository
import com.paredgames.aijyakae.data.repository.ImageDownloadManager
import com.paredgames.aijyakae.data.repository.MakeJyakaeRepository
import com.paredgames.aijyakae.data.util.ScreenInfo
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys

import com.paredgames.aijyakae.ui.nav.AijyakaeNavHost

import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModelFactory
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModelFactory
import retrofit2.Retrofit
import com.paredgames.aijyakae.data.util.ApiLinks
import com.paredgames.aijyakae.ui.composables.makejyakae.getPermission
import com.paredgames.aijyakae.ui.viewmodel.ArtBoardViewModel
import com.paredgames.aijyakae.ui.viewmodel.ArtBoardViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {


    private lateinit var beforeLoginViewModel:BeforeLoginViewModel;
    private lateinit var beforeLoginViewModelFactory: BeforeLoginViewModelFactory
    private lateinit var makeJyakaeViewModel: MakeJyakaeViewModel;
    private lateinit var makeJyakaeViewModelFactory: MakeJyakaeViewModelFactory
    private lateinit var artBoardViewModel: ArtBoardViewModel
    private lateinit var artBoardViewModelFactory: ArtBoardViewModelFactory
    private lateinit var modelsLabApiService: ModelsLabApiService
    private lateinit var deepLApiService: DeepLApiService
    private lateinit var aijyakaeServerApiService: AijyakaeServerApiService
    private lateinit var modelsLabRetrofit: Retrofit
    private lateinit var deepLRetrofit: Retrofit
    private lateinit var aijyakaeServerRetrofit: Retrofit



    /*billingClient.queryProductDetailsAsync(queryProductDetailsParams) {
        billingResult,
        productDetailsList ->
        // check billingResult
        // process returned productDetailsList
    }*/



    private final var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var billingManager = BillingManager(applicationContext,this)
        modelsLabRetrofit=ApiConfig.getInstance(ApiLinks.MODELS_LAB)
        deepLRetrofit = ApiConfig.getInstance(ApiLinks.DEEPL)
        aijyakaeServerRetrofit = ApiConfig.getInstance(ApiLinks.AIJYAKAE_SERVER)
        modelsLabApiService=modelsLabRetrofit.create(ModelsLabApiService::class.java)
        deepLApiService = deepLRetrofit.create(DeepLApiService::class.java)
        aijyakaeServerApiService = aijyakaeServerRetrofit.create(AijyakaeServerApiService::class.java)
        beforeLoginViewModelFactory  = BeforeLoginViewModelFactory(BeforeLoginRepository(modelsLabApiService,deepLApiService,this,
            ImageDownloadManager(this)
        ))
        beforeLoginViewModel = ViewModelProvider(this,beforeLoginViewModelFactory)[BeforeLoginViewModel::class.java]
        makeJyakaeViewModelFactory = MakeJyakaeViewModelFactory(MakeJyakaeRepository(modelsLabApiService,deepLApiService,aijyakaeServerApiService,this,
            ImageDownloadManager(this),billingManager,this))
        makeJyakaeViewModel = ViewModelProvider(this,makeJyakaeViewModelFactory)[MakeJyakaeViewModel::class.java]
        artBoardViewModelFactory = ArtBoardViewModelFactory(ArtBoardRepository(aijyakaeServerApiService,this))
        artBoardViewModel = ViewModelProvider(this,artBoardViewModelFactory)[ArtBoardViewModel::class.java]



        installSplashScreen()

        getPermission()
        val isFirst= beforeLoginViewModel.getPreferenceData(SharedPreferenceDataKeys.IS_LOGIN_KEY,"false")
        val adRequest = AdRequest.Builder().build()
        /*
        RewardedAd.load(this,"ca-app-pub-1036203527902832/3427992892",adRequest,object:RewardedAdLoadCallback(){

            override fun onAdLoaded(ad: RewardedAd) {
                Log.d(TAG, "Ad was loaded.")
                rewardedAd = ad
                rewardedAd?.show(this@MainActivity, OnUserEarnedRewardListener {rewardItem->
                    // Handle the reward.
                    val rewardAmount = rewardItem.amount
                    val rewardType = rewardItem.type
                    Log.d(TAG, "User earned the reward.")
                })
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString().let { Log.d(TAG, it) }
                rewardedAd = null

            }
        })
        */



        setContent {
            AijyakaeTheme {
                if(isFirst == "false"){
                    AijyakaeNavHost(
                        startDestination = ScreenInfo.BeforeLogin,
                        beforeLoginViewModel = beforeLoginViewModel,
                        makeJyakaeViewModel = makeJyakaeViewModel,
                        artBoardViewModel = artBoardViewModel
                    )
                }
                else{

                    AijyakaeNavHost(
                        startDestination = ScreenInfo.MakeJyakae,
                        beforeLoginViewModel = beforeLoginViewModel,
                        makeJyakaeViewModel = makeJyakaeViewModel,
                        artBoardViewModel = artBoardViewModel

                    )
                }

            }
        }



    }
}
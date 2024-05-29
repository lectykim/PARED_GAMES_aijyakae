package com.paredgames.aijyakae.ui.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paredgames.aijyakae.data.util.ScreenInfo
import com.paredgames.aijyakae.ui.composables.artboard.StartScreenArtBoard
import com.paredgames.aijyakae.ui.composables.artboard.StartScreenArtBoardDetail
import com.paredgames.aijyakae.ui.composables.beforelogin.StartScreenBeforeLogin
import com.paredgames.aijyakae.ui.composables.makejyakae.StartScreenMakeJyakae
import com.paredgames.aijyakae.ui.viewmodel.ArtBoardViewModel
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel


@Composable
    fun AijyakaeNavHost(
        modifier: Modifier=Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination:ScreenInfo=ScreenInfo.BeforeLogin,
        beforeLoginViewModel:BeforeLoginViewModel,
        makeJyakaeViewModel: MakeJyakaeViewModel,
        artBoardViewModel: ArtBoardViewModel
        //rewardedAd: RewardedAd
    ) {

        NavHost(
            navController = navController,
            modifier = modifier,
            startDestination = startDestination.name
        ){
            composable(ScreenInfo.BeforeLogin.name){
                StartScreenBeforeLogin(beforeLoginViewModel,navController)
            }
            composable(ScreenInfo.MakeJyakae.name){
                StartScreenMakeJyakae(makeJyakaeViewModel,navController)
            }
            composable(ScreenInfo.ArtBoard.name){
                artBoardViewModel.initFirstList()
                StartScreenArtBoard(artBoardViewModel,navController)
            }
            composable(route=ScreenInfo.ArtBoardDetail.name+"/{itemId}",
                arguments = listOf(
                navArgument("itemId"){type= NavType.StringType})
                ){
                backStackEntry->
                    val itemId = backStackEntry.arguments?.getString("itemId")
                        if (itemId != null) {
                            StartScreenArtBoardDetail(artBoardViewModel, navController, itemId = itemId )
                        }
            }
        }
    }
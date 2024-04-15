package com.paredgames.aijyakae.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paredgames.aijyakae.data.util.ScreenInfo

    @Composable
    fun AijyakaeNavHost(
        modifier: Modifier=Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination:ScreenInfo=ScreenInfo.BeforeLogin
    ) {
        NavHost(
            navController = navController,
            modifier = modifier,
            startDestination = startDestination.name
        ){
            composable(ScreenInfo.BeforeLogin.name){

            }
            composable(ScreenInfo.BeforeLoginResult.name){

            }
        }
    }
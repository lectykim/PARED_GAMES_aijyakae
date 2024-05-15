package com.paredgames.aijyakae.ui.nav

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.ScreenInfo

@Composable
fun BottomNavBar(navController: NavController){
    BottomNavigation (
        backgroundColor = Color.White,
        contentColor = colorResource(id = R.color.purple_200)
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavigationItem(
            selected = currentRoute== ScreenInfo.MakeJyakae.name,
            onClick = {
                navController.navigate(ScreenInfo.MakeJyakae.name)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_art),
                    contentDescription = stringResource(id = R.string.text_art),
                    modifier = Modifier
                        .width(26.dp)
                        .height(26.dp)
                )
            },
            label = { Text(text = stringResource(id = R.string.text_art), fontSize = 9.sp)},
            selectedContentColor = colorResource(id = R.color.purple_500),
            unselectedContentColor = colorResource(id = R.color.purple_200),

            )
        BottomNavigationItem(
            selected = currentRoute== ScreenInfo.ArtBoard.name,
            onClick = {
                navController.navigate(ScreenInfo.ArtBoard.name)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_board),
                    contentDescription = stringResource(id = R.string.text_board),
                    modifier = Modifier
                        .width(26.dp)
                        .height(26.dp)
                )
            },
            label = { Text(text = stringResource(id = R.string.text_board), fontSize = 9.sp)},
            selectedContentColor = colorResource(id = R.color.purple_500),
            unselectedContentColor = colorResource(id = R.color.purple_200),

            )


    }
}
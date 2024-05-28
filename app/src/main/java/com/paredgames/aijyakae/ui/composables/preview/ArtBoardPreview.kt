package com.paredgames.aijyakae.ui.composables.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paredgames.aijyakae.ui.composables.artboard.BoardContent
import com.paredgames.aijyakae.ui.composables.artboard.OnLoadMore
import com.paredgames.aijyakae.ui.composables.makejyakae.BannerAds
import com.paredgames.aijyakae.ui.nav.BottomNavBar
import com.paredgames.aijyakae.ui.viewmodel.ArtBoardViewModel



    /*@Preview
    @Composable
    fun MainPage(

    ){
        var page by rememberSaveable {
            mutableIntStateOf(0)
        }
        val gridState: LazyGridState = rememberLazyGridState()

        gridState.OnLoadMore (action = {  },page=page, updatePage = {page=it})

        Column {
            //BannerAds()
            BottomNavBar(navController = rememberNavController())
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = gridState
            ) {
                items(boardFakeData){
                        item->
                    BoardContent(artBoardContent = item)

                }
            }

        }
    }

*/
package com.paredgames.aijyakae.ui.composables.artboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.layout.LayoutInfo
import androidx.navigation.NavController
import com.paredgames.aijyakae.ui.nav.BottomNavBar
import com.paredgames.aijyakae.ui.viewmodel.ArtBoardViewModel


@Composable
fun StartScreenArtBoard(
    artBoardViewModel: ArtBoardViewModel,
    navController: NavController
) {
    MainPage(artBoardViewModel,navController)
}

@Composable
fun MainPage(
    artBoardViewModel: ArtBoardViewModel,
    navController: NavController
){
    var page by rememberSaveable {
        mutableIntStateOf(0)
    }
    val gridState = rememberLazyGridState()

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState
        ) {
            items(artBoardViewModel.artBoardList){
                it->{
                    // TODO: Inner Content Composable 정의
                }
            }
            item(){
                //TODO: Bottom Status Handling 설정
            }
        }
        BottomNavBar(navController = navController)
    }

    gridState.OnBottomReached (
        viewModel = artBoardViewModel,
        page = page,
        nextPage = {page=it}
    )
}


@Composable
fun LazyGridState.OnBottomReached(buffer:Int=2,viewModel: ArtBoardViewModel,page:Int,nextPage:(Int)->Unit){


    nextPage(page+1)

    val shouldLoadMore = remember{
        derivedStateOf {
            val lastVisibleItem=layoutInfo.visibleItemsInfo.lastOrNull() ?:return@derivedStateOf true
            lastVisibleItem.index==layoutInfo.totalItemsCount -1 -buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow{shouldLoadMore.value}
            .collect{
                if(it) viewModel.getBoardList(page+1)
        }
    }

}

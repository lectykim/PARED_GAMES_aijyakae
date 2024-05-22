package com.paredgames.aijyakae.ui.composables.artboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
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
import com.paredgames.aijyakae.data.dto.ArtBoardContent
import com.paredgames.aijyakae.ui.composables.makejyakae.BannerAds
import com.paredgames.aijyakae.ui.nav.BottomNavBar
import com.paredgames.aijyakae.ui.viewmodel.ArtBoardViewModel
import com.skydoves.landscapist.glide.GlideImage


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
    val gridState:LazyGridState = rememberLazyGridState()

    gridState.OnLoadMore (action = { artBoardViewModel.getFakeBoardData(page) },page=page, updatePage = {page=it})

    Column {
        BannerAds()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState
        ) {
            items(artBoardViewModel.artBoardList){
                item->
                    BoardContent(artBoardContent = item)

            }
        }
        BottomNavBar(navController = navController)
    }
}

@Composable
fun BoardContent(artBoardContent: ArtBoardContent){
    artBoardContent.prompt = artBoardContent.prompt.substring(0 until 100)
    Column {
        GlideImage(imageModel = { artBoardContent.s3Url })
        Text(text = artBoardContent.prompt)
        Text(text = artBoardContent.userName)
    }



}
fun LazyGridState.onBottomReached(buffer:Int=4,triggerOnEnd: Boolean = false): Boolean {


    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return (triggerOnEnd && lastVisibleItem?.index == layoutInfo.totalItemsCount - 1)
            || lastVisibleItem?.index != 0 && lastVisibleItem?.index == layoutInfo.totalItemsCount - (buffer + 1)

}


@Composable
fun LazyGridState.OnLoadMore(limitCount: Int = 4, loadOnBottom: Boolean = true, action: () -> Unit,page:Int,updatePage:(Int)->Unit){
    val reached by remember{
        derivedStateOf {
            onBottomReached(buffer = limitCount, triggerOnEnd = loadOnBottom)
        }
    }

    LaunchedEffect(reached) {
        if(reached){
            updatePage(page+1)
            action()
        }

    }
}
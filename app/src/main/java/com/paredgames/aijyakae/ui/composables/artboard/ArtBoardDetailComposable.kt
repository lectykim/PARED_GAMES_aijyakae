package com.paredgames.aijyakae.ui.composables.artboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.dto.ArtBoardContent
import com.paredgames.aijyakae.ui.viewmodel.ArtBoardViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun StartScreenArtBoardDetail(
    artBoardViewModel: ArtBoardViewModel,
    navController: NavController,
    itemId:String
){

    DetailPage(navController = navController,artBoardViewModel=artBoardViewModel, itemId = itemId)
}

@Composable
fun DetailPage(
    artBoardViewModel:ArtBoardViewModel,
    navController: NavController,
    itemId: String
) {
    artBoardViewModel.getBoardItem(itemId)
    val artBoardContent by artBoardViewModel.currentArtBoardItem.collectAsState()
    artBoardContent.changeItemSizeDetailPage()
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        GlideImage(imageModel = { artBoardContent.s3Url },
            modifier = Modifier
                .padding(10.dp)
                .width(artBoardContent.width.dp)
                .height(artBoardContent.height.dp)
        )
        Text(text = artBoardContent.prompt)
        Text(text = stringResource(id = R.string.writer) + " : " + artBoardContent.userName)

    }
}
package com.paredgames.aijyakae.ui.composables.makejyakae.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.DrawingStyle
import com.paredgames.aijyakae.data.util.Resolution
import com.paredgames.aijyakae.ui.viewmodel.IJyakaeViewModel
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel

val drawingStyleList= listOf(
    DrawingStyle.DRAWING_STYLE_ANIMATION,
    DrawingStyle.DRAWING_STYLE_TWO_FIVE_D,
    DrawingStyle.DRAWING_STYLE_PASTEL_ONE,
    DrawingStyle.DRAWING_STYLE_PASTEL_TWO,
    DrawingStyle.DRAWING_STYLE_CUTE
)
val resolutionStyleList= listOf(
    Resolution.ONE_BY_ONE,
    Resolution.NINE_BY_SIXTEEN,
    Resolution.SIXTEEN_BY_NINE
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawingStyleItemBottomSheet(
    modifier: Modifier=Modifier,
    closeSheet:()->Unit,
    makeJyakaeViewModel: IJyakaeViewModel
){

    val sheetState = rememberModalBottomSheetState()
    
    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState=sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = colorResource(id = R.color.warm_button_orange_disable),
        dragHandle = null,
        modifier = modifier
            .fillMaxWidth()
        )
    {
        Column {
            Text(text = "스타일")
            Spacer(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(drawingStyleList){
                        item->
                    ItemLogo(onClick = {
                        closeSheet()
                        makeJyakaeViewModel.makeJyakaeContent.value.drawingStyle=item
                                       }, image = item.image, title = item.title)
                }
            }
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResolutionItemBottomSheet(
    modifier: Modifier=Modifier,
    closeSheet:()->Unit,
    makeJyakaeViewModel: IJyakaeViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = colorResource(id = R.color.warm_button_orange_disable),
        dragHandle = null,
        modifier = modifier
            .fillMaxWidth()
    )
    {
        Column {
            Text(text = "해상도")
            Spacer(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(resolutionStyleList){
                        item->
                    ItemLogo(onClick = {
                        closeSheet()
                        makeJyakaeViewModel.makeJyakaeContent.value.resolution=item
                                       }, image = item.image, title = item.title)
                }
            }
        }
    }
}
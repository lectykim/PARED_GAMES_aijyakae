package com.paredgames.aijyakae.ui.composables.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.ui.composables.makejyakae.StartScreenMakeJyakae
import com.paredgames.aijyakae.ui.composables.makejyakae.item.DrawingStyleItemBottomSheet
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ItemLogo
import com.paredgames.aijyakae.ui.composables.makejyakae.item.drawingStyleList
import com.paredgames.aijyakae.ui.composables.makejyakae.textfield.CustomTextField
import com.paredgames.aijyakae.ui.nav.BottomNavBar
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme

/*
@Preview(
    widthDp = 600,
    heightDp = 900,
    showBackground = true,
    backgroundColor = 0xFFFFF2E6
)
@Composable
fun PreviewStartScreenMakeJyakae(){
    AijyakaeTheme {
        StartScreenMakeJyakae()
    }
}
*/
@Preview
@Composable
fun PreviewTextField(){
    var text by rememberSaveable {
        mutableStateOf("")
    }
    AijyakaeTheme {
        CustomTextField(onValueChange = {text=it}, text = text)
    }
}

@Preview
@Composable
fun BottomNav(){
    AijyakaeTheme {
        BottomNavBar(navController = rememberNavController())
    }
}

@Preview
@Composable
fun PreviewItemLogo(){
    AijyakaeTheme {
        ItemLogo(onClick = { /*TODO*/ }, image = R.drawable.item_logo, title = R.string.text_cute)
    }
}

@Preview
@Composable
fun PreviewItemButtonSheet(){
    AijyakaeTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(drawingStyleList){
                    item->
                ItemLogo(onClick = { /*TODO*/ }, image = item.image, title = item.title)
            }
        }
    }
}
package com.paredgames.aijyakae.ui.composables.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import com.paredgames.aijyakae.ui.composables.makejyakae.PaymentCompose
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ItemLogo
import com.paredgames.aijyakae.ui.composables.makejyakae.item.drawingStyleList
import com.paredgames.aijyakae.ui.composables.makejyakae.textfield.CustomTextField
import com.paredgames.aijyakae.ui.nav.BottomNavBar
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideRequestType

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
/*
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
        ItemLogo(onClick = { */
/*TODO*//*
 }, image = R.drawable.cute_item, title = R.string.text_cute)
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
                ItemLogo(onClick = { */
/*TODO*//*
 }, image = item.image, title = item.title)
            }
        }
    }
}

@Preview
@Composable
fun PreviewRowItem(){
    AijyakaeTheme {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(onClick = {},
                modifier= Modifier
                    .size(width = 80.dp, height = 80.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ){
                Icon(
                    painter = painterResource(id = R.drawable.icon_download),
                    contentDescription = "다운로드",
                    modifier=Modifier
                        .size(width = 50.dp, height = 50.dp)
                )
            }
            Spacer(modifier = Modifier
                .padding(PaddingValues(start = 50.dp,end=50.dp))
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .size(width = 160.dp, height = 80.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.re_make),
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}*/
@Preview
@Composable
fun FinalResultImage(
) {
    //val response by makeJyakaeViewModel.response.collectAsState()


    Column (
        modifier= Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        /*TitleText(titleText = R.string.makejyakae_final_result_page)
        BannerAds()*/
        /*
        GlideImage(imageModel = {  },
            modifier=Modifier.height(500.dp),
            glideRequestType = GlideRequestType.BITMAP,
            previewPlaceholder = painterResource(id = R.drawable.placeholder)
        )
        */
        Image(painter = painterResource(id = R.drawable.sixteen_by_nine), contentDescription = "", modifier = Modifier
            .width(300.dp)
            .height(300.dp))
        Spacer(modifier = Modifier.padding(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(onClick = {
                },
                modifier=Modifier
                    .size(width = 80.dp, height = 80.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ){
                Icon(
                    painter = painterResource(id = R.drawable.icon_download),
                    contentDescription = "다운로드",
                    modifier=Modifier
                        .size(width = 30.dp, height = 30.dp)
                )
            }
            Spacer(modifier = Modifier
                .padding(PaddingValues(start = 50.dp,end=50.dp))
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .size(width = 160.dp, height = 80.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.re_make),
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    letterSpacing = 2.sp
                )
            }
        }

    }


}

@Preview
@Composable
fun paymentPreview(){
    AijyakaeTheme {
        PaymentCompose()
    }
}
package com.paredgames.aijyakae.ui.composables.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.rememberNavController
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.util.DrawingStyle
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.data.util.ModelId
import com.paredgames.aijyakae.data.util.Resolution
import com.paredgames.aijyakae.data.util.SamplingMethod
import com.paredgames.aijyakae.ui.composables.makejyakae.PaymentCompose
import com.paredgames.aijyakae.ui.composables.makejyakae.item.DrawingStyleItemBottomSheet
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ItemLogo
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ResolutionItemBottomSheet
import com.paredgames.aijyakae.ui.composables.makejyakae.popupbox.PopupBox
import com.paredgames.aijyakae.ui.composables.makejyakae.textfield.CustomTextField
import com.paredgames.aijyakae.ui.nav.BottomNavBar

import com.paredgames.aijyakae.ui.viewmodel.FakeMakeJyakaeViewModel


@Preview(widthDp = 380, heightDp = 800, showBackground = true, backgroundColor = 0xFFFFF2e6)
@Composable
fun PromptTextArea(
){
    val makeJyakaeContent by rememberSaveable { mutableStateOf(
        MakeJyakaeContent(
            "",
            "",
            SamplingMethod.DPMPP_2M_KARRAS,
            ModelId.ANYTHING_V3,
            25,
            -1,
            512,
            512,
            DrawingStyle.DRAWING_STYLE_ANIMATION,
            Resolution.ONE_BY_ONE
        )
    ) }
    var promptString by rememberSaveable {
        mutableStateOf("")
    }
    var isDrawingStyleModalOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var resolutionStyleModalOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomNavBar(navController = rememberNavController())
        Text(
            text = stringResource(id = R.string.introduce_makejyakae),
            fontFamily = FontData.maruboriFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            maxLines = 2,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )
        CustomTextField(
            onValueChange = { promptString = it },
            text = promptString
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        ) {
            Column {

                Text(text = stringResource(id = R.string.style))
                Spacer(modifier = Modifier.padding(5.dp))
                when (makeJyakaeContent.drawingStyle) {
                    DrawingStyle.DRAWING_STYLE_ANIMATION -> ItemLogo(
                        onClick = { isDrawingStyleModalOpen = true },
                        image = DrawingStyle.DRAWING_STYLE_ANIMATION.image,
                        title = DrawingStyle.DRAWING_STYLE_ANIMATION.title
                    )

                    DrawingStyle.DRAWING_STYLE_TWO_FIVE_D -> ItemLogo(
                        onClick = { isDrawingStyleModalOpen = true },
                        image = DrawingStyle.DRAWING_STYLE_TWO_FIVE_D.image,
                        title = DrawingStyle.DRAWING_STYLE_TWO_FIVE_D.title
                    )

                    DrawingStyle.DRAWING_STYLE_PASTEL_ONE -> ItemLogo(
                        onClick = { isDrawingStyleModalOpen = true },
                        image = DrawingStyle.DRAWING_STYLE_PASTEL_ONE.image,
                        title = DrawingStyle.DRAWING_STYLE_PASTEL_TWO.title
                    )

                    DrawingStyle.DRAWING_STYLE_PASTEL_TWO -> ItemLogo(
                        onClick = { isDrawingStyleModalOpen = true },
                        image = DrawingStyle.DRAWING_STYLE_PASTEL_TWO.image,
                        title = DrawingStyle.DRAWING_STYLE_PASTEL_TWO.title
                    )

                    DrawingStyle.DRAWING_STYLE_CUTE -> ItemLogo(
                        onClick = { isDrawingStyleModalOpen = true },
                        image = DrawingStyle.DRAWING_STYLE_CUTE.image,
                        title = DrawingStyle.DRAWING_STYLE_CUTE.title
                    )
                }


            }
            Spacer(modifier = Modifier.padding(end = 100.dp))
            Column {
                Text(text = stringResource(id = R.string.resolution))
                Spacer(modifier = Modifier.padding(5.dp))
                when(makeJyakaeContent.resolution){
                    Resolution.ONE_BY_ONE-> ItemLogo(
                        onClick = { resolutionStyleModalOpen=true },
                        image = Resolution.ONE_BY_ONE.image,
                        title = Resolution.ONE_BY_ONE.title
                    )
                    Resolution.NINE_BY_SIXTEEN -> ItemLogo(
                        onClick = { resolutionStyleModalOpen=true },
                        image = Resolution.NINE_BY_SIXTEEN.image,
                        title = Resolution.NINE_BY_SIXTEEN.title
                    )
                    Resolution.SIXTEEN_BY_NINE -> ItemLogo(
                        onClick = { resolutionStyleModalOpen=true },
                        image = Resolution.SIXTEEN_BY_NINE.image,
                        title = Resolution.SIXTEEN_BY_NINE.title
                    )
                }
            }
        }


    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {


                makeJyakaeContent.prompt=promptString
            },
            modifier = Modifier
                .size(width = 480.dp, height = 80.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.warm_button_orange),
                contentColor = Color.White,
                disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                disabledContentColor = Color.White
            )
        ){
            Text(
                text = stringResource(id = R.string.generate_image),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                letterSpacing = 2.sp
            )
        }

    }

}

@Preview(widthDp = 380, heightDp = 800, showBackground = true, backgroundColor = 0xFFFFF2e6)
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
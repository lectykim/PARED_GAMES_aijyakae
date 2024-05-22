import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ItemLogo
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme

/*
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.ui.composables.beforelogin.button.NextButton
import com.paredgames.aijyakae.ui.composables.beforelogin.button.NextImageButton
import com.paredgames.aijyakae.ui.composables.beforelogin.title.TitleText
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import com.skydoves.landscapist.glide.GlideImage

@Preview
@Composable
fun ButtonPreview(){
    AijyakaeTheme {
        NextButton(onClick = { */
/*TODO*//*
 }, buttonText = R.string.select_second_page)
    }
}

@Preview
@Composable
fun NextImageButtonPreview(){
    AijyakaeTheme {
        NextImageButton(onClick = { */
/*TODO*//*
 }, buttonText = R.string.first_enter_page_button_start, buttonImage = R.drawable.animation_image)
    }
}

*/
/*
@Preview
@Composable
fun FirstPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(titleText = R.string.first_enter_page)
        NextButton(onClick = {  }, buttonText =R.string.first_enter_page_button_start )
    }
}

@Preview
@Composable
fun SecondPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(titleText = R.string.select_first_page)
        Row(
            modifier = modifier
                .padding(40.dp, 200.dp, 40.dp, 0.dp)
        ) {
            NextImageButton(onClick = {

                }
                , buttonText = R.string.sex_man,
                buttonImage = R.drawable.man_image)
            Spacer(modifier = Modifier.width(10.dp))
            NextImageButton(onClick = {},
                buttonText = R.string.sex_women,
                buttonImage = R.drawable.girl_image)

        }
        Spacer(modifier = Modifier.width(10.dp))
        NextImageButton(onClick = {


        }, buttonText = R.string.sex_none,
            buttonImage = R.drawable.none_image)
    }
}*//*



*/
/*
@Preview
@Composable
fun ThirdPage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(titleText = R.string.select_second_page)
        Row(
            modifier = modifier
                .padding(40.dp, 200.dp, 40.dp, 0.dp)

        ) {
            NextImageButton(onClick = {


            }, buttonText = R.string.dimension_threed,
                buttonImage = R.drawable.threed_image)
            Spacer(modifier = Modifier.width(10.dp))
            NextImageButton(onClick = { },
                buttonText = R.string.dimension_twod,
                buttonImage = R.drawable.twod_image)
        }

        Row(
            modifier = modifier
                .padding(40.dp, 20.dp, 40.dp, 0.dp)

        ) {
            NextImageButton(onClick = {},
                buttonText = R.string.dimension_animation,
                buttonImage = R.drawable.animation_image)
            Spacer(modifier = Modifier.width(10.dp))
            NextImageButton(onClick = { },
                buttonText = R.string.dimension_real,
                buttonImage = R.drawable.reality_image)
        }
    }

}

@Preview
@Composable
fun FourthPage(
    modifier: Modifier= Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(titleText = R.string.select_third_page)
        Row(
            modifier = modifier
                .padding(40.dp, 200.dp, 40.dp, 0.dp)
        ) {
            NextImageButton(onClick = { },
                buttonText = R.string.draw_size_ld,
                buttonImage = R.drawable.full_body_image)
            Spacer(modifier = Modifier.width(10.dp))
            NextImageButton(onClick = { },
                buttonText = R.string.draw_size_half,
                buttonImage = R.drawable.half_body_image)


        }

        Row(
            modifier = modifier
                .padding(40.dp, 20.dp, 40.dp, 0.dp)
        ) {

            NextImageButton(onClick = { },
                buttonText = R.string.draw_size_face,
                buttonImage = R.drawable.only_face_image)

        }

    }
}
*//*


@Preview
@Composable
fun FinalPage(
    modifier: Modifier= Modifier
) {

    val loading by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(titleText = R.string.select_final_page)

        if(loading){
            CircularProgressIndicator(
                modifier=modifier
                    .width(64.dp),
                color= colorResource(id = R.color.warm_button_orange),
                trackColor = MaterialTheme.colorScheme.surfaceVariant

            )
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(!loading){
            NextButton(onClick = {  },
                buttonText = R.string.go_result_page)
        }

    }
}

@Preview
@Composable
fun FinalResultImage(
    modifier: Modifier=Modifier
) {

    Column (
        modifier= modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TitleText(titleText = R.string.final_result_page)
        */
/*GlideImage(
            imageModel =  "" ,
            contentScale = ContentScale.Fit,
            circularReveal = CircularReveal(duration = 250),
            placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
            modifier = modifier.width(250.dp).height(250.dp)
        )*//*

        Image(painter = painterResource(id = R.drawable.one_by_one),
            contentDescription = "결과"
        ,modifier=modifier.width(250.dp).height(250.dp))
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { */
/*TODO: 구글로그인이랑 광고구현*//*
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
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_download),
                contentDescription = "다운로드",
                modifier=modifier
                    .size(width = 50.dp, height = 50.dp)
            )
            Text(
                text = "저장하기",
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                letterSpacing = 2.sp
            )
        }
        Spacer(modifier = Modifier
            .padding(20.dp)
        )
        Button(
            onClick = { */
/*TODO: 구글로그인이랑 광고구현*//*
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
        ) {
            Text(
                text = stringResource(id = R.string.make_other_art),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                letterSpacing = 2.sp
            )
        }
    }

}

*/

@Preview
@Composable
fun NewButtonImage(){
    AijyakaeTheme {
        ItemLogo(onClick = { /*TODO*/ },
            image = R.drawable.animation_image,
            title = R.string.text_2_5_d,
            width = 100.dp,
            height = 100.dp,
            borderStroke = BorderStroke(1.dp, Color.Black)
        )
    }
}
package com.paredgames.aijyakae.ui.composables.beforelogin


import android.content.Context
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.data.util.ScreenInfo
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import com.paredgames.aijyakae.ui.composables.beforelogin.button.NextButton
import com.paredgames.aijyakae.ui.composables.beforelogin.button.NextImageButton
import com.paredgames.aijyakae.ui.composables.beforelogin.title.TitleText
import com.paredgames.aijyakae.ui.composables.makejyakae.getPermission
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideRequestType

@Composable
    fun StartScreenBeforeLogin(
        beforeLoginViewModel: BeforeLoginViewModel= viewModel(),
        navController: NavController
    ) {
        var goNext by rememberSaveable {
            mutableIntStateOf(0)
        }

        val isFinal by beforeLoginViewModel.isFinal.collectAsState()

        if(isFinal){
            FinalResultImage(beforeLoginViewModel = beforeLoginViewModel, navController = navController)
        }else{
            when (goNext) {
                0 -> FirstPage(updateState = { goNext = it }, beforeLoginViewModel = beforeLoginViewModel)
                1 -> SecondPage(updateState = { goNext = it }, beforeLoginViewModel = beforeLoginViewModel)
                2 -> ThirdPage (updateState = {goNext=it}, beforeLoginViewModel = beforeLoginViewModel)
                3 -> FourthPage (updateState = {goNext = it}, beforeLoginViewModel = beforeLoginViewModel)
                else -> FinalPage (updateState = {goNext=it}, beforeLoginViewModel = beforeLoginViewModel, navController = navController)
            }
        }


    }

    @Composable
    fun FirstPage(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel,
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(titleText = R.string.first_enter_page)
            NextButton(onClick = { updateState(1) }, buttonText =R.string.first_enter_page_button_start )
        }
    }

    @Composable
    fun SecondPage(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel,
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(titleText = R.string.select_first_page)
            Row(
                modifier = modifier
                    .padding(0.dp, 200.dp, 0.dp, 0.dp)
            ) {
                NextImageButton(onClick = {
                    updateState(2)
                    beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Man}
                    , buttonText = R.string.sex_man,
                    buttonImage = R.drawable.man_image)
                Spacer(modifier = Modifier.width(10.dp))
                NextImageButton(onClick = {updateState(2)
                    beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Women},
                    buttonText = R.string.sex_women,
                    buttonImage = R.drawable.girl_image)

            }
            /*Spacer(modifier = Modifier.width(10.dp))
           NextImageButton(onClick = {
               updateState(2)
               beforeLoginViewModel.beforeLoginContent.value.sex=BeforeLoginSex.Asexual
           }, buttonText = R.string.sex_none,
               buttonImage = R.drawable.none_image)*/
        }
    }

    @Composable
    fun ThirdPage(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel,
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(titleText = R.string.select_second_page)
            Row(
                modifier = modifier
                    .padding(0.dp, 200.dp, 0.dp, 0.dp)

            ) {
                NextImageButton(onClick = {
                    updateState(3)
                    beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                        BeforeLoginDrawStyle.ThreeDimension
                }, buttonText = R.string.dimension_threed,
                    buttonImage = R.drawable.threed_image)
                Spacer(modifier = Modifier.width(10.dp))
                NextImageButton(onClick = { updateState(3)
                    beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                        BeforeLoginDrawStyle.TwoDimension },
                    buttonText = R.string.dimension_twod,
                    buttonImage = R.drawable.twod_image)
                Spacer(modifier = Modifier.width(10.dp))
                NextImageButton(onClick = {updateState(3)
                    beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                        BeforeLoginDrawStyle.Anime},
                    buttonText = R.string.dimension_animation,
                    buttonImage = R.drawable.animation_image)
            }
        }

    }

    @Composable
    fun FourthPage(
        modifier: Modifier= Modifier,
        beforeLoginViewModel: BeforeLoginViewModel,
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(titleText = R.string.select_third_page)
            Row(
                modifier = modifier
                    .padding(0.dp, 200.dp, 0.dp, 0.dp)
            ) {
                NextImageButton(onClick = { updateState(4)
                    beforeLoginViewModel.beforeLoginContent.value.drawSize = BeforeLoginDrawSize.LD},
                    buttonText = R.string.draw_size_ld,
                    buttonImage = R.drawable.full_body_image)
                Spacer(modifier = Modifier.width(10.dp))
                NextImageButton(onClick = { updateState(4)
                    beforeLoginViewModel.beforeLoginContent.value.drawSize = BeforeLoginDrawSize.SD},
                    buttonText = R.string.draw_size_half,
                    buttonImage = R.drawable.half_body_image)
                Spacer(modifier = Modifier.width(10.dp))
                NextImageButton(onClick = { updateState(4)
                    beforeLoginViewModel.beforeLoginContent.value.drawSize =
                        BeforeLoginDrawSize.FACE },
                    buttonText = R.string.draw_size_face,
                    buttonImage = R.drawable.only_face_image)

            }

        }
    }


    @Composable
    fun FinalPage(
        modifier: Modifier= Modifier,
        beforeLoginViewModel: BeforeLoginViewModel,
        updateState: (Int) -> Unit,
        navController: NavController

    ) {

        val loading by beforeLoginViewModel.loading.collectAsState()

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
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
                NextButton(onClick = { beforeLoginViewModel.getStableDiffusion()
                                     beforeLoginViewModel.setPreferenceData(SharedPreferenceDataKeys.IS_LOGIN_KEY,"true")
                                     beforeLoginViewModel.setPreferenceData(SharedPreferenceDataKeys.LAST_MODIFIED_STR_KEY,beforeLoginViewModel.beforeLoginContent.value.getPrompt())},
                    buttonText = R.string.go_result_page)
            }

        }
    }


@Composable
fun FinalResultImage(
    modifier: Modifier=Modifier,
    beforeLoginViewModel: BeforeLoginViewModel,
    navController: NavController
) {

    val response by beforeLoginViewModel.response.collectAsState()

    Column (
        modifier= modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TitleText(titleText = R.string.final_result_page)
        /*GlideImage(
            imageModel =  response.output[0] ,
            contentScale = ContentScale.Fit,
            circularReveal = CircularReveal(duration = 250),
            placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
            modifier = modifier.width(250.dp).height(250.dp)
        )*/
        GlideImage(imageModel = { response.base64Img },
            modifier=modifier.height(300.dp).width(300.dp),
            glideRequestType = GlideRequestType.BITMAP,
            previewPlaceholder = painterResource(id = R.drawable.placeholder)
        )
        Spacer(modifier = modifier.padding(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(onClick = {
                beforeLoginViewModel.downloadImage(response.base64Img,response.id)},
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
                    modifier=modifier
                        .size(width = 30.dp, height = 30.dp)
                )
            }
            Spacer(modifier = Modifier
                .padding(PaddingValues(start = 50.dp,end=50.dp))
            )
            Button(
                onClick = { navController.navigate(ScreenInfo.MakeJyakae.name) },
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
                    text = stringResource(id = R.string.make_other_art),
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    letterSpacing = 2.sp
                )
            }
        }

    }


}
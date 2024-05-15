package com.paredgames.aijyakae.ui.composables.makejyakae


import android.util.Base64
import android.util.Log
import android.widget.EditText
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
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.rewarded.RewardedAd
import com.paredgames.aijyakae.BuildConfig
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.DrawingStyle
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.data.util.Resolution
import com.paredgames.aijyakae.data.util.ScreenInfo
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import com.paredgames.aijyakae.ui.composables.beforelogin.title.TitleText
import com.paredgames.aijyakae.ui.composables.makejyakae.item.DrawingStyleItemBottomSheet
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ItemLogo
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ResolutionItemBottomSheet
import com.paredgames.aijyakae.ui.composables.makejyakae.textfield.CustomTextField
import com.paredgames.aijyakae.ui.nav.BottomNavBar
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideRequestType
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL


@Composable
    fun StartScreenMakeJyakae(
        makeJyakaeViewModel: MakeJyakaeViewModel,
        navController: NavController
    ) {


        PromptTextArea(makeJyakaeViewModel = makeJyakaeViewModel, navController = navController)

    }

    @Composable
    fun PromptTextArea(
        modifier:Modifier=Modifier,
        makeJyakaeViewModel: MakeJyakaeViewModel,
        navController: NavController
    ){
        val isFinal by makeJyakaeViewModel.isFinal.collectAsState()
        val makeJyakaeContent by makeJyakaeViewModel.makeJyakaeContent.collectAsState()
        val loading by makeJyakaeViewModel.loading.collectAsState()

        var promptString by rememberSaveable {
            mutableStateOf(makeJyakaeViewModel.getPreferenceData(SharedPreferenceDataKeys.LAST_MODIFIED_STR_KEY,""))
        }

        var isDrawingStyleModalOpen by rememberSaveable {
            mutableStateOf(false)
        }

        var resolutionStyleModalOpen by rememberSaveable {
            mutableStateOf(false)
        }

        if(isDrawingStyleModalOpen){
            DrawingStyleItemBottomSheet(
                closeSheet = {isDrawingStyleModalOpen=false}
            )
        }

        if(resolutionStyleModalOpen){
            ResolutionItemBottomSheet (
                closeSheet = {resolutionStyleModalOpen=false}
            )

        }

        if(isFinal){
            FinalResultImage(modifier = modifier, makeJyakaeViewModel = makeJyakaeViewModel, navController = navController)
        }else{
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "이제 나만의 자캐를 만들어 보세요!",
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 30.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
                BannerAds()
                if(!loading){
                    CustomTextField(
                        onValueChange = {promptString=it},
                        text = promptString
                    )

                    Spacer(modifier = modifier.padding(10.dp))

                    Row (
                        modifier= modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    ){
                        Column {

                            Text(text = "스타일")
                            Spacer(modifier = modifier.padding(5.dp))
                            when(makeJyakaeContent.drawingStyle){
                                DrawingStyle.DRAWING_STYLE_ANIMATION-> ItemLogo(
                                    onClick = { isDrawingStyleModalOpen=true },
                                    image = DrawingStyle.DRAWING_STYLE_ANIMATION.image,
                                    title =DrawingStyle.DRAWING_STYLE_ANIMATION.title
                                )

                                DrawingStyle.DRAWING_STYLE_TWO_FIVE_D -> ItemLogo(
                                    onClick = { isDrawingStyleModalOpen=true },
                                    image = DrawingStyle.DRAWING_STYLE_TWO_FIVE_D.image,
                                    title =  DrawingStyle.DRAWING_STYLE_TWO_FIVE_D.title
                                )
                                DrawingStyle.DRAWING_STYLE_PASTEL_ONE -> ItemLogo(
                                    onClick = { isDrawingStyleModalOpen=true },
                                    image = DrawingStyle.DRAWING_STYLE_PASTEL_ONE.image,
                                    title = DrawingStyle.DRAWING_STYLE_PASTEL_TWO.title
                                )
                                DrawingStyle.DRAWING_STYLE_PASTEL_TWO -> ItemLogo(
                                    onClick = { isDrawingStyleModalOpen=true },
                                    image = DrawingStyle.DRAWING_STYLE_PASTEL_TWO.image,
                                    title =DrawingStyle.DRAWING_STYLE_PASTEL_TWO.title
                                )
                                DrawingStyle.DRAWING_STYLE_CUTE -> ItemLogo(
                                    onClick = { isDrawingStyleModalOpen=true },
                                    image = DrawingStyle.DRAWING_STYLE_CUTE.image,
                                    title = DrawingStyle.DRAWING_STYLE_CUTE.title
                                )
                            }
                        }
                        Spacer(modifier = modifier.padding(end = 100.dp))
                        Column {
                            Text(text = "해상도")
                            Spacer(modifier = modifier.padding(5.dp))
                            when(makeJyakaeContent.resolution){
                                Resolution.ONE_BY_ONE-> ItemLogo(
                                    onClick = { resolutionStyleModalOpen=true },
                                    image = Resolution.ONE_BY_ONE.image,
                                    title =Resolution.ONE_BY_ONE.title
                                )

                                Resolution.NINE_BY_SIXTEEN -> ItemLogo(
                                    onClick = { resolutionStyleModalOpen=true },
                                    image = Resolution.NINE_BY_SIXTEEN.image,
                                    title =Resolution.NINE_BY_SIXTEEN.title
                                )
                                Resolution.SIXTEEN_BY_NINE -> ItemLogo(
                                    onClick = { resolutionStyleModalOpen=true },
                                    image = Resolution.SIXTEEN_BY_NINE.image,
                                    title =Resolution.SIXTEEN_BY_NINE.title
                                )


                            }
                        }

                    }

                }else{
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
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(!loading){
                    Button(
                        onClick = {

                                    makeJyakaeViewModel.addAd()
                                    makeJyakaeContent.prompt=promptString
                                    makeJyakaeViewModel.getStableDiffusion()
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
                            text = "이미지 생성하기",
                            fontFamily = FontData.maruboriFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 40.sp,
                            letterSpacing = 2.sp
                        )
                    }
                }
                BottomNavBar(navController = navController)
            }
        }


    }

@Composable
fun FinalResultImage(
    modifier: Modifier,
    makeJyakaeViewModel: MakeJyakaeViewModel,
    navController: NavController
) {
    val response by makeJyakaeViewModel.response.collectAsState()


    Column (
        modifier= modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        TitleText(titleText = R.string.makejyakae_final_result_page)
        BannerAds()
        GlideImage(imageModel = { response.base64Img },
            glideRequestType = GlideRequestType.BITMAP,
            modifier= modifier
                .width(250.dp)
                .height(250.dp),
            previewPlaceholder = painterResource(id = R.drawable.placeholder)
        )
    }
    Column (
        modifier= modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { makeJyakaeViewModel.downloadImage(response.base64Img,response.id)},
            modifier=Modifier
                .size(width = 480.dp, height = 80.dp),
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
                    .size(width = 50.dp, height = 50.dp)
            )
            Text(
                text = "저장하기",
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp,
                letterSpacing = 2.sp
            )
        }
        Spacer(modifier = Modifier
            .padding(20.dp)
        )
        Button(
            onClick = { makeJyakaeViewModel.setInitialState()
                      makeJyakaeViewModel.setPreferenceData(SharedPreferenceDataKeys.LAST_MODIFIED_STR_KEY,makeJyakaeViewModel.makeJyakaeContent.value.prompt)},
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
                text = stringResource(id = R.string.re_make),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp,
                letterSpacing = 2.sp
            )
        }
    }
}



@Composable
fun BannerAds(){

    AndroidView(modifier = Modifier.fillMaxWidth(), factory = {context->AdView(context).apply {
        setAdSize(AdSize.BANNER)
        adUnitId=BuildConfig.AD_UNIT_ID_BANNER
        loadAd(AdRequest.Builder().build())
            }
        }
    )
}
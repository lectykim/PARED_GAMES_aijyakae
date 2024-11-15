package com.paredgames.aijyakae.ui.composables.makejyakae


import android.Manifest
import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.DrawingStyle
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.data.util.Resolution
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import com.paredgames.aijyakae.ui.composables.makejyakae.item.DrawingStyleItemBottomSheet
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ItemLogo
import com.paredgames.aijyakae.ui.composables.makejyakae.item.ResolutionItemBottomSheet
import com.paredgames.aijyakae.ui.composables.makejyakae.popupbox.PopupBox
import com.paredgames.aijyakae.ui.composables.makejyakae.textfield.CustomTextField
import com.paredgames.aijyakae.ui.nav.BottomNavBar
import com.paredgames.aijyakae.ui.viewmodel.IJyakaeViewModel
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideRequestType


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

        var showPopup by rememberSaveable {
            mutableStateOf(makeJyakaeViewModel.getPreferenceData(SharedPreferenceDataKeys.IS_ADD_SHOW,"true"))
        }

        if(makeJyakaeViewModel.getPreferenceData("isPurchased","false") == "true"){
            showPopup = "false"
        }

        if(isDrawingStyleModalOpen){
            DrawingStyleItemBottomSheet(
                closeSheet = {isDrawingStyleModalOpen=false},
                makeJyakaeViewModel = makeJyakaeViewModel
            )
        }

        if(resolutionStyleModalOpen){
            ResolutionItemBottomSheet (
                closeSheet = {resolutionStyleModalOpen=false},
                makeJyakaeViewModel = makeJyakaeViewModel
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
                    text = stringResource(id = R.string.introduce_makejyakae),
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

                            Text(text = stringResource(id = R.string.style))
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
                            Text(text = stringResource(id = R.string.resolution))
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

                            if(makeJyakaeViewModel.getPreferenceData("isPurchased","false") != "true"){
                                makeJyakaeViewModel.addAd()
                            }
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
                            text = stringResource(id = R.string.generate_image),
                            fontFamily = FontData.maruboriFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 40.sp,
                            letterSpacing = 2.sp
                        )
                    }
                }
                BottomNavBar(navController = navController)

            }
            PopupBox(
                popupWidth = 400,
                popupHeight = 600,
                showPopup = showPopup,
                onClickCancelButton = {showPopup="false"},
                content = { PaymentCompose(makeJyakaeViewModel) },
                makeJyakaeViewModel
            )
        }


    }

@Composable
fun PaymentCompose(
    makeJyakaeViewModel: IJyakaeViewModel
){

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.twod_image), contentDescription = "no_add")
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = stringResource(id = R.string.no_add_introduce), fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(20.dp))
        Button(onClick = { makeJyakaeViewModel.billingPayment() }) {
            Text(text = stringResource(id = R.string.add_payment))
        }
        Text(text = stringResource(id = R.string.no_add_small_introduce), fontSize = 6.sp)
    }
}

@Composable
fun FinalResultImage(
    modifier: Modifier,
    makeJyakaeViewModel:IJyakaeViewModel,
    navController: NavController
) {
    val response by makeJyakaeViewModel.response.collectAsState()

    val content  by makeJyakaeViewModel.makeJyakaeContent.collectAsState()
    var width:Dp=0.dp
    var height:Dp=0.dp
    val context = LocalContext.current
    when(content.resolution){
        Resolution.ONE_BY_ONE->{
            width=400.dp
            height=400.dp
        }
        Resolution.NINE_BY_SIXTEEN-> {
            width = 225.dp
            height = 400.dp
        }
        Resolution.SIXTEEN_BY_NINE->{
            width=400.dp
            height=225.dp
        }
    }

    Column (
        modifier= modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        /*TitleText(titleText = R.string.makejyakae_final_result_page)
        BannerAds()*/
        GlideImage(imageModel = { response.base64Img },
            modifier= modifier
                .height(height)
                .width(width),
            glideRequestType = GlideRequestType.BITMAP,
            previewPlaceholder = painterResource(id = R.drawable.placeholder)
        )
        Spacer(modifier = modifier.padding(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(onClick = {
                Toast.makeText(context, context.getString(R.string.completed_download), Toast.LENGTH_SHORT).show()
                makeJyakaeViewModel.downloadImage(response.base64Img,response.id)},
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
                onClick = { makeJyakaeViewModel.setInitialState()
                    makeJyakaeViewModel.setPreferenceData(SharedPreferenceDataKeys.LAST_MODIFIED_STR_KEY,makeJyakaeViewModel.makeJyakaeContent.value.prompt)
                          makeJyakaeViewModel.uploadImg()
                    makeJyakaeViewModel.setPreferenceData(SharedPreferenceDataKeys.IS_ADD_SHOW,"true")},
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



@Composable
fun BannerAds(){

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(SharedPreferenceDataKeys.SHARED_KEY_BEFORE_LOGIN,
        Context.MODE_PRIVATE)

    val purchased = sharedPreferences.getString("isPurchased","false")

    if(purchased=="false"){
        AndroidView(modifier = Modifier.fillMaxWidth(), factory = {context->AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId="BuildConfig.AD_UNIT_ID_BANNER"
            loadAd(AdRequest.Builder().build())
        }
        }
        )
    }


}

fun getPermission(){
    val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            //Toast.makeText(this@MainActivity, "권한 승인 됨", Toast.LENGTH_SHORT).show()
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
            //Toast.makeText(this@MainActivity, "권한 거부 됨\n$deniedPermissions", Toast.LENGTH_SHORT).show()
        }
    }


    TedPermission.create()
        .setPermissionListener(permissionListener)
        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        .check()

}
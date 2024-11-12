package com.paredgames.aijyakae.ui.composables.makejyakae.popupbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import com.paredgames.aijyakae.ui.viewmodel.IJyakaeViewModel
import com.paredgames.aijyakae.ui.viewmodel.MakeJyakaeViewModel

@Composable
fun PopupBox(
    popupWidth:Int,
    popupHeight:Int,
    showPopup:String,
    onClickCancelButton:()->Unit,
    content:@Composable() ()->Unit,
    makeJyakaeViewModel: IJyakaeViewModel
){
    if(showPopup=="true"){
        //full screen background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .zIndex(10F),
            contentAlignment = Alignment.Center
        ){
            Popup (
                alignment = Alignment.Center,
                properties = PopupProperties(
                    excludeFromSystemGesture = true
                ),
                onDismissRequest ={onClickCancelButton()
                makeJyakaeViewModel.setPreferenceData(SharedPreferenceDataKeys.IS_ADD_SHOW,"false")}
            ){
                Box(modifier = Modifier
                    .width(width = popupWidth.dp)
                    .height(height = popupHeight.dp)
                    .background(colorResource(id = R.color.warm_button_orange_disable))
                    .clip(RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Button(modifier = Modifier.align(Alignment.TopEnd), onClick = { onClickCancelButton()
                        makeJyakaeViewModel.setPreferenceData(SharedPreferenceDataKeys.IS_ADD_SHOW,"false")}) {
                        Text(text = "X")
                    }
                    content()
                }
            }
        }
    }

}
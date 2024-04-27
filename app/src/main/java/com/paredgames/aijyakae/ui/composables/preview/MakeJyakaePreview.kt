package com.paredgames.aijyakae.ui.composables.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.paredgames.aijyakae.ui.composables.makejyakae.StartScreenMakeJyakae
import com.paredgames.aijyakae.ui.composables.makejyakae.textfield.CustomTextField
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
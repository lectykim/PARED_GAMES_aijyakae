package com.paredgames.aijyakae.ui.composables.beforelogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel

class BeforeLoginComposable {


    @Preview(
        widthDp = 600,
        heightDp = 900,
        showBackground = true,
        backgroundColor = 0xFFFFF2e6
    )
    @Composable
    fun PreviewStartScreenBeforeLogin() {
        AijyakaeTheme {
            StartScreenBeforeLogin()
        }
    }

    @Composable
    fun StartScreenBeforeLogin(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel = viewModel(),
    ) {
        var goNext by rememberSaveable {
            mutableIntStateOf(0)
        }

        when (goNext) {
            0 -> FirstPage(updateState = { goNext = it })
            1 -> SecondPage(updateState = { goNext = it })
            2 -> ThirdPage (updateState = {goNext=it})
            3 -> FourthPage (updateState = {goNext = it})
            else -> FinalPage (updateState = {goNext=it})
        }




    }

    @Composable
    fun FirstPage(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel = viewModel(),
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.first_enter_page),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
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
                onClick = { updateState(1) },
                modifier = Modifier
                    .size(width = 480.dp, height = 120.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.first_enter_page_button_start),
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }

    @Composable
    fun SecondPage(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel = viewModel(),
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.select_first_page),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
            Row(
                modifier = modifier
                    .padding(40.dp, 400.dp, 40.dp, 0.dp)
            ) {
                Button(onClick = {
                    updateState(2)
                    beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Man
                }, modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.man_character),
                        contentDescription = "남자",
                        modifier = modifier
                            .size(width = 70.dp, height = 70.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape),

                        )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "남성")
                },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )

                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {
                    updateState(2)
                    beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Women
                }, modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.women_character),
                        contentDescription = "여성",
                        modifier = modifier
                            .size(width = 70.dp, height = 70.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "여성")
                },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        updateState(2)
                        beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Asexual
                    },
                    modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                        Image(
                            painter = painterResource(id = R.drawable.asexual),
                            contentDescription = "무성",
                            modifier = modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "무성")
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )


            }
        }
    }

    @Composable
    fun ThirdPage(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel = viewModel(),
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.select_second_page),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
            Row(
                modifier = modifier
                    .padding(40.dp, 400.dp, 40.dp, 0.dp)

            ) {
                Button(onClick = {
                    updateState(3)
                    beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                        BeforeLoginDrawStyle.ThreeDimension
                }, modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.women_character),
                        contentDescription = "3D",
                        modifier = modifier
                            .size(width = 70.dp, height = 70.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "3D")
                },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        updateState(3)
                        beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                            BeforeLoginDrawStyle.TwoDimension
                    },
                    modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                        Image(
                            painter = painterResource(id = R.drawable.asexual),
                            contentDescription = "2D",
                            modifier = modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "2D")
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )


            }

            Row(
                modifier = modifier
                    .padding(40.dp, 20.dp, 40.dp, 0.dp)

            ) {
                Button(onClick = {
                    updateState(3)
                    beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                        BeforeLoginDrawStyle.Anime
                }, modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.women_character),
                        contentDescription = "animation",
                        modifier = modifier
                            .size(width = 70.dp, height = 70.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "애니메이션")
                },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        updateState(3)
                        beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                            BeforeLoginDrawStyle.Real
                    },
                    modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                        Image(
                            painter = painterResource(id = R.drawable.asexual),
                            contentDescription = "실사",
                            modifier = modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "실사")
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )


            }
        }

    }

    @Composable
    fun FourthPage(
        modifier: Modifier= Modifier,
        beforeLoginViewModel: BeforeLoginViewModel= viewModel(),
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.select_third_page),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
            Row(
                modifier = modifier
                    .padding(40.dp, 400.dp, 40.dp, 0.dp)
            ) {
                Button(onClick = {
                    updateState(4)
                    beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Man
                }, modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.man_character),
                        contentDescription = "LD",
                        modifier = modifier
                            .size(width = 70.dp, height = 70.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "LD")
                },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )

                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {
                    updateState(4)
                    beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Women
                }, modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.women_character),
                        contentDescription = "SD",
                        modifier = modifier
                            .size(width = 70.dp, height = 70.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "SD")
                },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        updateState(4)
                        beforeLoginViewModel.beforeLoginContent.value.sex =
                            BeforeLoginSex.Asexual
                    },
                    modifier = modifier.size(width = 150.dp, height = 100.dp), content = {
                        Image(
                            painter = painterResource(id = R.drawable.asexual),
                            contentDescription = "FACE",
                            modifier = modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "두상")
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.warm_button_orange),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                        disabledContentColor = Color.White
                    )
                )


            }
        }

    }


    @Composable
    fun FinalPage(
        modifier: Modifier= Modifier,
        beforeLoginViewModel: BeforeLoginViewModel= viewModel(),
        updateState: (Int) -> Unit
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.select_final_page),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
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
                onClick = { /* TODO */ },
                modifier = Modifier
                    .size(width = 480.dp, height = 120.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.warm_button_orange),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.warm_button_orange_disable),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "결과 보러 가기!",
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}
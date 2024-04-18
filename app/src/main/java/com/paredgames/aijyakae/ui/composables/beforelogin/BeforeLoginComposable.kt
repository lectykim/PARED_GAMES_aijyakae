package com.paredgames.aijyakae.ui.composables.beforelogin

import android.content.Context
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
import androidx.navigation.NavController
import com.paredgames.aijyakae.R
import com.paredgames.aijyakae.data.util.BeforeLoginDrawSize
import com.paredgames.aijyakae.data.util.BeforeLoginDrawStyle
import com.paredgames.aijyakae.data.util.BeforeLoginSex
import com.paredgames.aijyakae.data.util.FontData
import com.paredgames.aijyakae.data.util.ScreenInfo
import com.paredgames.aijyakae.ui.theme.AijyakaeTheme
import com.paredgames.aijyakae.ui.viewmodel.BeforeLoginViewModel




    @Preview(
        widthDp = 600,
        heightDp = 900,
        showBackground = true,
        backgroundColor = 0xFFFFF2e6
    )
    @Composable
    fun PreviewStartScreenBeforeLogin() {
        AijyakaeTheme {
            //StartScreenBeforeLogin()
        }
    }

    @Composable
    fun StartScreenBeforeLogin(
        beforeLoginViewModel: BeforeLoginViewModel= viewModel(),
        navController: NavController
    ) {
        var goNext by rememberSaveable {
            mutableIntStateOf(0)
        }

        when (goNext) {
            0 -> FirstPage(updateState = { goNext = it }, beforeLoginViewModel = beforeLoginViewModel)
            1 -> SecondPage(updateState = { goNext = it }, beforeLoginViewModel = beforeLoginViewModel)
            2 -> ThirdPage (updateState = {goNext=it}, beforeLoginViewModel = beforeLoginViewModel)
            3 -> FourthPage (updateState = {goNext = it}, beforeLoginViewModel = beforeLoginViewModel)
            else -> FinalPage (updateState = {goNext=it}, beforeLoginViewModel = beforeLoginViewModel, navController = navController)
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
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.first_enter_page),
                fontFamily = FontData.maruboriFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp,
                maxLines = Int.MAX_VALUE,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
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
        beforeLoginViewModel: BeforeLoginViewModel,
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
                fontSize = 30.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
            Row(
                modifier = modifier
                    .padding(40.dp, 200.dp, 40.dp, 0.dp)
            ) {
                Button(onClick = {
                    updateState(2)
                    beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Man
                }, modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.man_image),
                        contentDescription = "남자",
                        modifier = modifier
                            .size(width = 60.dp, height = 60.dp)
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
                }, modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.girl_image),
                        contentDescription = "여성",
                        modifier = modifier
                            .size(width = 60.dp, height = 60.dp)
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
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    updateState(2)
                    beforeLoginViewModel.beforeLoginContent.value.sex = BeforeLoginSex.Asexual
                },
                modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.none_image),
                        contentDescription = "무성",
                        modifier = modifier
                            .size(width = 60.dp, height = 60.dp)
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

    @Composable
    fun ThirdPage(
        modifier: Modifier = Modifier,
        beforeLoginViewModel: BeforeLoginViewModel,
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
                fontSize = 30.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
            Row(
                modifier = modifier
                    .padding(40.dp, 200.dp, 40.dp, 0.dp)

            ) {
                Button(onClick = {
                    updateState(3)
                    beforeLoginViewModel.beforeLoginContent.value.drawStyle =
                        BeforeLoginDrawStyle.ThreeDimension
                }, modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.threed_image),
                        contentDescription = "3D",
                        modifier = modifier
                            .size(width = 60.dp, height = 60.dp)
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
                    modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                        Image(
                            painter = painterResource(id = R.drawable.twod_image),
                            contentDescription = "2D",
                            modifier = modifier
                                .size(width = 60.dp, height = 60.dp)
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
                }, modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.animation_image),
                        contentDescription = "animation",
                        modifier = modifier
                            .size(width = 60.dp, height = 60.dp)
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
                    modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                        Image(
                            painter = painterResource(id = R.drawable.reality_image),
                            contentDescription = "실사",
                            modifier = modifier
                                .size(width = 60.dp, height = 60.dp)
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
        beforeLoginViewModel: BeforeLoginViewModel,
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
                fontSize = 30.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
            Row(
                modifier = modifier
                    .padding(40.dp, 200.dp, 40.dp, 0.dp)
            ) {
                Button(onClick = {
                    updateState(4)
                    beforeLoginViewModel.beforeLoginContent.value.drawSize = BeforeLoginDrawSize.LD
                }, modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.full_body_image),
                        contentDescription = "전신",
                        modifier = modifier
                            .size(width = 60.dp, height = 60.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "전신")
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
                    beforeLoginViewModel.beforeLoginContent.value.drawSize = BeforeLoginDrawSize.SD
                }, modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                    Image(
                        painter = painterResource(id = R.drawable.half_body_image),
                        contentDescription = "반신",
                        modifier = modifier
                            .size(width = 60.dp, height = 60.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "반신")
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


                Button(
                    onClick = {
                        updateState(4)
                        beforeLoginViewModel.beforeLoginContent.value.drawSize =
                            BeforeLoginDrawSize.FACE
                    },
                    modifier = modifier.size(width = 130.dp, height = 80.dp), content = {
                        Image(
                            painter = painterResource(id = R.drawable.only_face_image),
                            contentDescription = "FACE",
                            modifier = modifier
                                .size(width = 60.dp, height = 60.dp)
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
        beforeLoginViewModel: BeforeLoginViewModel,
        updateState: (Int) -> Unit,
        navController: NavController
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
                fontSize = 30.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
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
                onClick = {
                            navController.navigate(ScreenInfo.BeforeLoginResult.name)
                            beforeLoginViewModel.getStableDiffusion()
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
                    text = "결과 보러 가기!",
                    fontFamily = FontData.maruboriFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }

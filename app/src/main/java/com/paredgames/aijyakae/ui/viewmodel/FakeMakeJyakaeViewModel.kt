package com.paredgames.aijyakae.ui.viewmodel

import android.graphics.Bitmap
import com.paredgames.aijyakae.data.dto.MakeJyakaeContent
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.util.DrawingStyle
import com.paredgames.aijyakae.data.util.ModelId
import com.paredgames.aijyakae.data.util.Resolution
import com.paredgames.aijyakae.data.util.SamplingMethod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeMakeJyakaeViewModel(

) :IJyakaeViewModel {
    override val _makeJyakaeContent: StateFlow<MakeJyakaeContent>
        get() = MutableStateFlow(
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
        )
    override val _loadingStateFlow: StateFlow<Boolean>
        get() = MutableStateFlow(false)
    override val _response: StateFlow<TextTwoImageResponseDTO>
        get() = MutableStateFlow(TextTwoImageResponseDTO())
    override val _isFinal: StateFlow<Boolean>
        get() = MutableStateFlow(false)

    override val makeJyakaeContent: StateFlow<MakeJyakaeContent>
        get() = MutableStateFlow(
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
        )
    override val loading: StateFlow<Boolean>
        get() = MutableStateFlow(false)
    override val response: StateFlow<TextTwoImageResponseDTO>
        get() = MutableStateFlow(TextTwoImageResponseDTO())
    override val isFinal: StateFlow<Boolean>
        get() = MutableStateFlow(false)

    override fun getPreferenceData(key: String, defaultValue: String): String {
        TODO("Not yet implemented")
    }

    override fun setPreferenceData(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override fun downloadImage(bitmap: Bitmap, title: String) {
        TODO("Not yet implemented")
    }

    override fun setInitialState() {
        TODO("Not yet implemented")
    }

    override fun addAd() {
        TODO("Not yet implemented")
    }

    override fun billingPayment() {
        TODO("Not yet implemented")
    }

    override fun verifyBilling() {
        TODO("Not yet implemented")
    }
}
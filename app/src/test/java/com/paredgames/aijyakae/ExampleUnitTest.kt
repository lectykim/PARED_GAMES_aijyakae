package com.paredgames.aijyakae

import android.util.Log
import com.paredgames.aijyakae.data.dto.FetchQueuedRequestDTO
import com.paredgames.aijyakae.data.dto.FetchQueuedResponseDTO
import com.paredgames.aijyakae.data.dto.TextTwoImageResponseDTO
import com.paredgames.aijyakae.data.repository.MakeJyakaeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import retrofit2.Response
import java.io.IOException
import java.net.URL

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun processing_test(){


    }


}


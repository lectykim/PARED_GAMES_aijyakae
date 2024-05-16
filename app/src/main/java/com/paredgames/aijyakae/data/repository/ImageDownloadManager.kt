package com.paredgames.aijyakae.data.repository

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.contentValuesOf
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class ImageDownloadManager (private val context:Context){


    fun downloadImage(bitmap: Bitmap,title:String) {
        val pictureFolder = Environment.getExternalStorageDirectory()
            .toString() + "/" + Environment.DIRECTORY_PICTURES
        val savePath = "$pictureFolder/Aijyakae"
        val imageFolder = File(savePath).apply {
            if (!isDirectory) mkdirs()
        }
        val completeFileName = "/$title.jpg"
        FileOutputStream(savePath + completeFileName).use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, it)
            it.flush()
        }
// 미디어 스캔
        val intent = Intent(
            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
            Uri.parse("file://${imageFolder.absolutePath + completeFileName}")
        )
        context.sendBroadcast(intent)


    }







}
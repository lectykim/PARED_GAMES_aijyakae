package com.paredgames.aijyakae.data.repository

import android.R.attr.path
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import okio.Path.Companion.toPath
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.nio.file.Files


class ImageDownloadManager (private val context:Context){


    @RequiresApi(Build.VERSION_CODES.O)
    fun downloadImage(bitmap: Bitmap, title:String) {



        val dataFolder = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Aijyakae" )
        Log.d("parent",dataFolder.absolutePath)

        if(!dataFolder.exists()){
            Log.d("mkdir successed","succesfully created directory "+dataFolder.mkdirs())
            Files.createDirectories(dataFolder.toPath())
        }else{
            Log.d("file exists","true")
        }

        val item = File(dataFolder,"$title.jpg")

        if(item.exists()){
            item.delete()
        }
        Log.d("media_file","successfully created " + item.createNewFile())



        FileOutputStream(item).use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            it.flush()
        }
        Log.d("file download","success")

        // 미디어 스캔
        val intent = Intent(
            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
            Uri.parse("file://${item}")
        )
        context.sendBroadcast(intent)

    }







}
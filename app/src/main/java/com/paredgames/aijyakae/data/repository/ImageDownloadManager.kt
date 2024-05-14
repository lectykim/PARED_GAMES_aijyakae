package com.paredgames.aijyakae.data.repository

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class ImageDownloadManager (private val context:Context){

    fun downloadImage(bitmap: Bitmap,title:String) {
        val rootPath:String =Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        ).toString()

        val dirName = "/"+"Aijyakae"
        val fileName = "$title.jpg"
        val savePath = File(rootPath+dirName)
        savePath.mkdirs()

        val file = File(savePath,fileName)

        try{
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)
            out.flush()
            out.close()

            context.sendBroadcast(
                Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())
                )
            )

        }catch (e:Exception){
            e.printStackTrace()
        }




    }







}
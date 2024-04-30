package com.paredgames.aijyakae.data.repository

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import java.io.File

class ImageDownloadManager (private val context:Context){

    private val outputFile= Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOWNLOADS + "/Aijyakae")

    private var downloadManager:DownloadManager
    private var downloadQueueId:Long = 0
    private lateinit var file:File
    private lateinit var dir:File
    private val dirName = "Aijyakae"

    init {
        downloadManager= context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        if (!outputFile.getParentFile()?.exists()!!) {
            outputFile.getParentFile()?.mkdirs();
        }


    }

    fun downloadImage(url:Uri,title:String) {

        // DownloadManager.Request을 설정하여 DownloadManager Queue에 등록하게 되면 큐에 들어간 순서대로 다운로드가 처리된다.
        // DownloadManager.Request : Request 객체를 생성하며 인자로 다운로드할 파일의 URI를 전달한다
        val downloadRequest: DownloadManager.Request = DownloadManager.Request(url)
        val pathSegmentList: List<String> = url.pathSegments

        downloadRequest.setTitle(title)
        downloadRequest.setDescription("Jyakae Image")

        downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        

        downloadRequest.setDestinationUri(Uri.fromFile(outputFile))


        downloadQueueId = downloadManager.enqueue(downloadRequest)
    }







}
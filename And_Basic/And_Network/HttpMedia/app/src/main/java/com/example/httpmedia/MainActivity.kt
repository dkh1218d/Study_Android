package com.example.httpmedia

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var mp : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if(mp==null){
                val uri = Uri.parse("http://192.168.0.8:8090/yasumi.mp3")
                mp = MediaPlayer.create(this, uri)
                mp?.start()
            }

            thread {
                val server = URL("http://192.168.0.8:8090/favicon.ico")
                val conn = server.openConnection() as HttpURLConnection

                val bitmap = BitmapFactory.decodeStream(conn.inputStream)

                runOnUiThread {
                    imageView.setImageBitmap(bitmap)
                }
            }
        }

        button2.setOnClickListener {
            if(mp!=null){
                mp?.stop()
                mp = null
            }
        }

        button3.setOnClickListener {
            if(videoView.isPlaying == false){
                val uri = Uri.parse("http://192.168.0.8:8090/yu.mp4")
                videoView.setVideoURI(uri)
                videoView.start()
            }
        }
        button4.setOnClickListener {
            if(videoView.isPlaying == true){
                videoView.stopPlayback()
            }
        }
    }
}
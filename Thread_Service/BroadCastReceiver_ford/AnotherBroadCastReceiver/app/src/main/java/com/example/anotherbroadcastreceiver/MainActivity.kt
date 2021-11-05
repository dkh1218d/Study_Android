package com.example.anotherbroadcastreceiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val brIntent = Intent("testbroadcast1")
            sendBroadcast(brIntent)
            // 8.0 이전 버전에서는 첫 번째 애플리케이션이 동작하지 않아도 호출이 된다
            // 8.0 이상 버전에서는 첫 번쨰 애플리케이션이 동작 중 BroadCastReceiver의 이름이 등록되어 있어 호출이 된다
        }
    }
}
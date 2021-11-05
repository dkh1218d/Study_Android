/* BroadCastReceiver : AndroidOS에서 특정 상황에 발생하는 메시지를 받아들여 동작하는 실행 단위
반드시 외부에서 접근하기 위한 이름을 가져야 한다
평소에는 동작하지 않다가 동작요청이 발생하면 동작
IntentFilter를 통해 이름을 등록
Android 8.0 부터는 개발자가 만든 BroadCastReceiver와 OS가 제공하는 일부 BroadCastReceiver는 코드를 통해서만 등록
-> 보안상의 이유로 BroadCastReceiver를 가진 Application 내부에서만 사용하기 위한 제약
*/
package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val br = TestReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 8.0 이상부터는 코드를 통해 등록하고 해제 해야 한다
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val filter = IntentFilter("testbroadcast1")
            // 이름 등록
            registerReceiver(br, filter)
        }


        button.setOnClickListener {
            /*val brIntent = Intent(this, TestReceiver::class.java) // 내부 애플리케이션 실행*/
            val brIntent = Intent("testbroadcast1") // 등록된 이름으로 요청 - 8.0 이상에서는 동작 X
            // BroadCastReceiver 요청
            sendBroadcast(brIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Activity가 종료될 때 BroadCastReceiver 등록을 해제
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            unregisterReceiver(br)
        }
    }
}
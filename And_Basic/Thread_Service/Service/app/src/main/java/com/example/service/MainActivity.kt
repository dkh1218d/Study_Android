/* Service : Android 4대 구성요소, 백그라운드 처리를 위한 요소
   Service에서 Thread를 운용한다
   8.0 이상부터 절전모드가 되거나 메모리가 부족해지는 상황에서 OS에 의해 Service 제거가 가능
   보안상의 이유로 장시간 운용중인 Service를 강제 종료하기도 한다
   -> ForegroundService : 현재 단말기에서 Service를 통해 백그라운드에서 작업중이라는 것을 고지하는 목적
   - Notification 으로 고지하는 것을 전제한다 ex) 다운로드, 음악 재생
   FOREGROUND_SERVICE 권한 허용 필요
*/
package com.example.service

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Service 객체 생성
        val service = Intent(this, MyService::class.java)
        button.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                startForegroundService(service)
            else startService(service)
        }

        button2.setOnClickListener {
            stopService(service)
        }
    }
}
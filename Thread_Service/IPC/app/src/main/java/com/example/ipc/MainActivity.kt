/* IPC : Activity에서 실행중인 Service를 제어하거나 데이터를 사용하는 등의 작업이 필요할 때 사용하는 개념
   Activity가 종료되더라도 현재 실행중인 Service에 접속하고 Service가 가지고 있는 메서드를 호출 할 수 있는 개념
   데이터를 반환 받아 사용 가능

 */
package com.example.ipc

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 접속한 서비스 객체
    var ipcService:MyService? = null

    // 서비스 접속을 관리하는 객체
    val connection = object : ServiceConnection{

        // 서비스에 접속할 때 메서드 / 변수(Service 이름, Service에서 보낸 Binder 객체)
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // 서비스 추출
            val binder = service as MyService.LocalBinder
            ipcService = binder.getService()
        }
        // 서비스 접속을 해제했을 때
        override fun onServiceDisconnected(name: ComponentName?) {
            ipcService = null
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 서비스가 가동중이 아니라면 가동 (패키지를 포함한 이름)
        val runningService = isServiceRunning("com.example.ipc.MyService")

        val serviceIntent = Intent(this, MyService::class.java)

        button.setOnClickListener {
            Log.d("test", "${runningService}")
            if(!runningService){
                // val service = Intent(this, MyService::class.java)
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    startForegroundService(serviceIntent)
                }else
                    startService(serviceIntent)
            }else
                textView.text = "이미 서비스가 가동중입니다"
        }

        // 서비스에 접속 한다
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)

        button2.setOnClickListener {
            var value = ipcService?.getNum()
            textView2.text = "value : ${value}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 서비스 접속 해제
        unbindService(connection)
    }

    // Service 실행 여부 검사 메서드
    fun isServiceRunning(name:String) : Boolean{
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        // 현재 실행중인 서비스들 추출
        // 동작 중인 서비스 전체 -> 보안상 Deprecated(고지 목적) -> 같은 애플리케이션 내부의 Service만 추출하도록 변경
        val serviceList = manager.getRunningServices(Int.MAX_VALUE)
        for(info in serviceList){
            // 원하는 서비스 이름인지 검사
            if(info.service.className == name){
                return true
            }
        }
        return false
    }
}
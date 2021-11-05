/*
Thread : 여러 처리를 비동기적 처리하는 도구
비동기적 처리 외 네트워크에 관련된 코드는 전부 Thread로 운영해야한다(구버전)
Activity의 코드를 처리하기 위해 AndroidOS가 Thread를 발생 - MainThread 혹은 UI Thread
MainThread가 어떠한 작업을 하지않는 유휴상태일때만 화면 작업이 가능하다
오래 걸리는 작업은 별도의 Thread를 발생시켜 처리 - MainThread를 유휴상태로 유지

1. 화면에 관련된 작업이나 터치처리는 MainThread에서만 처리 가능
2. MainThread가 작업 처리중이라면 화면 작업이나 터치 작업을 할 수가 없다
3. 오래 걸리는 작업은 별도의 Thread운영을 해야 한다
-> 개발자가 발생시킨 Thread에서 화면처리를 하면 오류가 발생
ex) 다운로드 중 화면처리 = 다른 Thread를 운영하면서 MainThread로 화면처리를 할 필요가 있다

* Android8.0 이상에서는 개발자가 발생시킨 Thread에서 화면처리가 가능
-> 하위버전을 포함하기 위해서는 반드시 MainThread에서 해야 한다
*/
package com.example.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var isrunnig = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = System.currentTimeMillis().toString()
        }
        button2.setOnClickListener {
            isrunnig = false
        }

    // Thread를 상속받은 클래스
        /*val thread1 = object : Thread(){
            override fun run() {
                super.run()
                isrunnig = true
                // 무한 반복문으로 MainThread가 화면작업을 하지 못한다
                while (isrunnig) {
                    SystemClock.sleep(1000)
                    Log.d("test", System.currentTimeMillis().toString())
                    textView2.text = "Thread : ${System.currentTimeMillis().toString()}"
                    // Android8.0 미만에서는 화면 처리 하면 오류
                }
            }
        }*/

        // Thread 고차함수(람다) 지원 / start메서드를 쓰지 않아도 작동
        thread {
            isrunnig = true
            while (isrunnig){
                SystemClock.sleep(1000)
                Log.d("test", System.currentTimeMillis().toString())
            }
        }
    }


    // Activity가 종료될 때 동작하는 메서드
    override fun onDestroy() {
        super.onDestroy()
        isrunnig = false
    }
}
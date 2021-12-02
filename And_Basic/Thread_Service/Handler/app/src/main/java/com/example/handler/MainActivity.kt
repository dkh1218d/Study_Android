/* Handler : 짧은 작업을 반복해야 할 경우 사용
   While문을 사용할 경우 무한루프로 MainThread가 종료되지 않아 화면처리가 불가능하다
   쉬는 작업도 작업이기에 MainThread가 가동한다
   Handler는 중간에 잠깐씩 완전히 작업을 멈추는 것이기에 화면처리 가능

*  Handler는 개발자가 OS에게 작업 수행을 요청하는 역할
   요청을 하면 OS는 작업을 하지 않을 떄 요청을 처리 - MainThread에서
   5초 이상의 오래걸리는 작업은 하지 않는 것이 좋다
*/
package com.example.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = "버튼 클릭 ${System.currentTimeMillis()}"
        }

        /*while (true){
            // 쉬는 행위도 작업이기에 멈추더라도 화면처리를 하지 않는다
            SystemClock.sleep(1000)
            textView2.text = "while문 : ${System.currentTimeMillis()}"
        }*/

        // Handler 객체 생성 / Looper : 대기 하면서 작업요청이 들어오면 처리해주는 역할
        val handler = Handler(Looper.myLooper()!!)

        // 처리 한번에 대한 작업을 구현
        val thread1 = object : Thread() {
            override fun run() {
                super.run()
                textView2.text = "handler : ${System.currentTimeMillis()}"
                // 반복작업 시 / handler 작업에 딜레이를 걸고 어떠한 작업도 하지 않는다 (한번의 작업이 너무 오래걸리면 안된다)
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(thread1)
    }
}
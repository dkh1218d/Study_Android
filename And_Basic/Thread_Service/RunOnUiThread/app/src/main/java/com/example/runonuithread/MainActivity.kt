// RunOnUiThread : 개발자가 발생시킨 일반 Thread에서 코드 일부를 MainThread가 처리하도록 하는 메서드
// Handler를 대신하여 Thread를 처리할 수 있다
// Android 하위버전에서 일반 Thread를 처리하면서 화면처리도 할 수 있는 장점이 있다
package com.example.runonuithread

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
            textView.text = "버튼 클릭 : ${System.currentTimeMillis()}"
        }

        isrunnig = true
        thread {
            while(isrunnig){
                Log.d("test", "오래 걸리는 작업 : ${System.currentTimeMillis()}")
                SystemClock.sleep(500)

                // 해당 코드를 MainThread가 처리하게 요청
                runOnUiThread(object:Thread(){
                    override fun run() {
                        super.run()
                        textView2.text = "2번 : ${System.currentTimeMillis()}"
                    }
                })

                SystemClock.sleep(500)
                // lambda로 제공
                runOnUiThread {
                    textView.text = "1번 : ${System.currentTimeMillis()}"
                }
            }
        }
    }

    override fun isDestroyed(): Boolean {
        return super.isDestroyed()
        isrunnig = false
    }
}
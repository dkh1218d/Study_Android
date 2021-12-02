// Handler를 이용한 화면 처리
package com.example.handlermessage

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var isrun = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 화면 처리를 위한 Handler
        val handler = object :Handler(Looper.myLooper()!!){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                when(msg.what){
                    0 -> textView1.text = "handler : 0"
                    1 -> textView1.text = "handler : 1"
                    2 -> {
                        textView1.text = "${msg.arg1} + ${msg.arg2}"
                        textView1.append("\n ${msg.obj}")
                    }
                }
            }
        }
        isrun = true
        thread {
            while (isrun){
                Log.d("test", "오래 걸리는 작업 + ${System.currentTimeMillis()}")
                SystemClock.sleep(500)
                // Thread 작업 중 handler의 요청은 MainThread에서 처리해서 화면 처리가 가능
                handler.sendEmptyMessage(0) // what : Handler 안에서 작업을 구분하는 index
                SystemClock.sleep(500)
                handler.sendEmptyMessage(1) // 여러 화면 처리를 분기로 처리 가능
                SystemClock.sleep(1000)

                val msg = Message()
                msg.what = 2
                // Message 객체를 생성해 데이터를 넘겨줄 수 있다 (정수 2개, 객체 1개)
                msg.arg1 = 100
                msg.arg2 = 200
                msg.obj = listOf("하나", "둘", "셋")
                handler.sendMessage(msg) // Message를 전송
            }
        }

        val thread1 = object : Thread(){
            override fun run() {
                super.run()
                if(progressBar.progress<100){
                    progressBar.progress+=2
                    textView2.text = "${progressBar.progress}%"
                    handler.postDelayed(this, 500)
                }else{
                    handler.removeCallbacksAndMessages(null)
                    button2.text = "Success"
                }
            }
        }

        button.setOnClickListener {
            textView.text = System.currentTimeMillis().toString()
        }

        var load = true
        button2.setOnClickListener {
            if(progressBar.progress<100&&load){
                handler.post(thread1)
                load = false
                button2.text = "Stop"
            }else if(progressBar.progress<100&&!load){
                // Handler 정지
                handler.removeCallbacksAndMessages(null)
                load = true
                button2.text = "Load"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isrun = false
    }
}
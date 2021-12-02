package com.example.onactivityresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        // MainActivity로  데이터를 전송
        val t_intent = Intent(this, MainActivity::class.java).apply{
            putExtra("Third","")
        }

        textView3.text = "${intent.getStringExtra("3_1")}"
        button3.setOnClickListener {
            t_intent.putExtra(Activity.RESULT_CANCELED.toString(),"Cancle")
            setResult(200, t_intent)
            // ResultCode 정수값을 할당 data에는 intent객체의 정보를 할당
            // 미리 정의 된 result변수가 존재

            finish()
        }
        button4.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
        button5.setOnClickListener {
            setResult(Activity.RESULT_FIRST_USER)
            finish()
        }
        button6.setOnClickListener {
            setResult(Activity.RESULT_FIRST_USER+1)
            finish()
        }

    }
}
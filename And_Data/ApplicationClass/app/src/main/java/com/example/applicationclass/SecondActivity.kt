package com.example.applicationclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val app = application as AppClass

        textView2.text = "${app.id}"
        textView2.append("\n${app.password}")

        second_btn.setOnClickListener {
            app.id = id_text.text.toString()
            app.password = password_text.text.toString()
            textView2.text = "${app.id}"
            textView2.append("\n${app.password}")
        }

        second_btn2.setOnClickListener {
            app.id = "입력값이 없습니다"
            app.password = null
            finish()
        }
    }
}
package com.example.onactivityresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // intent.getXXXExtra : name과 Defaultvalue를 가진다 (String은 name만)
        textView2.text = intent.getStringExtra("2_1")
        button2.setOnClickListener {
            setResult(100)
            finish()
        }
    }
}
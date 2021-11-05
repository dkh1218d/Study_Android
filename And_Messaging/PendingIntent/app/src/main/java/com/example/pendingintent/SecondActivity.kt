package com.example.pendingintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val str = intent.getStringExtra("data1")
        val idx = intent.getIntExtra("data2", 0)

        textView2.append("\n${str} : ${idx}")
    }
}
package com.example.intentreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val data1 = intent.getStringExtra("first")
        textView.append("\ndata1 : ${data1}")
        button2.setOnClickListener {
            val result = Intent()
            result.putExtra("second", 999)
            result.putExtra("third", "second and third")
            setResult(RESULT_OK, result)
            finish()
        }
    }
}
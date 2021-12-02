package com.example.intentreference2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val data1 = intent.getStringExtra("first")
        textView.append("\ndata1 : ${data1}")
        button.setOnClickListener {
            val result = Intent()
            result.putExtra("second", 777)
            result.putExtra("third", "(Third Activity) second and third")
            setResult(RESULT_OK, result)
            finish()
        }
    }
}
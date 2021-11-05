package com.example.parcelable

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val first = intent.getParcelableExtra<TestClass>("first")
        textView2.text = "${first?.data1}\n"
        textView2.append(first?.data2)

        button2.setOnClickListener {
            val tc2 = TestClass()
            tc2.data1 = 200
            tc2.data2 = "second"

            val r_intent = Intent()
            r_intent.putExtra("second", tc2)
            setResult(RESULT_OK, r_intent)
            finish()
        }
    }
}
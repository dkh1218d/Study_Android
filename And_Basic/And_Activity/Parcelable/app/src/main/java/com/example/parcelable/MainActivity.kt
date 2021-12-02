// 객체직렬화 : Parcelable을 통해 Activity간 객체 전달
package com.example.parcelable

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val regis = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val str = it.data?.getParcelableExtra<TestClass>("second")
                textView3.text = "data1 : ${str?.data1}, data2 : ${str?.data2}"
            }
        }

        button1.setOnClickListener {
            val s_intent = Intent(this@MainActivity, SecondActivity::class.java)

            val tc = TestClass()
            tc.data1 = 100
            tc.data2 = "First"

            s_intent.putExtra("first", tc) // Class를 Parcelable로 구현해서 value 저장

            regis.launch(s_intent)
        }
    }
}
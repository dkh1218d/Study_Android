package com.example.activityanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        button2.setOnClickListener {
            finishActivity()
        }
    }

    // Back 버튼을 눌렀을 때 호출되는 메서드(종료)
    override fun onBackPressed() {
        finishActivity()
    }

    // Activity 종료
    fun finishActivity(){
        finish()
        // finish 다음에 Animation 설정
        // overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        overridePendingTransition(R.anim.slide_xml3, R.anim.slide_xml4)
    }
}
/*  ActionBar Customizing : ActionBar를 커스터마이징 하기 위해서는 기존의 ActionBar를 사라지게 해야 한다

 */
package com.example.customactionbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_actionbar.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ActionBar Custimizing 설정
        supportActionBar?.setDisplayShowCustomEnabled(true) // 커스텀 허용
        supportActionBar?.setDisplayHomeAsUpEnabled(false)  // home버튼
        supportActionBar?.setDisplayShowHomeEnabled(false)  //
        supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀

        // layout을 통해 view 생성
        val topbar = layoutInflater.inflate(R.layout.custom_actionbar, null)

        supportActionBar?.customView = topbar

        topbar.run {
            custom_text.text = "CustomActionBar"
            custom_text.setTextColor(Color.WHITE)

            custom_button.setOnClickListener {
                // Activity import를 해야 한다
                textView.text = "ActionBar Button Click"
            }
        }
    }
}
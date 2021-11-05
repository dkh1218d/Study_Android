// Toast : 일정시간이 지나면 사라지는 간단한 출력용 Message
// AndroidOS에 메시지 출력을 요청하고 OS에 의해 나타나는 메시지
// 단말기 내의 모든 애플리케이션, 구성요소가 요청 가능하며 애플리케이션에 관계 없이 요청 순서대로 출력
// Android11 (API30) 이상부터는 Toast에 CallBack 설정 가능
package com.example.toast

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // Toast 객체 생성 (context, 표시 문자열, 표시 시간)
            val msg = Toast.makeText(this, "Show Basic Toast", Toast.LENGTH_SHORT)

            // CallBack 객체 생성(추상클래스)
            // 현 버전은 CallBack이 지원되지 않기에 버전별로 분기
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){ // 11버전 = R
                // 버전이 맞지 않을 시 null값 할당
                val callback = object : Toast.Callback() {
                    // Toast객체가 사라질 때 호출
                    override fun onToastHidden() {
                        super.onToastHidden()
                        textView.text = "toast 객체 제거"
                    }
                    // Toast객체가 나타날 때 호출
                    override fun onToastShown() {
                        super.onToastShown()
                        textView.text = "toast 객체 생성"
                    }
                }
                msg.addCallback(callback)
            }
            msg.show()
        }

        // Custom Toast
        button2.setOnClickListener {
            val toast_view = layoutInflater.inflate(R.layout.custom_toast, null)

            // Toast의 이미지 변경 세팅 run 메서드에 람다 설정
            toast_view.run {
                toast_image.setImageResource(R.drawable.testimage)
                toast_text.text = "First Test Toast"
            }

            // Background / androidOS에서 제공하는 frame
            toast_view.setBackgroundResource(android.R.drawable.toast_frame)

            val msg2 = Toast(this)
            // Android 버전에 따라 메시지 커스텀을 할 시에는 Toast보다는 Snackbar를 사용한다
            msg2.view = toast_view

            // Toast의 위치 조정
            msg2.setGravity(Gravity.CENTER, 0, 500)

            // Toast의 시간 설정 / Default : Length_short
            msg2.duration = Toast.LENGTH_LONG

            msg2.show()
        }
    }
}
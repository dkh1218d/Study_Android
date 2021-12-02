/*  Android에서는 Activity가 context를 상속받기 때문에 this로 받아올 수 있다
    그 외에는 context를 구하는 메서드를 통해서 설정할 수 있다
    LayoutParam : View가 배치될 때 필요한 공통속성을 설정하는 객체
 */
package com.example.codeview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View를 생성하는 것은 Activity에서만 가능하다
        val btn1 = Button(this)
        btn1.text = "추가 버튼"
        btn1.setBackgroundColor(Color.CYAN)
        val progress = ProgressBar(this)
        val img1 = ImageView(this)
        img1.setImageResource(R.mipmap.ic_launcher)

        // layout 속성(가로, 세로)
        val param1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        btn1.setOnClickListener {
            textView.text = "추가 버튼 클릭"
        }

        button.setOnClickListener {
            container.removeAllViews()
            container.addView(btn1)
            container.addView(progress)
            container.addView(img1)
        }

        button2.setOnClickListener {
            container.removeAllViews()
        }
    }
}
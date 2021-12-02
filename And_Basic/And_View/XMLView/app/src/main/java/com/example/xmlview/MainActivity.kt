/*  layoutInflate : layout폴더의 xml파일을 통해 화면을 구현 -> Activity가 처음 나타날 때의 모습
    그 이후의 화면에 View를 구성하는 등의 작업을 위해서는 코드를 통해 View를 구현해야 한다
    layoutInflate를 통해 xml로 만든 화면을 view 객체로 사용이 가능
 */
package com.example.xmlview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isEmpty
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sub_layout1.view.*
import kotlinx.android.synthetic.main.sub_layout2.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sub1 = layoutInflater.inflate(R.layout.sub_layout1, null)
        val sub2 = layoutInflater.inflate(R.layout.sub_layout2, null)
        // layout을 통해 생성한 view가 container에 자동으로 추가한다
        val sub3 = layoutInflater.inflate(R.layout.sub3_layout, container)

        sub1.run {
            sub1_text.text = "sub1 layout"
            sub1_btn.setOnClickListener {
                // view 제거(sub1)
                container.removeView(sub1)
                container.addView(sub2)
            }
        }
        sub2.run {
            sub2_text.text = "${container.isEmpty()}"
            sub2_btn.setOnClickListener {
                container.removeView(sub2)
            }
        }

        button.setOnClickListener {
            container.removeAllViewsInLayout()
            container.addView(sub1)
        }
        button2.setOnClickListener {
            // View를 전부 제거
            container.removeAllViewsInLayout()
        }

        button3.setOnClickListener {
            // root를 정해 생성한 layout은 단일 제거가 불가능
            container.removeView(sub3)
        }
    }
}
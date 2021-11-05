/* ActivityAnimation : FragmentAnimation과 다르게 제거시의 Animation까지 설정해야 한다
    translate                   태그
    android:fromXDelta="-100%"  가로 축 이동 (왼쪽 기준)
    android:toXDelta="0%"
    android:duration="1000"     애니메이션 유지 시간
    android:fromYDelta="-100%"  세로 축 이동
    android:toYDelta="0%"
 */

package com.example.activityanimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

            // startActivity 다음에 Animation 설정
            // overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            overridePendingTransition(R.anim.slide_xml1, R.anim.slide_xml2)
        }
    }
}
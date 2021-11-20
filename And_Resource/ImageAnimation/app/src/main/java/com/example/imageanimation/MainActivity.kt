/*  연속 동장 Image를 이용해 움짤을 만듬

 */
package com.example.imageanimation

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ani = getDrawable(R.drawable.animation)
        imageView.setImageDrawable(ani)

        // 애니메이션 추출
        val animate = imageView.drawable as AnimationDrawable

        button.setOnClickListener {
            animate.start()
        }

        button2.setOnClickListener {
            animate.stop()

        }
    }
}
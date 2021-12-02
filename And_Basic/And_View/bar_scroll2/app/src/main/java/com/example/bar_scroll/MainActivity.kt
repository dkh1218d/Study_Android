package com.example.bar_scroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView1.text = ""
        seekBar1.progress = 10
        seekBar2.progress = 5

        seekBar1.setOnSeekBarChangeListener(lit1)

        // ratingBar 별점(rating, numStars, stepSize(기본0.5))
        // 이미지 추가는 이미지 XML 작성 필요
        button.setOnClickListener {
            textView1.text = "Rating : ${ratingBar.rating}"
        }
        button2.setOnClickListener {
            ratingBar.rating = 1.0f // float값으로 설정
        }
        ratingBar.setOnRatingBarChangeListener(rat)

        // ScrollBar
        button3.setOnClickListener {
            /*// 지정 위치 이동
            scroll1.scrollTo(0,600)
            scroll2.scrollTo(600,0)
            // 애니메이션 이동
            scroll1.smoothScrollTo(0, 600)
            scroll2.smoothScrollTo(600,0)*/
            // 현재 위치에서 좌표만큼 이동
            scroll1.smoothScrollBy(0, 600)
            scroll2.smoothScrollBy(600, 0)
        }
        scroll1.setOnScrollChangeListener(sco)
        scroll2.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            scroll_text.text = "X : ${oldScrollX} -> ${scrollX}"
        }
    }

    val lit1 = object : SeekBar.OnSeekBarChangeListener {
        // 시커바가 움직이면 동작하는 리스너
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            when(seekBar?.id){
                R.id.seekBar1 -> seekBar2.incrementProgressBy(2)
                R.id.seekBar2 -> seekBar1.incrementProgressBy(-2)
            }
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }
    
    val rat = object : RatingBar.OnRatingBarChangeListener {
        override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
            when(ratingBar?.id){
                R.id.ratingBar -> {
                    textView2.text = "rating\n"
                    if(fromUser) textView2.append("사용자설정")
                }
            }
        }
    }
    // ScrollBar 고차함수
    val sco = object : View.OnScrollChangeListener {
        override fun onScrollChange(
            v: View?,
            scrollX: Int,
            scrollY: Int,
            oldScrollX: Int,
            oldScrollY: Int
        ) {
            when(v?.id){
                // 이전좌표값 -> 현재 좌표값
                R.id.scroll1 -> scroll_text.text = "Y : ${oldScrollY} -> ${scrollY}"
            }
        }
    }
}
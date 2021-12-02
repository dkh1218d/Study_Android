// SnackBar : Toast의 업그레이드 버전
// Android11 이후부터는 커스터마이징에 SnackBar 이용 권장
// SnackBar는 새로운 View를 구성하기 위한 메서드나 프로퍼티가 X
// 기존 Layout을 추출해서 View를 추가해 처리
package com.example.snackbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_layout.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // SnackBar는 Android 내장이 아니기에 Google추가 라이브러리 세팅 필요
            val snack1 = Snackbar.make(it, "Basic SnackBar", Snackbar.LENGTH_LONG) // (snackBar를 표시할 view, 기본 문자열, 표시 시간)
            // Custom
            snack1.setTextColor(Color.WHITE)
            snack1.setBackgroundTint(Color.BLACK)
            snack1.animationMode = Snackbar.ANIMATION_MODE_FADE // 애니메이션 효과
            snack1.setAction("실행 취소"){
                // Action에 대해 고차함수로 제공
                textView.text = "실행이 취소 되었습니다"
            }

            val callback = object : BaseTransientBottomBar.BaseCallback<Snackbar>(){
                override fun onShown(transientBottomBar: Snackbar?) {
                    super.onShown(transientBottomBar)
                    textView2.text = "Shown SnackBar"
                }

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    textView2.text = "Closwd SnackBar"
                }
            }
            snack1.addCallback(callback)
            snack1.show()
        }

        button2.setOnClickListener {
            val snack2 = Snackbar.make(it, "", Snackbar.LENGTH_SHORT)
            val snackView = layoutInflater.inflate(R.layout.custom_layout, null)

            snackView.run {
                customimage.setImageResource(R.drawable.testimage2)
                customtext.text = "새로 추가된 View"
                customtext.setTextColor(Color.WHITE)
            }

            // snackBar의 layout 추출 (getter)
            val snackbarlayout = snack2.view as Snackbar.SnackbarLayout
            // 추출한 layout에 view를 추가
            snackbarlayout.addView(snackView)

            val snackText = snackbarlayout.findViewById<TextView>(com.example.snackbar.R.id.customtext)
            /*snackText.visibility = View.INVISIBLE*/

            snack2.show()
        }
    }
}
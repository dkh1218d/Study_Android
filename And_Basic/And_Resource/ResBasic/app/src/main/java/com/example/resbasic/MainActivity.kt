/*  Ressource : 애플리케이션에서 사용하는 다양한 미디어, 데이터 파일
    Android는 res폴더에서 관리
    이미지와 xml파일을 resource로 관리하며 xml파일을 안드로이드에서 정의한 데이터들을 관리하는 용도로 사용

 */
package com.example.resbasic

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // R.태그이름.value name
            // xml은 setText로 불러온다
            // textView.setText(R.string.str1)

            // resource 객체를 호출
            // val str1 = resources.getString(R.string.str1)
            // 메서드가 자체적으로 getString을 제공한다
            val str1 = getString(R.string.str1)
            textView.text = str1
        }

        button2.setOnClickListener {
            // format 문자열 사용
            val str2 = getString(R.string.str2)
            val fom = String.format(str2, "홍길동", 20)
            textView.text = fom
        }

        button3.setOnClickListener {
            val arr = resources.getStringArray(R.array.array1)
            textView.text = ""

            for(str in arr){
                textView.append("${str}\n")
            }
        }

        button4.setOnClickListener {
            // val color = Color.rgb(20, 230, 240)
            // val color = Color.argb(70, 20, 230, 240) // 투명도
            val color = getColor(R.color.c1)
            textView.setTextColor(color)
        }

        button5.setOnClickListener {
            /*  기종별로 단말기 사이즈가 다르기 때문에 가변형 단위를 사용
                ppi (pixel per inch) : 가로세로 1인치 안의 픽셀 갯수
                dpi (dot per inch : 가로세로 1인치 안의 점의 갯수

                px = 픽셀의 갯수
                * dp = 160ppi에서 1dp = 1px / 320ppi -> 1dp = 2px
                * sp = 단말기 설정의 글자 크기에 따라 가변 160ppi에서 1sp = 1px
                pt = 1pt = 1/72 inch

                dimen -> 사이즈 단위 xml
            */
            val px = resources.getDimension(R.dimen.px)
            val inch = resources.getDimension(R.dimen.inch)
            val mm = resources.getDimension(R.dimen.mm)
            val pt = resources.getDimension(R.dimen.pt)
            val dp = resources.getDimension(R.dimen.dp)
            val sp = resources.getDimension(R.dimen.sp)

            textView.text = "px : $px\n"
            textView.append("inch : $inch\n")
            textView.append("mm : $mm\n")
            textView.append("pt : $pt\n")
            textView.append("dp : $dp\n")
            textView.append("sp : $sp\n")

            // 코드에서는 해당 단위의 px로 가져와서 별도로 계산을 해줘야 한다
            textView.textSize = 15 * sp

        }
    }
}
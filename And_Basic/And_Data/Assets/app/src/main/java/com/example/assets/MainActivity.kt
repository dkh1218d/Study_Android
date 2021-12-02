/*  Assets : 파일들을 계층적인 폴더 구조로 만들어 관리하기 위한 개념
    - 하위 디렉토리 생성 가능
    Assets폴더는 res폴더 내부가 아니기에 리소스(R 클래스)로 관리할 수 없다
    assets를 통해 폰트 파일을 쉽게 사용할 수 있다
 */
package com.example.assets

import android.graphics.Typeface
import android.hardware.lights.Light
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // Stream 가져오기
            val inputStream = assets.open("Text/data1.txt")
            val isr = InputStreamReader(inputStream, "UTF-8")
            val buf = BufferedReader(isr)

            var str :String? = null
            val sbu = StringBuffer()

            do{
                str = buf.readLine()
                if(str!=null){
                    sbu.append("$str\n")
                }
            }while(str!=null)

            buf.close()
            textView.text = "${sbu.toString()}"
        }

        button2.setOnClickListener {
            val inputStream = assets.open("Text/data2.txt")
            val isr = InputStreamReader(inputStream, "UTF-8")
            val buf = BufferedReader(isr)

            var str :String? = null
            val sbu = StringBuffer()

            do{
                str = buf.readLine()
                if(str!=null){
                    sbu.append("$str\n")
                }
            }while(str!=null)

            buf.close()
            textView2.text = "${sbu.toString()}"
        }

        button3.setOnClickListener {
            // 폰트 객체 생성
            val face = Typeface.createFromAsset(assets, "Font/NotoSerifKR-Light.otf")
            textView.typeface = face

        }
    }
}
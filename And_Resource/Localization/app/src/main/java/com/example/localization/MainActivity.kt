/*  단말기 대응 : 단말기 별로 상이한 상태에 따라 대응할 수 있도록 지원 (언어, 지역, 해상도, 크기, 버전 등)
              - res폴더 내의 폴더에 수식을 설정해 두면 수식어와 일치하는 단말기에 대해 이미지, 문자열 등을 선택해서
                사용할 수 있도록 제공할 수 있다
    Localization (지역화) : 하나의 애플리케이션으로 다양한 언어 및 국가를 지원하기 위한 개념
                        -  res폴더 내부에 국가코드를 통해 지역화 가능
                           res의 Available qualifiers를 통해 옵션 설정 가능

 */
package com.example.localization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val str = getString(R.string.str1)
        textView.text = str
    }
}
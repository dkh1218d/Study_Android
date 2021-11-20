/*  화면 회전 - layout 폴더의 Orientation 속성을 통해 개별 화면 적용 가능
    layout-port : 세로
    layout-land : 가로

    View 복원 : 안드로이드에서 화면 회전 발생 시 화면을 새롭게 생성
    - onSaveInstanceState 메서드를 통해 복원시 필요 자료를 저장
    - onCreate 메서드에서 복원

    화면 고정 설정 : AndroidManifest.xml 의 Activity 태그에 screenOrientation 설정
 */
package com.example.rotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        if(savedInstanceState==null){
            textView.text = "등장"
        }else{
            textView.text = savedInstanceState.getString("data1")
        }
        button.setOnClickListener {
            textView.text = plain.text
        }

        button2.setOnClickListener {

        }
    }

    // 화면 회전 발생 시 호출되는 메서드
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 복원에 필요한 데이터 저장
        // (key, values)
        outState.putString("data1", textView.text.toString())
    }
}
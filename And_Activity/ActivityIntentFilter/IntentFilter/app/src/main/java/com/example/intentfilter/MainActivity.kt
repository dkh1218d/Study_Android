/* AndroidManifest.xml파일에 기록된 안드로이드 4대 구성요소를 통해
   Intent Filter를 이용해 이름을 설정해 두면 다른 어플리케이션에서 실행이 가능하다
   직접 실행이 아닌 AndroidOs에 설정된 이름의 Activity를 실행 요청을 한다
   구성요소에 이름을 부여해야만 요청이 가능, 이름이 없으면 오류
 */
package com.example.intentfilter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reg = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) {
                val data1 = it.data?.getIntExtra("second", 0)
                val data2 = it.data?.getStringExtra("third")
                textView.text = "Main Activity"
                textView.append("\ndata1 : ${data1}  data2 : $data2")
            }
        }
        button.setOnClickListener {
            val s_intent = Intent("reference second")
            // 다른 Activity의 이름이 중복일 경우 어느 Application의 Activity를 호출할지 묻는 창이 팝업된다
            s_intent.putExtra("first", "First data")
            reg.launch(s_intent)
        }
    }
}
// 다른 Activity를 실행하고 돌아왔을 때 필요한 처리 -> startActivityForResult
/* onActivityResult는 registerForActivityResult로 대체됐다
*  registerForActivityResult : onCreate에서 호출되야 하며 build.gradle에 의존성 추가를 요함
* */

package com.example.onactivityresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val second_act = 100
    val third_act = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*button1_1.setOnClickListener {
            val s_intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(s_intent, second_act)
            // requestcode = 해당 Activity를 구분하는 정수값
        }
        button1_2.setOnClickListener {
            val s_intent = Intent(this, ThirdActivity::class.java)
            startActivityForResult(s_intent, third_act)

        }*/

        // registerForActivityResult 객체 생성
        val result_act = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            // onActivityResult의 대체 : it.resultCode / it.data
            textView.text = "${it.resultCode}에서 Activity에서 복귀\n"

            textView.append("\n" + it.data?.getStringExtra(Activity.RESULT_CANCELED.toString()))
        }


        button1_1.setOnClickListener {
            val s_intent = Intent(this@MainActivity, SecondActivity::class.java)
            // 해당 Activity에 key와 value를 전송 가능
            s_intent.putExtra("2_1", "Welcome SecondActivity")
            // Result 객체의 launch 메서드로 Activity 실행
            result_act.launch(s_intent)
        }
        button1_2.setOnClickListener {
            val s_intent = Intent(this, ThirdActivity::class.java)
            s_intent.putExtra("3_1", "Question : my hobby")
            result_act.launch(s_intent)
        }
    }
    
   /* onActivityResult의 객체
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // resultCode : 해당 Activity에서 작업의 결과를 처리하고자 할 때
        super.onActivityResult(requestCode, resultCode, data)

        textView.text = "${requestCode}에서 Activity에서 복귀\n"
        when(requestCode){
            second_act -> textView.append("두번째")
            200 ->{
                textView.append("세번째")
                when(resultCode){
                    Activity.RESULT_CANCELED -> textView.append("\n결과 : 취소")
                    Activity.RESULT_OK -> textView.append("\n결과 : 정답")
                    Activity.RESULT_FIRST_USER -> textView.append("\n결과 : 오답1")
                    Activity.RESULT_FIRST_USER+1 -> textView.append("\n결과 : 오답1")
                }
            }
        }
    }*/
}
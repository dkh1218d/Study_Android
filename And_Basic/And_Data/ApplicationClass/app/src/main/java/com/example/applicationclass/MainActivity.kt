/*  ApplicationClass : 안드로이드 애플리케이션에서 단 하나를 지정할 수 있는 객체
    이 객체는 같은 안드로이드 애플리케이션이면 어디서든 주소값을 가져 올 수 있다
    공통적으로 사용하는 데이터를 저장해 관리 할 수 있다
 */
package com.example.applicationclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // application 객체를 받아온다
        val app = application as AppClass
        app.id = "qqqq"
        app.password = "1234"

        // Activity로 돌아올 때 처리되는 register
        val regi = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            textView.text = "${app.id}"
            textView.append("\n${app.password}")
        }

        button.setOnClickListener {
            // startActivityForResult(Intent(this, SecondActivity::class.java), 0)
            regi.launch(Intent(this, SecondActivity::class.java))
        }
    }

    // registerForActivityResult 로 대체되면서 사용하지 않는다
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val app = application as AppClass
        textView.text = "${app.id}"
        textView.append("\n${app.password}")
    }*/
}
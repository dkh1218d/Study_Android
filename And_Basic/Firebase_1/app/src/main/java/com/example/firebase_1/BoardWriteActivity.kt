/*  Firebase 데이터 업로드 : RealtimeDatabase
    규칙을 직접 설정해서 보안 이슈 해결

    DataBase에 Model의 형태로 데이터 전송
 */
package com.example.firebase_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_board_write.*

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        val upload_btn = findViewById<Button>(R.id.upload_btn)
        upload_btn.setOnClickListener {
            val database = Firebase.database
            // Data를 저장할 경로
            val myRef = database.getReference("board")

            val w_text = findViewById<EditText>(R.id.writetextarea).text

            // 데이터 지속 입력 push().setValue
            myRef.push().setValue(
                // Model data class로 데이터를 삽입
                Model(w_text.toString(), "이건 내용")
            )

        }

        val cancle_btn = findViewById<Button>(R.id.cancle_btn)
        cancle_btn.setOnClickListener {

        }
    }
}
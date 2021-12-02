/*  Firebase 세팅
    app폴더에 firebase.json 파일 저장
    firebase SDK를 Project gradle에 입력 필요

    Module gradle에 plugin, dependencies 입력

    firebase auth : 추가 종속성 입력 필요


    Activity Binding
    * Module gradle에 buildFeatures 추가 - binding 인스턴스 생성
    - DataBindingUtil.setContentView로 activity 정보 호출
 */
package com.example.firebase_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebase_1.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Firebase 인스턴스 생성
    private lateinit var auth: FirebaseAuth

    // DataBinding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        // dataBinding으로 layout 정보 가져오기
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

        val clicksign = findViewById<Button>(R.id.signin)
        clicksign.setOnClickListener {
            
            // DataBinding을 통해 데이터 추출
            val email = binding.emailarea
            val pwd = binding.pwdarea

            // Login chk
            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->
                // 회원가입 성공시
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sign in Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, BoardListActivity::class.java)
                    startActivity(intent)

                // 가입 실패시
                } else {
                    Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
                }
            }
        }



        binding.logout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "${auth.currentUser?.uid} 로그인", Toast.LENGTH_SHORT)
        }


        binding.login.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailarea)
            val pwd = findViewById<EditText>(R.id.pwdarea)

            auth.signInWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "${auth.currentUser?.uid} 로그인", Toast.LENGTH_SHORT)
                        val intent = Intent(this, BoardListActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT)
                    }
                }

        }
    }

    public override fun onStart() {
        super.onStart()
        // Login state check
        val currentUser = auth.currentUser
        if(currentUser != null){

        }
    }
}
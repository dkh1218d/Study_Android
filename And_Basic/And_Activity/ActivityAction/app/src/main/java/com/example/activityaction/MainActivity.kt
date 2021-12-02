package com.example.activityaction

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val permissions = arrayOf(android.Manifest.permission.CALL_PHONE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(permissions, 0)

        val regi = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        }

        // Action View Map
        googlemap.setOnClickListener {
            val uri = Uri.parse("geo:37.243243, 131.861601") // map을 호출하기 위한 위치정보 (geo : 위도, 경도)
            val g_intent = Intent(Intent.ACTION_VIEW, uri) // ACTION_XXX : Android에서 Static final로 제공되는 변수
            regi.launch(g_intent)
        }

        // Action View browser
        browser.setOnClickListener {
            val w_intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            regi.launch(w_intent)
        }

        // Action Dial
        dial.setOnClickListener {
            val d_intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01020255653"))
            regi.launch(d_intent)
        }

        // Action Call (권한 설정 필요)
        call.setOnClickListener {
            val c_intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:01020255653"))
            regi.launch(c_intent)
        }
    }
}
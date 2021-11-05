// 허용 권한 설정
package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val perm = arrayOf( // 권한 배열
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 권한 체크
        button.setOnClickListener {
            textView.text = ""
            for(pe in perm){
                val chk = checkCallingOrSelfPermission(pe)
                // 권한 허용 = GRANTED / 불허 = DENIED
                if(chk == PackageManager.PERMISSION_GRANTED) {
                    textView.append("$pe : 허용\n")
                }else if(chk == PackageManager.PERMISSION_DENIED){
                    textView.append("$pe : 거부\n")
                }
            }
        }

        button2.setOnClickListener {
            // 거부되어 있는 권한을 사용자에게 확인 받는다
            requestPermissions(perm, 0)
        }
    }
    // 사용자에게 허용을 받으면 자동으로 호출되는 메서드
    override fun onRequestPermissionsResult(
        requestCode: Int, // 상황 구분을 위한 정수값
        permissions: Array<out String>, // 권한
        grantResults: IntArray // 허용, 거부 값
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        textView.text = ""
        for(idx in grantResults.indices) { // indices : 변수의 갯수에 맞춰 배열 생성
            if(grantResults[idx] == PackageManager.PERMISSION_GRANTED)
                textView.append("${permissions[idx]} : 허용\n")
            else if(grantResults[idx] == PackageManager.PERMISSION_DENIED)
                textView.append("${permissions[idx]} : 거부\n")
        }
    }
}

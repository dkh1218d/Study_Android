/* SystemMessage : 안드로이드 단말기에서 사건 발생 시 각 사건에 대해 정해진 메시지를 발생시키고
   그에 반응하는 BroadCastReceiver들이 동작하게 된다
   ex) 배터리 부족 메시지, 문자 도착 메시지 등
   https://developer.android.com/guide/components/broadcast-exceptions.html
   OS에 등록되어 있는 BroadCastReceiver 이름들
*/
package com.example.systemmessage

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val permission = arrayOf(
        Manifest.permission.RECEIVE_BOOT_COMPLETED,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.RECEIVE_SMS
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(permission, 0)

    }
}
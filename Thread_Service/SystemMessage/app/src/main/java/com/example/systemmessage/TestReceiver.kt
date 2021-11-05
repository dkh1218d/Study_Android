package com.example.systemmessage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast

class TestReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // BR의 이름으로 분기 / AndroidOS에서 제공하는 일부 이름은 8.0 이상 버전에서도 동작
        when(intent.action){
            // 부팅 시 호출
            "android.intent.action.BOOT_COMPLETED" -> {
                val t1 = Toast.makeText(context, "부팅 함", Toast.LENGTH_SHORT)
                t1.show()
            }

            // 문자메시지 수신시 호출
            "android.provider.Telephony.SMS_RECEIVED" -> {
                // Android에서 제공하는 코드
                if(intent.extras != null){
                    // 문자 메시지 정보 객체 추출
                    val pdu = intent.extras?.get("pdus") as Array<Any>
                    if(pdu != null){
                        // MMS 장문 , SMS 단문
                        for(str in pdu){
                            // 문자 메시지 양식 객체 추출
                            val format = intent.extras?.getString("format")
                            // 문자 메시지 객체를 생성
                            val currentSMS = SmsMessage.createFromPdu(str as ByteArray?, format)
                            val showMessage = "전화번호 : ${currentSMS.displayOriginatingAddress}\n" +
                                    "내용 : ${currentSMS.displayMessageBody}"

                            val t1 = Toast.makeText(context, showMessage, Toast.LENGTH_SHORT)
                            t1.show()
                        }
                    }
                }
            }
        }
    }
}
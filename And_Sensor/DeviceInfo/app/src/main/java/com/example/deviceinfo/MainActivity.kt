/*  DeviceInfomation : 안드로이드 8.0부터 단말기의 정보를 사용할 수 없게 변경
    권한을 요청하고 단말기 정보를 추출해야 한다

    TelephonyManager - 전화와 관련된 기능 (전화 번호, SIM 코드 등)
    Build - 안드로이드 OS와 관련된 정보
    단말기 해상도 정보
 */

package com.example.deviceinfo

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val permission = arrayOf(
        android.Manifest.permission.READ_PHONE_NUMBERS,
        android.Manifest.permission.READ_PHONE_STATE,
        android.Manifest.permission.READ_SMS
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(permission, 0)

        // 단말기
        button.setOnClickListener {
            val manager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

            // Permission Check
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS
                ) != PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS
                ) != PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED) {
                // 권한 비 허가 시 동작 (안내문구 등)

            }else{
                textView.text = "phone num : ${manager.line1Number}\n"
                textView.append("sim code : ${manager.simCountryIso}\n") // 국가 코드
                textView.append("sim operater : ${manager.simOperator}\n") // 모바일 국가코드, 네트워크 코드
                textView.append("sim operater name : ${manager.simOperatorName}\n") // 서비스 이름
                textView.append("sim state : ${manager.simState}\n")    // 심 상태
            }
        }

        // 빌드
        button2.setOnClickListener {
            textView.text = "빌드 이름 : ${Build.BOARD}\n"
            textView.append("소프트웨어 커스텀 회사 : ${Build.BRAND}\n")
            textView.append("제조사 디자인 명 : ${Build.DEVICE}\n")
            textView.append("사용자에게 표시되는 빌드 ID : ${Build.DISPLAY}\n")
            textView.append("빌드 고유 ID : ${Build.FINGERPRINT}\n")
            textView.append("Change List ID : ${Build.ID}\n")
            textView.append("제품 하드웨어 제조업체 : ${Build.MANUFACTURER}\n")
            textView.append("제품 모델명 : ${Build.MODEL}\n")
            textView.append("제품명 : ${Build.PRODUCT}\n")
            textView.append("빌드 구분 : ${Build.TAGS}\n")
            textView.append("빌드 타입 : ${Build.TYPE}\n")
            textView.append("안드로이드 버전 : ${Build.VERSION.RELEASE}\n")

        }

        // 해상도
        button3.setOnClickListener {
            textView.text = getRocation()
        }

    }

    fun getRocation() : String?{
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        var roc : String? = null

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val matrix = wm.currentWindowMetrics
            roc = "${matrix.bounds.width()} x ${matrix.bounds.height()}"
        }else{
            val display = wm.defaultDisplay
            val point = Point()
            display.getSize(point)
            roc = "${point.x} x ${point.y}"
        }
        return roc
    }
}
/*  해상도 대응 : 단말기 해상도에 해당하는 폴더에 이미지가 있을 경우 원본크기 사용
                해상도에 해당하는 폴더에 이미지가 없을 경우 인접한 해상도 폴더의 이미지를 확대, 축소해서 사용
        ldpi    ~   120dpi
        mdpi    ~   160dpi (구버전 기본 해상도)
        hdpi    ~   240dpi
        xhdpi   ~   320dpi  ┐ (최근 기본 해상도)
        xxhdpi  ~   480dpi  ┘ (제일 상용화)
        xxxhdpi ~   640dpi
    drawable 폴더의 Density 속성으로 해상도 구분

    Mipmap : 애플리케이션의 아이콘 설정(해상도와 관계 없이 설정크기)
    mdpi 48 x 48 / hdpi 72 x 72 / xhdpi 96 x 96 / xxhdpi 144 x 144 / xxxhdpi 192 x 192
 */

package com.example.resolution

import android.graphics.Point
import android.hardware.display.DisplayManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import android.view.WindowMetrics
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val display = windowManager.defaultDisplay // case Activity
        // val display = activity!!.windowManaver.defaultDisplay // case Fragment
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val width = size.x
        val height = size.y

        textView.text = "${width} x $height"*/

    }
}
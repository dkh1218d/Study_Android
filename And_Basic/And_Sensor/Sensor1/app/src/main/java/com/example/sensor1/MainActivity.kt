/*  조도 센서 : 주변 밝기
    기압 센서 : 공기압
    근접 센서 : 단말기와 물건의 거리 계산 센서
    자이로 스코프 센서 : 수평 유지
 */

package com.example.sensor1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mn = getSystemService(SENSOR_SERVICE) as SensorManager

        val listener1 = object : SensorEventListener{
            // 센서로부터 측정된 값이 변경될 때 호출
            override fun onSensorChanged(event: SensorEvent?) {
                // 센서 타입별 분기
                when(event?.sensor?.type){
                    Sensor.TYPE_LIGHT ->{
                        textView.text = "주변 밝기 : ${event.values[0]}"
                    }
                    Sensor.TYPE_PRESSURE ->{
                        textView2.text = "기압 : ${event.values[0]}"
                    }
                    Sensor.TYPE_PROXIMITY ->{
                        textView3.text = "근접 : ${event.values[0]}"
                    }
                    Sensor.TYPE_GYROSCOPE ->{
                        textView4.text = "x축 : ${event.values[0]}"
                        textView4.append("\ny축 : ${event.values[1]}")
                        textView4.append("\nz축 : ${event.values[2]}")
                    }
                }
0            }
            // 센서의 감도가 변경될 때 호출
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
        }

        button.setOnClickListener {
            // 조도 센서 객체 호출
            val sensor = mn.getDefaultSensor(Sensor.TYPE_LIGHT)
            // return boolean (리스너, 센서 객체, 딜레이 타임) - FASTEST : 최고 속도 / GAME : 게임 프레임 수 / UI : 화면 갱신 속도
            val chk = mn.registerListener(listener1, sensor, SensorManager.SENSOR_DELAY_UI)
            if(!chk) textView.text = "조도 센서 미지원"

        }

        button2.setOnClickListener {
            mn.unregisterListener(listener1)
        }

        button3.setOnClickListener {
            // 공기압 센서 호출
            val sensor = mn.getDefaultSensor(Sensor.TYPE_PRESSURE)
            val chk = mn.registerListener(listener1, sensor, SensorManager.SENSOR_DELAY_UI)
            if(!chk) textView2.text = "공기압 센서 미지원"
        }

        button4.setOnClickListener {
            mn.unregisterListener(listener1)
        }

        button5.setOnClickListener {
            // 근접 센서 호출
            val sensor = mn.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            val chk = mn.registerListener(listener1, sensor, SensorManager.SENSOR_DELAY_UI)
            if(!chk) textView3.text = "근접 센서 미지원"
        }

        button6.setOnClickListener {
            mn.unregisterListener(listener1)
        }
        button7.setOnClickListener {
            // 근접 센서 호출
            val sensor = mn.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            val chk = mn.registerListener(listener1, sensor, SensorManager.SENSOR_DELAY_UI)
            if(!chk) textView4.text = "자이로 스코프 센서 미지원"
        }

        button8.setOnClickListener {
            mn.unregisterListener(listener1)
        }
    }
}
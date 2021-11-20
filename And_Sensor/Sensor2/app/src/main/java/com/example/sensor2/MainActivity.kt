/*  가속도 센서 : 기울기
    마그네틱 필드 : 단말기 주변의 자기장값, 방위를 읽는 방향 측정용도
 */

package com.example.sensor2

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
        val listener = object : SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {
                when(event?.sensor?.type){
                    Sensor.TYPE_ACCELEROMETER ->{
                        textView.text = "x축 : ${event.values[0]}"
                        textView2.text = "y축 : ${event.values[1]}"
                        textView3.text = "z축 : ${event.values[2]}"
                    }
                    Sensor.TYPE_MAGNETIC_FIELD ->{
                        textView.text = "x축 자기장 : ${event.values[0]}"
                        textView2.text = "y축 자기장 : ${event.values[1]}"
                        textView3.text = "z축 자기장 : ${event.values[2]}"
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
        }

        button.setOnClickListener {
            mn.unregisterListener(listener)
            // 가속도 센서
            val sensor = mn.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val chk = mn.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

            if(!chk) textView.text = "가속도 센서 미지원"
        }
        button1.setOnClickListener {
            mn.unregisterListener(listener)
            // 자기장 센서
            val sensor = mn.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            val chk = mn.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

            if(!chk) textView.text = "자기장 센서 미지원"
        }
        
        button2.setOnClickListener {
            mn.unregisterListener(listener)
        }

    }
}
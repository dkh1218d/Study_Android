/*  방위 측정 : 마그네틱 필드의 첫 번째 값으로 방위 측정 - 단말기의 기울기에 따라 오차 발생
            - 가속도 센서를 이용해 기울기의 값을 계산해서 오차 수정 가능
            - 0을 자북으로 계산하여 0 ~ 359.99999 까지 계산 가능
 */
package com.example.cardinal_point

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
            // 각 센서의 값을 담을 배열
            val accValues = FloatArray(3)
            val mgValues = FloatArray(3)

            // 두 센서로부터 값을 얻어 온 적이 있는지 확인 변수
            var isGetAcc = false
            var isGetMgf = false

            override fun onSensorChanged(event: SensorEvent?) {
                when(event?.sensor?.type){
                    Sensor.TYPE_MAGNETIC_FIELD ->{
                        mgValues[0] = event?.values[0]
                        mgValues[1] = event?.values[1]
                        mgValues[2] = event?.values[2]
                        isGetMgf = true
                    }
                    Sensor.TYPE_ACCELEROMETER ->{
                        accValues[0] = event?.values[0]
                        accValues[1] = event?.values[1]
                        accValues[2] = event?.values[2]
                        isGetAcc = true
                    }
                }
                if(isGetAcc && isGetMgf){
                    // 행렬 계산용 배열
                    val R = FloatArray(9)
                    val I = FloatArray(9)

                    SensorManager.getRotationMatrix(R, I, accValues, mgValues)
                    // 계산된 결과에서 방위 값 추출
                    val values = FloatArray(3)
                    SensorManager.getOrientation(R, values) // 리턴 타입이 라디언으로 나온다

                    // 라디언 값을 각도 값으로 변경
                    var azimuth = Math.toDegrees(values[0].toDouble()).toFloat() // 방위 값
                    val pitch = Math.toDegrees(values[1].toDouble()).toFloat()
                    val roll = Math.toDegrees(values[2].toDouble()).toFloat()

                    if(azimuth < 0){
                        azimuth += 360f
                    }
                    textView.text = "방위 값 : $azimuth"
                    textView2.text = "좌우 기울기 값 : $pitch"
                    textView3.text = "좌우 기울기 값 : $roll"

                    // 나침반 이미지
                    imageView.rotation = 360 - azimuth
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
        }

        button.setOnClickListener {
            val mg_sensor = mn.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            val ac_sensor = mn.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val chk = mn.registerListener(listener, mg_sensor, SensorManager.SENSOR_DELAY_UI)
            val chk2 = mn.registerListener(listener, ac_sensor, SensorManager.SENSOR_DELAY_UI)
            if(!chk || !chk2){
                mn.unregisterListener(listener)
                textView.text = "방위 측정에 실패했습니다"
            }
        }
        button2.setOnClickListener {
            mn.unregisterListener(listener)
        }
    }
}
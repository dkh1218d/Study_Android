/*  RawData : 가공되지 않은 원천 데이터 ex) 카메라로 찍은 사진, 영상, 녹음 등을 가공하지 않은 원본
    안드로이드에서는 이미지나 xml을 제외한 모든 파일을 rawData라고 정의
    이미지, xml은 res폴더에 넣기만 해도 사용이 가능하지만 그 외의 데이터는 가공을 해야하기 때문에
    나머지의 모든 파일은 raw데이터로 정의
    안드로이드 애플리케이션 내부에 접속해야 하기 때문에 '읽기 전용'

    res내부에 raw폴더를 생성해서 ResourceID를 통해 쉽게 스트림을 추출 가능

    Stream : 애플리케이션과 외부의 파일, 네트워크 등의 요소들과 데이터를 주고 받기위해 사용하는 객체
 */
package com.example.rawdata

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    // Media Player 관리 객체
    var mp : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // Stream 추출 / res폴더안의 파일이나 데이터를 관리하는 resources 객체
            val inputStream = resources.openRawResource(R.raw.data1)
            // File로부터 데이터를 읽어온 문자열을 decoding을 통해 원본 글자로 변환해준다
            val isr = InputStreamReader(inputStream,"UTF-8")
            // Line단위로 읽는 Stream
            val br = BufferedReader(isr)

            // Buffer에서 한줄씩 읽어와 담는 변수
            var str:String? = null
            val sb = StringBuffer()

            do{
                str = br.readLine()
                if(str!=null){
                    sb.append("$str\n")
                }
            }while (str!=null)

            textView.text = sb.toString()
        }

        button2.setOnClickListener {
            mp = MediaPlayer.create(this, R.raw.yasumi)
            mp?.start()

        }

        button3.setOnClickListener {
            if(mp!=null) {
                mp?.stop()
            }
        }

        button4.setOnClickListener {
            // 재생 여부 확인 property
            if(videoView.isPlaying==false){
                // 영상 파일 경로
                val uri = Uri.parse("android.resource://${packageName}/raw/yu")
                videoView.setVideoURI(uri)
                videoView.start()
            }
        }

        button5.setOnClickListener {
            if(videoView.isPlaying==true){
                videoView.stopPlayback()
            }
        }
    }
}
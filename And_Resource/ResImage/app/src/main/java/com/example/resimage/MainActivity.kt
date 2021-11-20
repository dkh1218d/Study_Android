/*  ImageResource : xml을 통해 이미지를 새롭게 구성 가능
    겹치기 / 이어붙이기/ 패턴 / 상태이미지 등
    drawable : 애플리케이션 내 이미지 ( 해상도 별 이미지 분기 구분 )
    mipmap : 아이콘 이미지 ( 애플리케이션 아이콘 이미지 - 사이즈 설정용 )

    이미지 타입 클래스
    Bitmap : jpg, png, gif 파일에서 읽어온 이미지 데이터를 관리하는 객체
    Drawable : Bitmap을 포함해서 통칭. xml 파일을 통해 만든 이미지, 코드를 포함해 만든 이미지, 서버를 통해 받은 이미지 등
               안드로이드에서 화면이 그릴 수 있는 모든 그림 요소를 관리하는 객체

    theme를 Theme.AppCompat.Light로 변경 필요(Bug)
 */
package com.example.resimage

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // decodeStream 네트워크에서 받아온 이미지 객체
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
            hearder.setImageBitmap(bitmap)

            val drawable = getDrawable(R.drawable.test2)
            imageView.setImageDrawable(drawable)

            // Bitmap 타일
            // container.setBackgroundResource(R.drawable.tile)
            // Drawable 타일 (jpt, png, gif 이외의 이미지 포함) ex) 네트워크 이미지
            val drawabletile = getDrawable(R.drawable.tile)
            container.background = drawabletile

            val drawable_btn = getDrawable(R.drawable.status)
            button2.background = drawable_btn
        }

        button2.setOnClickListener {
            // layer 이미지 사용
            // 이미지가 1번부터 겹쳐서 위에 쌓인다
            val drawable_layer = getDrawable(R.drawable.layer)
            imageView.setImageDrawable(drawable_layer)
        }
    }
}
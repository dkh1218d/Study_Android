package com.example.getalbum

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val permission = arrayOf(Manifest.permission.ACCESS_MEDIA_LOCATION,
    Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions(permission, 0)

        val regi = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                // 선택한 이미지의 경로 데이터를 관리하는 uri 객체 추출
                val uri = it.data?.data

                if(uri != null){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        // 음원이나 이미지 등 파일을 넣으면 안드로이드 OS가 외부저장소의 파일 정보들을 읽어서 SQLite에 저장
                        // 저장된 Media 파일 데이터를 contentProvider로 읽어와서 uri 객체를 통해 이미지 객체를 만들어 반환
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        imageView.setImageBitmap(bitmap)
                    }else{
                        val cursor = contentResolver.query(uri, null, null, null, null)
                        if(cursor != null){
                            cursor.moveToNext()
                            // 이미지 경로 추출
                            val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                            val source = cursor.getString(index)
                            val bitmap = BitmapFactory.decodeFile(source)
                            imageView.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }

        button.setOnClickListener {
            // 앨범에서 사진을 선택할 수 있는 Activity를 실행
            val a_intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // 실행할 Activity의 타입을 설정 (이미지만 선택)
            a_intent.type = "image/*"
            // 선택할 File의 타입을 설정 (안드로이드 OS가 사전작업을 할 수 있도록)
            val mimeType = arrayOf("image/*")
            a_intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)

            regi.launch(a_intent)
        }
    }
}
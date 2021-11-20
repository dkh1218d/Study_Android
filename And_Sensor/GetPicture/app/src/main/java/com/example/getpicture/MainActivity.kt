package com.example.getpicture

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    // 사진이 저장될 위치를 관리하는 uri객체
    lateinit var uri:Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 외부 저장소의 데이터 경로
        val filepath = getExternalFilesDir(null).toString()

        val regi1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode== RESULT_OK){
                // data 안의 촬영한 사진의 정보 추출
                val bitmap = it.data?.getParcelableExtra<Bitmap>("data")
                // 썸네일 이미지 크기
                imageView.setImageBitmap(bitmap)
            }
        }

        numbertext.setText("${seekBar.progress}")
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {} override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                numbertext.setText("$progress")
            }
        })

        val regi2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){

                // 사이즈 비율 조정
                val bitmap = resize_bitmap(numbertext.text.toString().toInt(), BitmapFactory.decodeFile(uri.path))

                // 각도 계산
                val degree = getDegree(uri, uri.path!!)

                // 이미지 회전
                val rotebit = rotateBitmap(bitmap, degree)

                imageView2.setImageBitmap(rotebit)
            }
        }

        button.setOnClickListener {
            val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            regi1.launch(intent1)
        }

        // 촬영한 사진을 저장 한 후 원본 이미지 데이터를 받아온다
        button2.setOnClickListener {
            val intent2 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // 촬영한 사진이 저장 될 파일 이름
            val filename = "/temp_${System.currentTimeMillis()}.jpg"
            // 경로 + 파일이름
            val picfilepath = "$filepath/$filename"
            val file = File(picfilepath)

            // Provider를 통해 저장한 사진의 이용 허가와 uri를 받는다
            uri = FileProvider.getUriForFile(this, "com.example.picture.file_provider", file)

            if(uri != null){
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                regi2.launch(intent2)
            }
        }
    }

    // 이미지의 사이즈 조정 메서드
    fun resize_bitmap(targetWidth : Int, bitmap : Bitmap) : Bitmap{
        // 이미지 비율 계산 ( 원하는 사이즈의 가로 길이 / 원본의 가로 길이 )
        val ratio = targetWidth.toDouble() / bitmap.width.toDouble()
        // 보정될 세로 길이
        val targetHeight = (bitmap.height * ratio).toInt()
        // 크기를 조정한 Bitmap 객체 생성
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false)
    }

    // 이미지의 화면 각도 조정 메서드
    fun getDegree(uri : Uri, source : String) : Float{
        // 이미지의 정보를 분석해서 가지고 있는 객체
        var exif : ExifInterface? = null
        // 안드로이드 10버전 이상 (Q)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val photoUri = MediaStore.setRequireOriginal(uri)
            val stream = contentResolver.openInputStream(photoUri)
            // 10버전 이상부터 uri를 stream으로 받아서 exif객체에 넘겨줘야 한다
            exif = ExifInterface(stream!!)
        }else{
            exif = ExifInterface(source)
        }

        var degree = 0
        // tag_orientation : 이미지의 회전 각도 값 / 회전값이 없을 시의 default값
        val ori = exif?.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
        when(ori){
            ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
            ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
            ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
        }
        return degree.toFloat()
    }

    // 이미지 회전 메서드
    fun rotateBitmap(basic_bitmap : Bitmap, degree : Float) : Bitmap{
        // 각도값을 관리하는 객체(android.graphics)
        val matrix = Matrix()
        matrix.postRotate(degree)
        // 원본 이미지의 x, y축 거리(좌측 상단 기준), 가로, 세로 길이로 재 생성
        val bitmap = Bitmap.createBitmap(basic_bitmap, 0, 0, basic_bitmap.width, basic_bitmap.height, matrix, true)
        return bitmap
    }
}
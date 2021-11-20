/*  Android 저장소(내부, 외부)
    외부 저장소 - 안드로이드 11이상부터 보안이슈로 외부저장소의 자유로운 접근이 불가 : Scoped Storage
    Scoped Storage
    - 앱 데이터 폴더 : 읽고 쓰는데 권한이 필요 없으며, 해당 애플리케이션만 접근 가능
    - 미디어 파일 폴더 : 사진, 동영상, 음원등 저장 장소
    - 공용 파일 폴더 : Download폴더, 이 폴더에 저장된 파일은 모든 애플리케이션 접근 가능
                     단, 코드를 통한 직접접근은 불가능하고 단말기에 설치된 파일 관리 어플을 통해서만 접근가능
                     -> 파일 관리 어플을 실행해 사용자가 직접 파일을 선택해야 가능하다
 */
package com.example.filedata

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    // 외부 저장소의 앱 데이터 디렉토리의 경로
    lateinit var file_path : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getExternalFilesDir type을 null로 하면 앱 데이터 폴더의 경로
        // Environment.DIRECTORY_??? 로 각 디렉토리의 경로 설정 가능
        file_path = getExternalFilesDir(null).toString()
        Log.d("test", file_path)

        // 내부 저장소 저장(용량이 적고 외부유출X, 애플리케이션 삭제시 같이 삭제)
        button.setOnClickListener {
            // 파일 명, 모드 선택
            // MODE_PRIVATE : 덮어쓰기 / MODE_APPEND : 이어서 쓰기
            val fos = openFileOutput("data1.dat", Context.MODE_PRIVATE)
            val dos = DataOutputStream(fos)

            // 데이터 입력(순차적 저장)
            dos.writeInt(100)
            dos.writeBoolean(true)
            dos.writeUTF("1번 문자열")

            dos.flush()
            dos.close()

            textView.text = "내부저장소 쓰기 완료"
        }

        // 내부 저장소 읽기
        button2.setOnClickListener {
            val fis = openFileInput("data1.dat")
            val dis = DataInputStream(fis)

            // 저장한 순서에 맞게 읽어와야 한다
            val data1 = dis.readInt()
            val data2 = dis.readBoolean()
            val data3 = dis.readUTF()

            dis.close()

            textView.text = "data1 : ${data1}"
            textView.append("\ndata2 : ${data2}")
            textView.append("\ndata3 : ${data3}")

        }

        // 앱 데이터 경로 저장 (내부 저장소와 같이 애플리케이션 삭제시 삭제, 내부저장소에 비해 대용량)
        button3.setOnClickListener {
            val fos = FileOutputStream("$file_path/data2.dat")
            val dos = DataOutputStream(fos)

            dos.writeInt(124)
            dos.writeBoolean(false)
            dos.writeUTF("2번째 문자열")

            dos.flush()
            dos.close()

            textView.text = "외부 저장소의 앱 데이터 폴더 저장"
        }

        //
        button4.setOnClickListener {
            val fis = FileInputStream("$file_path/data2.dat")
            val dis = DataInputStream(fis)

            textView.text = "${dis.readInt()}"
            textView.append("\n${dis.readBoolean()}")
            textView.append("\n${dis.readUTF()}")

            dis.close()
        }

        // Download 파일 저장 Register
        val regi = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                // File의 경로를 관리하는 객체를 얻어온다 / 쓰기모드 "w"
                val des1 =contentResolver.openFileDescriptor(it.data?.data!!,"w")
                // contentResolver로 만든 fileDescriptor로 해당 File에 접근 할 수 있는 Stream을 생성
                val fos = FileOutputStream(des1?.fileDescriptor)
                val dos = DataOutputStream(fos)
                dos.writeInt(555)
                dos.writeDouble(1.23)
                dos.writeUTF("3번째 문자열")

                dos.flush()
                dos.close()

                textView.text = "Download 폴더에 저장"
            }
        }

        // 공용 파일(Download 폴더) : 경로를 직접 지정 x / File관리 App을 통해서만 관리 가능
        button5.setOnClickListener {
            // File 관리 App의 Activity 실행
            val fileIntent = Intent(Intent.ACTION_CREATE_DOCUMENT)  // File을 만들어서 쓰기 위한 용도의 File관리 Activity
            fileIntent.addCategory(Intent.CATEGORY_OPENABLE)        // 파일 열기 위한 셋팅
            // mime type 지정 - 미지정시 오류
            fileIntent.type = "*/*" // 모든 type
            // fileIntent.type = "image/*" // 이미지 type

            regi.launch(fileIntent)
        }

        // Download 파일 읽기 Register
        val regi2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                // 읽기모드 "r"
                val des2 = contentResolver.openFileDescriptor(it.data?.data!!, "r")
                val fis = FileInputStream(des2?.fileDescriptor)
                val dis = DataInputStream(fis)

                textView.text = "${dis.readInt()}"
                textView.append("\n${dis.readDouble()}")
                textView.append("\n${dis.readUTF()}")

                dis.close()
            }
        }

        // Download 폴더의 file 읽기
        button6.setOnClickListener {
            // File 관리 App의 Activity 실행
            val fileIntent = Intent(Intent.ACTION_OPEN_DOCUMENT) // File을 읽기 위한 용도의 File관리 Activity
            fileIntent.type = "*/*"
            regi2.launch(fileIntent)

        }
    }
}
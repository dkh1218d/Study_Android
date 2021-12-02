/*  IntelliJ 의 SocketServer와 연결해서 소켓 통신
    Internet 관련 권한 등록 필요
    쓰레드로 운영해야 한다
*/

package com.example.socketclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var socket = Socket()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("test", "테스트 : ${socket}")

        button1.setOnClickListener {
            thread {
                // 서버 접속
                socket = Socket("192.168.0.8", 55555)
                Log.d("test", "Socket : $socket")

                val input = socket.getInputStream()
                val dis = DataInputStream(input)

                val data1 = dis.readInt()
                val data2 = dis.readDouble()
                val data3 = dis.readBoolean()
                val data4 = dis.readUTF()

                // Thread에서 출력
                runOnUiThread {
                    textView.text = "int : $data1\n"
                    textView.append("double : $data2\n")
                    textView.append("bool : $data3\n")
                    textView.append("text : $data4")
                }

                val output = socket.getOutputStream()
                val dos = DataOutputStream(output)
                dos.writeInt(data1+100)
                dos.writeDouble(data2+0.999)
                dos.writeBoolean(false)
                dos.writeUTF(data4+"아니야")

            }
        }
        button2.setOnClickListener {
            socket.close()
            Log.d("test", "종료 : ${socket}")
        }


        button.setOnClickListener {

        }
    }
}
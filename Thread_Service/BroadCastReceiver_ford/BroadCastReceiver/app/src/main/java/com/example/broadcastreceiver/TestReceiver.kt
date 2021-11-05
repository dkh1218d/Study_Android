// Manifest.xml에서 IntentFilter 등록 필요
package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TestReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val str = "BroadCastReceiver가 동작하였습니다"
        // BroadCastReceiver는 Context를 상속받지 않기 때문에 직접 할당 필요
        val toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
        toast.show()
    }
}
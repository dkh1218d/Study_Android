/*  Provider : 안드로이드는 위치측정을 위해 위치정보 제공자를 선택해서 사용
    - GPS Provider : GPS위성과 통신하여 3각 측량방법을 이용해 위치정보 습득(위성 통신전파 방해 요인이 있을 시 오차율 증가 - 방해전파, 실내, 지하 등)
    - Network Provider : 이동통신 기지국, Wifi ap 등 통신망을 이용해 위치정보 습득
    - Passive Provider : 직접 측정이 아닌 다른 애플리케이션이 구한 위치정보를 받아 사용

    권한 등록 필요
    GPS = ACCESS_FINE_LOCATION
    Network = ACCESS_FINE_LOCATION 또는 ACCESS_COARSE_LOCATION
    Passive = ACCESS_FINE_LOCATION
 */
package com.example.gps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val permission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(permission, 0)

    }

    // 권한 확인 후 위치정보를 불러오기 위한 메서드
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(i in grantResults){
            if(i == PackageManager.PERMISSION_DENIED)return
        }
        // 위치 정보를 관리하는 매니저 호출
        val mn = getSystemService(LOCATION_SERVICE) as LocationManager


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        // 이전의 저장되어 있는 위치정보 호출
        val loca1 = mn.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val loca2 = mn.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if(loca1 != null) showinfo(loca1)
        else if(loca2 != null) showinfo(loca2)

        // lambda로 제공되는 리스너
        val listener = LocationListener {
            showinfo(it)
        }

        button.setOnClickListener {
            // 두개의 Provider를 같이 가동시킨다
            if(mn.isProviderEnabled(LocationManager.GPS_PROVIDER))
                // 설정 된 주기마다 위치정보 갱신
                mn.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
            if(mn.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                mn.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, listener)
        }

        button3.setOnClickListener {
            mn.removeUpdates(listener)
        }
    }

    fun showinfo(location : Location){
        if(location != null){
            textView.text = "Provider : ${location.provider}" // provider
            textView2.text = "위도 : ${location.latitude}" // 위도
            textView3.text = "경도 : ${location.longitude}" // 경도
        }
    }
}
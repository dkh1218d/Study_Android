package com.example.parcelable

import android.os.Parcel
import android.os.Parcelable

// 객체 직렬화 (Add Parcelable Implementasion)
class TestClass() : Parcelable{
    var data1:Int = 0
    var data2:String? = "" // null 허용하는 변수로 초기화

    // Parcelable 구현 필드

    constructor(parcel: Parcel) : this() {
        data1 = parcel.readInt()
        data2 = parcel.readString() // 데이터가 없으면 null값
    }

    // putExtra를 이용해 객체를 Intent에 담는 작업을 할 경우 호출되는 메서드
    // 객체가 Intent에 저장되는 것이 아닌 parcel로 추출한 value가 저장된다
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(data1)
        parcel.writeString(data2)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Static 객체
    companion object CREATOR : Parcelable.Creator<TestClass> {
        // getParcelableExtra를 호출해서 객체를 추출하려고 하면 자동으로 호출되는 메서드
        // 새로운 객체를 생성하고 parcel에 저장되어 있는데이터를 추출해 객체의 변수에 담고 그 후 생성된 객체를 반환
        override fun createFromParcel(parcel: Parcel): TestClass {
            return TestClass(parcel)
        }

        override fun newArray(size: Int): Array<TestClass?> {
            return arrayOfNulls(size)
        }
    }
}
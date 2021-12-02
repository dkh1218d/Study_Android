package com.example.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class TestProvider : ContentProvider() {
    var db : SQLiteDatabase? = null

    // 열의 데이터 타입을 MIME 타입 형태로 반환하는 메서드
    // 알려줄 필요가 없으면 null값 반환
    override fun getType(uri: Uri): String? {
        return null
    }

    // Content Provider 객체가 생성되면 자동으로 호출되는 메서드
    // DB에 접근할 수 있는 객체를 생성하고 성공하면 true, 실패하면 false를 반환
    override fun onCreate(): Boolean {
        // context를 상속받고 있지 않기 떄문에 context property로 구해와야 한다
        db = DBHelper(context!!).writableDatabase

        if(db == null) return false
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // ContentValues 객체로 데이터를 받아 처리
        db?.insert("testTable", null, values)
        return uri
    }

    // Select 구문
    // uri : provider 요청시 사용한 uri값
    // projection : 가져올 column 이름 목록, null이면 모든 column 반환
    // selection : 조건절, null이면 조건이 없다
    // selectionArgs : 조건문 Binding Values
    // sortOrder : 정렬 기준이 되는 column
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        // groupBy, having 은 매개변수로 받지 않는다
        val c1 = db?.query("testTable", projection, selection, selectionArgs, null, null, sortOrder)
        return c1
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val cnt = db?.update("testTable", values, selection, selectionArgs)
        return cnt!!
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val cnt = db?.delete("testTable", selection, selectionArgs)
        return cnt!!
    }
}
package com.example.sqlitedb1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper:SQLiteOpenHelper {
    // context를 받아 부모로 넘기고 DB파일의 이름을 설정해 있으면 오픈, 없으면 생성하게 된다
    constructor(context: Context) : super(context, "Test.db", null, 1) // version을 통해 DB 갱신을 구분

    // 애플리케이션 설치 후 최초로 접근 시 호출
    // 최신 형태의 테이블을 생성하는 쿼리문을 작성
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("test", "db생성 : ")

        val sql = """
                    create Table TestTable
                        (idx integer primary key autoincrement,
                         textData text not null default "lenon",
                         intData integer not null,
                         floatData real not null,
                         dateData date not null)
                  """.trimIndent() // 정수형 integer / 문자열 text / 실수형 real / 날짜형 date
        db?.execSQL(sql) // sql 실행
    }

    // 버전이 변경되는 경우 호출
    // 기존의 사용자를 최신 형태의 테이블로 변경하는 쿼리문을 작성
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("test", "db갱신 : $oldVersion -> $newVersion")
        when(oldVersion){ // SQLite는 Modify가 없다
            1 -> {} // oldVersion이 1인 경우
            2 -> {} // oldVersion이 2인 경우
        }
    }
}
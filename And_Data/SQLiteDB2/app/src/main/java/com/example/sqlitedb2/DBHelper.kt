package com.example.sqlitedb2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper:SQLiteOpenHelper {
    // 애플리케이션 별로 DB를 관리하기 때문에 DB이름이 다른 애플리케이션과 중복가능
    constructor(context: Context) : super(context, "Test.db", null, 1)

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
                    Create Table TestTable
                        (idx integer primary key autoincrement,
                         name text not null,
                         num integer not null default 100)
                  """.trimIndent()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
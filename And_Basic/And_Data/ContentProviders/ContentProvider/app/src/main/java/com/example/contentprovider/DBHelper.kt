package com.example.contentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper:SQLiteOpenHelper {

    constructor(context: Context) : super(context, "Test.db", null, 1)

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
                        create table testTable
                            (idx integer primary key autoincrement,
                            textData text not null,
                            intData not null default 100,
                            floatData real not null,
                            dateData date not null)                             
                    """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
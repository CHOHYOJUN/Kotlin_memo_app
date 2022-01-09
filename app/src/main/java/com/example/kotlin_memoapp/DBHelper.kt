package com.example.kotlin_memoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper {

    // 생성자 (데이터베이스 이름을 정해준다.)
    constructor(context: Context) : super(context, "Memo.db", null, 1)

    // 데이터 베이스 파일이 생성될 때 자동 호출되는 메서드
    // 테이블 생성 코드를 작성해준다.

    override fun onCreate(p0: SQLiteDatabase?) {
//        TODO("Not yet implemented")

        // 쿼리문
        var sql = """
            create table MomoTable
            (
                memo_idx integer primary key autoincrement,
                memo_subject text not null,
                memo_text text not null,
                memo_date date not null 
            )
        """.trimIndent()

        p0?.execSQL(sql)

    }

    // 사용 안하기때문에 기본 설정값 유지
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}
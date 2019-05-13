package com.test.mysamplepush

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SqlLiteSampleActivity : AppCompatActivity() {

    val dbName = "DBName"
    val tableName = "person"
    val tableName3 = "person22222222222222222"


    private lateinit var sampleDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sampleDB = this.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null)

        sampleDB.execSQL(
            "CREATE TABLE IF NOT EXISTS" + tableName +
                    "(name VARCHAR(20), phone VARCHAR(20) );"
        )

        sampleDB.execSQL(
            "DELETE FROM"
                    + tableName
        )

    }


}
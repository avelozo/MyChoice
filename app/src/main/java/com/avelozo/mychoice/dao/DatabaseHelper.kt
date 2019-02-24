package com.avelozo.mychoice.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

private  val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${DatabaseContract.CategoryEntry.CATEGORY_TABLE_NAME} (" +
            "${DatabaseContract.CategoryEntry.COLUMN_NAME_CATEGORY_NAME} TEXT," +
            "${DatabaseContract.CategoryEntry.COLUMN_NAME_CATEGORY_URL} TEXT,"+
            "${DatabaseContract.CategoryEntry.COLUMN_NAME_CATEGORY_VISITS} INTEGER)"

private  val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DatabaseContract.CategoryEntry.CATEGORY_TABLE_NAME}"

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyChoice.db"
    }
}





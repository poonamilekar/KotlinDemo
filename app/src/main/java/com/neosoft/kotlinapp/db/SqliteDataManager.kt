package com.neosoft.kotlinapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Poonam Ilekar on 29/12/17.
 */
class SqliteDataManager(context: Context,name:String,cursorFactory:SQLiteDatabase.CursorFactory,version:Int ) : SQLiteOpenHelper(context,name,cursorFactory,version){
    val SQLITE_VERSION=1
    val DATABASE_NAME="mydatabase.db"


    /*constructor(context: Context,name:String,cursorFactory:SQLiteDatabase.CursorFactory,version:Int ) : super(context,name,cursorFactory,version){

    }*/

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
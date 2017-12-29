package com.neosoft.kotlinapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.neosoft.kotlinapp.data.AnnotationData
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Poonam Ilekar on 29/12/17.
 */
class SqliteDataManager(context: Context,name:String,cursorFactory:SQLiteDatabase.CursorFactory?,version:Int ) : SQLiteOpenHelper(context,name,cursorFactory,version){

    val TAG :String="SqliteDataManager"

    lateinit var db :SQLiteDatabase
    val TABLE_ANNOTATION="Annotation"
    val ID="id"
    val USER_ID="user_id"
    val ALERT_DESCRIPTION="alert_description"
    val ALERT_TILTE="alert_title"
    val CATEGORY_TYPE_ID="category_type_id"
    val SUB_CATEGORY_ID="sub_category_id"
    val LATITUDE="latitude"
    val LONGITUDE="longitude"
    val STATUS="status"
    val SUB_CATEGORY_IMAGE ="sub_category_image"
    val CREATED_USER_LEVEL="created_user_level"
    val UPDATED_BY="updated_by"
    val CREATED_USER_ID="created_user_id"
    val CREATED_AT="created_at"
    val CREATE_TABLE_ANNOTATION :String="CREATE TABLE "+TABLE_ANNOTATION+"("+ID+" integer PRIMARY KEY , "
            .plus(USER_ID+" integer , ")
            .plus(ALERT_DESCRIPTION+" text ,")
            .plus(ALERT_TILTE+" text , ")
            .plus(CATEGORY_TYPE_ID+" text , ")
            .plus(SUB_CATEGORY_ID+" text , ")
            .plus(LATITUDE+" text , ")
            .plus(LONGITUDE+" text , ")
            .plus(STATUS+" text , ")
            .plus(SUB_CATEGORY_IMAGE +" text , ")
            .plus(CREATED_USER_LEVEL+" text , ")
            .plus(UPDATED_BY+" text , ")
            .plus(CREATED_USER_ID+" text , ")
            .plus(CREATED_AT+" text ")
            .plus(")")
    /*constructor(context: Context,name:String,cursorFactory:SQLiteDatabase.CursorFactory,version:Int ) : super(context,name,cursorFactory,version){

    }*/

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE_ANNOTATION)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //db!!.execSQL("DROP TABLE "+TABLE_ANNOTATION)
    }

    fun insertAnnotionData(arrayList: ArrayList<AnnotationData>){
        db=writableDatabase
        Log.e(TAG,"insert Annotation "+Date())
        for(item:AnnotationData in arrayList){
            var contentValue:ContentValues=ContentValues()
            contentValue.put(USER_ID,item.userId)
            contentValue.put(ID,item.id)
            contentValue.put(ALERT_DESCRIPTION,item.alertDescription)
            contentValue.put(ALERT_TILTE,item.alertTitle)
            contentValue.put(CATEGORY_TYPE_ID,item.categoryTypeId)
            contentValue.put(SUB_CATEGORY_ID,item.subCategoryId)
            contentValue.put(LATITUDE,item.latitude)
            contentValue.put(LONGITUDE,item.longitude)
            contentValue.put(SUB_CATEGORY_IMAGE,item.subCategoryImage)
            contentValue.put(CREATED_USER_LEVEL,item.createdUserLevel)
            contentValue.put(UPDATED_BY,item.updatedBy)
            contentValue.put(CREATED_USER_ID,item.createdUserId)
            contentValue.put(CREATED_AT,item.createdAt)
            db!!.insert(TABLE_ANNOTATION,null,contentValue)
        }
        db.close()
        Log.e(TAG,"insert Annotation done"+Date())
        Log.e(TAG,"list Size :"+getAnnotationData()?.size)
    }

    fun bulkInsertAnnotionData(arrayList: ArrayList<AnnotationData>){
        db=writableDatabase

        try {
            Log.e(TAG,"insert Annotation begin :"+Date())
            db.beginTransaction()
            for (item: AnnotationData in arrayList) {
                var contentValue: ContentValues = ContentValues()
                contentValue.put(USER_ID, item.userId)
                contentValue.put(ID, item.id)
                contentValue.put(ALERT_DESCRIPTION, item.alertDescription)
                contentValue.put(ALERT_TILTE, item.alertTitle)
                contentValue.put(CATEGORY_TYPE_ID, item.categoryTypeId)
                contentValue.put(SUB_CATEGORY_ID, item.subCategoryId)
                contentValue.put(LATITUDE, item.latitude)
                contentValue.put(LONGITUDE, item.longitude)
                contentValue.put(SUB_CATEGORY_IMAGE, item.subCategoryImage)
                contentValue.put(CREATED_USER_LEVEL, item.createdUserLevel)
                contentValue.put(UPDATED_BY, item.updatedBy)
                contentValue.put(CREATED_USER_ID, item.createdUserId)
                contentValue.put(CREATED_AT, item.createdAt)
                db!!.insert(TABLE_ANNOTATION, null, contentValue)
            }
            db.setTransactionSuccessful()
        }catch (e1:Exception)
        {
            e1.printStackTrace()
        }finally {
            db.endTransaction()
            db.close()
        }
        Log.e(TAG,"insert Annotation end :"+Date())
        Log.e(TAG,"list Size :"+getAnnotationData()?.size)
    }

    fun getAnnotationData():ArrayList<AnnotationData>?{
        db=readableDatabase
        var list=ArrayList<AnnotationData>()
        var cursor:Cursor=db.rawQuery("SELECT * FROM ".plus(TABLE_ANNOTATION),null)
        if(cursor != null){
            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                var annotationData=AnnotationData()

                annotationData.id=cursor.getInt(cursor.getColumnIndex(ID))
                annotationData.userId=cursor.getInt(cursor.getColumnIndex(USER_ID))
                annotationData.alertDescription=cursor.getString(cursor.getColumnIndex(ALERT_DESCRIPTION))
                annotationData.alertTitle=cursor.getString(cursor.getColumnIndex(ALERT_TILTE))
                annotationData.categoryTypeId=cursor.getString(cursor.getColumnIndex(CATEGORY_TYPE_ID))
                annotationData.subCategoryId=cursor.getString(cursor.getColumnIndex(SUB_CATEGORY_ID))
                annotationData.latitude=cursor.getString(cursor.getColumnIndex(LATITUDE))
                annotationData.subCategoryImage=cursor.getString(cursor.getColumnIndex(SUB_CATEGORY_IMAGE))
                annotationData.createdUserLevel=cursor.getInt(cursor.getColumnIndex(CREATED_USER_LEVEL))
                annotationData.updatedBy=cursor.getInt(cursor.getColumnIndex(UPDATED_BY))
                annotationData.createdUserId=cursor.getInt(cursor.getColumnIndex(CREATED_USER_ID))
                annotationData.createdAt=cursor.getString(cursor.getColumnIndex(CREATED_AT))

                list.add(annotationData)

                cursor.moveToNext()
            }
            cursor.close()
        }
        db.close()
        return list
        //var cursor:Cursor=db.query(TABLE_ANNOTATION, String[] columns,null, null,null,null, null)
    }

    fun updateAnnotationDate(id:Int,title:String){
        db=writableDatabase
        var contentValue:ContentValues=ContentValues()
        contentValue.put(ALERT_TILTE,title)

        db.update(TABLE_ANNOTATION,contentValue,ID.plus(" = ?"), arrayOf(id.toString()))
        db.close()
    }

    fun deleteAnnoationData(id:Int){
        db=writableDatabase
        db.delete(TABLE_ANNOTATION,ID.plus(" = ?"), arrayOf(id.toString()))
        db.close()
    }

    fun deleteAllRecords(){
        db=writableDatabase
        db.delete(TABLE_ANNOTATION,null,null)
        db.close()
        Log.e(TAG,"after delete list Size :"+getAnnotationData()?.size)
    }

}
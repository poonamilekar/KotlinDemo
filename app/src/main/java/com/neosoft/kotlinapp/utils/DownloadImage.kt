package com.neosoft.kotlinapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by Poonam Ilekar on 28/12/17.
 */
class DownloadImage :AsyncTask<Void,Void,Void>(){

    var imageUrl="http://go.saesa.cl:81/public/upload/sub_category/PJb80OA72YyA1C_4.png"
    //val BUFFER_SIZE = 1024//4096
    override fun onPreExecute() {
        super.onPreExecute()
        System.out.println("OnPreExecute")
    }

    override fun doInBackground(vararg p0: Void?): Void? {
        System.out.println("doInBackground")
        var fileOpStream : FileOutputStream? =null
        var conn :HttpURLConnection?=null
        val url = URL(imageUrl)
        try {

            conn = url.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.connect()
            var ipStrem : InputStream = conn.inputStream

            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.RGB_565
            var bmImg :Bitmap = BitmapFactory.decodeStream(ipStrem, null, options)

            var file : File = File(Environment.getExternalStorageDirectory().absolutePath,"xyzImage.jpg")
            fileOpStream= FileOutputStream(file)
            bmImg.compress(Bitmap.CompressFormat.JPEG,100,fileOpStream)

        } catch (e: IOException) {
            e.printStackTrace()
        }catch (e1: Exception){
            e1.printStackTrace()
        }finally {
            if(fileOpStream != null){
                fileOpStream.flush()
                fileOpStream.close()
            }
            if(conn != null){
                conn.disconnect()
            }
        }
     return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        System.out.println("File Download complete")
    }

    override fun onCancelled(result: Void?) {
        super.onCancelled(result)
        System.out.println("OnCancelled")
    }
}
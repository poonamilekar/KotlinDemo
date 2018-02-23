package com.neosoft.kotlinapp.app

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.neosoft.kotlinapp.retro.APIRetro
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Poonam Ilekar on 2/1/18.
 */
class MyApp:Application() {

    val BASE_URL="http://go.saesa.cl:81/public/index.php/"
    val API_KEY="basic_token"
    val USER_NAME="abcd"
    val PASSWORD="123456"

    lateinit var gson:Gson
    lateinit var retrofitClient:Retrofit
    lateinit var apiRetroService:APIRetro

    lateinit var sharedPref:SharedPreferences
    lateinit var editor:SharedPreferences.Editor

    companion object {
        lateinit var appInstance:MyApp
            private set


    }
    override fun onCreate() {
        super.onCreate()
        appInstance=this

        sharedPref=this.getSharedPreferences("KotlinSharedPref",android.content.Context.MODE_PRIVATE)
        editor=sharedPref.edit()

        setApiClient()
    }

    private fun setApiClient(){
        gson=GsonBuilder().create()
        retrofitClient=Retrofit.Builder()
                .client(getClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        apiRetroService=retrofitClient.create(APIRetro::class.java)
    }

    fun getApiService():APIRetro{
        return apiRetroService
    }

    fun getSharePref():SharedPreferences=sharedPref
    fun getSharedPrefEditor():SharedPreferences.Editor=editor


    private fun getClient():OkHttpClient{
        var httpClient:OkHttpClient.Builder=OkHttpClient.Builder()
        httpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): Response {

                var request:Request =chain!!.request()
                var autheticationRequest : Request=request.newBuilder()
                        .header("apikey",API_KEY)
                        .header("username",USER_NAME)
                        .header("password",PASSWORD).build()

                return chain!!.proceed(autheticationRequest)
            }
        })
        httpClient.connectTimeout(1000,TimeUnit.MILLISECONDS)
        return httpClient.build()
    }
}
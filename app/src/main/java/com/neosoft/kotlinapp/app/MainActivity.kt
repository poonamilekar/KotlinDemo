package com.neosoft.kotlinapp.app


import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.neosoft.kotlinapp.R
import com.neosoft.kotlinapp.adapters.MyAdapter
import com.neosoft.kotlinapp.data.AnnotationData
import com.neosoft.kotlinapp.data.AnnotationResponse
import com.neosoft.kotlinapp.data.MyData
import com.neosoft.kotlinapp.db.SqliteDataManager
import com.neosoft.kotlinapp.fragments.FirstFragment
import com.neosoft.kotlinapp.models.Employee
import com.neosoft.kotlinapp.retro.APIRetro
import com.neosoft.kotlinapp.utils.Constants
import com.neosoft.kotlinapp.utils.DownloadImage
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(),Constants {

    //lateinit var mRecyclerView : RecyclerView

    lateinit var mLayoutManager: RecyclerView.LayoutManager

    lateinit var adapter: MyAdapter
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    public val TAG="MainActivity"
    lateinit var retrofit : Retrofit
    val BASE_URL="http://go.saesa.cl:81/public/index.php/"
    val API_KEY="basic_token"
    val USER_NAME="abcd"
    val PASSWORD="123456";

    var mArrayList:ArrayList<AnnotationData> = ArrayList()
    private val REQUEST_PERMISSION=1
    lateinit var sqliteDataManager:SqliteDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ButterKnife.bind(this)

        mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
       /* adapter = MyAdapter(this, getList())
        recyclerView.adapter = adapter*/
//        askPermission()

        loadFragment()

        checkKotlinBasics()
        //Initialize sqlite
        sqliteDataManager= SqliteDataManager(this,DATABASE_NAME,null,SQLITE_VERSION)

        //Retrofit
        getApiService().getAnnotationFilteredData(4,"").enqueue(object : Callback<AnnotationResponse>{
            override fun onFailure(call: Call<AnnotationResponse>?, t: Throwable?) {
               Log.e(TAG,""+ (t?.message ?: "Error"))
            }

            override fun onResponse(call: Call<AnnotationResponse>?, response: Response<AnnotationResponse>?) {
                Log.e(TAG,"status : "+response?.body()?.status)
                if(response?.body()?.status == 0) {
                    mArrayList.addAll(response!!.body()!!.annotationDataArrayList!!)
                    var list=ArrayList<AnnotationData>()
                    for(index in 1 downTo 100 ){
                        list.addAll(mArrayList)
                    }
                    sqliteDataManager.deleteAllRecords()
                    sqliteDataManager.insertAnnotionData(list)

                    /*for(item in mArrayList){
                        Log.e(TAG,"item : "+item)
                    }*/
                    setListAdpater(mArrayList)

                    sqliteDataManager.deleteAllRecords()
                    sqliteDataManager.bulkInsertAnnotionData(list)
                }
            }
        })
    }

    fun askPermission(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),REQUEST_PERMISSION)
        }else{
            DownloadImage().execute()
        }
    }
    private fun getApiService(): APIRetro {
        retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(APIRetro::class.java)
    }
    private fun getClient(): OkHttpClient? {
        var okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
                var request : Request = chain!!.request()
                var requestAuthetication : Request = request.newBuilder()
                        .header("apikey",API_KEY)
                        .header("username",USER_NAME)
                        .header("password",PASSWORD).build()

                return chain.proceed(requestAuthetication)
            }
        })
        return  okHttpClient.build()
    }

    fun hasPrefix(x: Any) = when(x) {
        is String -> {
            Log.e(TAG, "x is string")
            x.startsWith("Poo")
        }
        else -> false
    }
    private fun loadFragment() {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameContainer, FirstFragment.newInstance("text1", "text2"))
        fragmentTransaction.commit()
    }

    private fun getList(): ArrayList<MyData> {
        var list = ArrayList<MyData>()
        list.add(MyData("foreground", R.drawable.ic_launcher_foreground))
        list.add(MyData("background", R.drawable.ic_launcher_background))
        list.add(MyData("foreground 1", R.drawable.ic_launcher_foreground))
        list.add(MyData("background 1", R.drawable.ic_launcher_background))

        list.add(MyData("foreground 2", R.drawable.ic_launcher_foreground))

        list.add(MyData("background 2", R.drawable.ic_launcher_background))
        list.add(MyData("foreground 4", R.drawable.ic_launcher_foreground))
        list.add(MyData("background 4", R.drawable.ic_launcher_background))

        return list
    }

    private fun setListAdpater(list:ArrayList<AnnotationData>){

        adapter = MyAdapter(this, list)
        recyclerView.adapter = adapter
    }

    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum1(a:Int,b:Int =0) = a+b

    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    fun allString(vararg str:String){
        for(s in str){
            Log.e(TAG," "+s)
        }
    }

    /*infix fun Int.abc(x: Int): Int {

    }*/

    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // 'this' corresponds to the list
        this[index1] = this[index2]
        this[index2] = tmp
        Log.e(TAG, "index1:$index1 index2:$index2")
    }

    private fun checkKotlinBasics(){
        //Kotlin Basics
        Log.e(TAG, "Sum of 2:" + sum(1, 2))
        Log.e(TAG, "** Sum of b and a:" + sum(b = 2, a = 3))
        Log.e(TAG, "** Sum1 :" + sum1(a = 3))
        Log.e(TAG, "** printSum :" + printSum(b = 3, a = 4))
        Log.e(TAG, "** allString :" + allString("abc","efg","hij","klm"))
        Log.e(TAG, "** shl :" + 1.shl(2))
        Log.e(TAG, "** plus :" + 1.plus(2))
        Log.e(TAG, "** minus :" + 1.minus(2))
        Log.e(TAG, "** multi :" + 2.times(2))
        Log.e(TAG, "** div :" + 1.div(2))
        Log.e(TAG, "** mod :" + 3.mod(2))
        Log.e(TAG, "** rem :" + 1.rem(2))
        Log.e(TAG, "** inc :" + 2.inc())
        Log.e(TAG, "** dec :" + 5.dec())
        Log.e(TAG, "** unaryMinus :" + 5.unaryMinus())
        Log.e(TAG, "** unaryPlus :" + 5.unaryPlus())
        Log.e(TAG, "** rangeTo 5-1:" + 5.rangeTo(1))

        val range=5.rangeTo(1)
        for(i  in range) {
            Log.e(TAG,"range:"+i)
        }
        Log.e(TAG, "** rangeTo 1-5:" + 1.rangeTo(5))


        val a : Int = 1
//        a =2
        Log.e(TAG,"a:"+a)

        val l = mutableListOf(1, 2, 3)
        Log.e(TAG,"swap:"+l.swap(0, 2))
        val text = """
    for (c in "foo")
        print(c)
""".trimMargin()

        val text1 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()

        val price = """
${'$'}9.99
"""
        Log.e(TAG,"$text and $text1 and $price")
        loop@ for (i in 1..10) {
            for (j in 1..10) {
                if (i == 2 && i== j) {
                    //break@loop
                    continue@loop
                }
                Log.e(TAG,"i $i j $j")
            }
        }
        var q=10
        var b=20
        val max = if (q > b) {
            Log.e(TAG,"Choose a")
            q
        } else {
            Log.e(TAG,"Choose b")
            b
        }
        Log.e(TAG,"max : $max")
        q=3
        when (q) {
            1,3 -> print("x == 1 or x== 3")
            2 -> print("x == 2")
            10 -> Log.e(TAG,"x == 10")

            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
        Log.e(TAG,""+hasPrefix("Poonam"))
        var array : ArrayList<String> = ArrayList()
        array.add("a")
        array.add("b")
        array.add("c")
        array.add("d")
        array.add("e")

        var array1 = arrayOf("1","2","3")

        for(data in array){
            Log.e(TAG,"com.neosoft.kotlinapp.data : "+data)
        }
        for ((index, value) in array.withIndex() ) {
            System.out.println("the element at $index is $value")
        }

        for(index in 1 downTo 100){

        }

        while(q > 0)
        {
            System.out.println("$q")
            q--
        }
        //Kotlin classes and Objects
        var e= Employee("Poonam")
        Log.e(TAG,"**** : "+e.firstProperty)

        ABC(1).v()
    }

    open class Outer(value:Int ) :Any(){
        val accessInDerived=0
         open val bar: Int
            get() = 0

         private var simple : Int
             get() = 1
             set(value) {
                this.simple = value
            }

        open fun v(){
            Log.e("","Message v")
        }

        inner class Nested {
            fun foo() : Int{
                Log.e("TAG","Nested $bar")
                return 2
            }
        }
    }

    class ABC : Outer, B {

        override var bar=1
        constructor(field:Int) :super(field)

        override fun v() {
            super<B>.v()
            super<Outer>.v()
            System.out.println("$accessInDerived")
        }
    }

    interface B {
        fun v() { System.out.println("v") } // interface members are 'open' by default
        fun b() { System.out.println("b") }
    }

    open class MyView : View {
        constructor(context: Context) :super(context){
            Log.e("TAG","vxmbn,z")

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_PERMISSION -> {
                if(grantResults.isNotEmpty()  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    DownloadImage().execute()
                }
            }
        }
    }

}

package com.neosoft.kotlinapp.app

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.neosoft.kotlinapp.R
import com.neosoft.kotlinapp.data.AnnotationData
import com.neosoft.kotlinapp.databinding.ActivityNextBinding
import java.util.*

/*@BindingAdapter("srcCompat")
fun setImage(view : ImageView, value :String ) {
    var IMAGE_PATH : String="http://go.saesa.cl:81/public/upload"

    Glide.with(view.context).load(IMAGE_PATH+value).into(view)

}

@BindingAdapter("abc")
fun setImage1(view : ImageView, value :String ) {
    var IMAGE_PATH : String="http://go.saesa.cl:81/public/upload"

    Glide.with(view.context).load(IMAGE_PATH+value).into(view)

}*/
class NextActivity : AppCompatActivity() {
    lateinit var binding:ActivityNextBinding
    lateinit var allData:ArrayList<AnnotationData>
    var i=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_next)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_next) as ActivityNextBinding
        var data=intent.getSerializableExtra("data") as AnnotationData
        allData=intent.getSerializableExtra("allData") as ArrayList<AnnotationData>
        Log.e("","data:"+data+" size:"+allData.size)
        binding.annotationData=data
        binding.handlers=this
        setData()
    }
    fun setData(){

        Timer().scheduleAtFixedRate(
                object :TimerTask(){
                    override fun run() {
                        if(!allData.isEmpty()) {
                            if(i >= allData.size)
                                i=0

                            Log.e("", "i:" + i)
                            binding.annotationData = allData.get(i)
                            i++
                        }
                    }
                } ,
                1000,
                2000
        )
    }

    companion object {
        @JvmStatic
        @BindingAdapter("srcCompat")
        fun setImage(view : ImageView, value :String ) {
            var IMAGE_PATH : String="http://go.saesa.cl:81/public/upload"

            Glide.with(view.context).load(IMAGE_PATH+value).into(view)

        }

        @JvmStatic
        @BindingAdapter("abc")
        fun setImage1(view : ImageView, value :String ) {
            var IMAGE_PATH : String="http://go.saesa.cl:81/public/upload"

            Glide.with(view.context).load(IMAGE_PATH+value).into(view)

        }
    }

    fun onButtonClick(view: View,data :AnnotationData){
        Log.e("aaa","onButtonClick :"+data.toString())
    }


}

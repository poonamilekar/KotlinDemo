package com.neosoft.kotlinapp.models

import com.neosoft.kotlinapp.app.MyApp.Companion.appInstance

/**
 * Created by Poonam Ilekar on 2/1/18.
 */
object MySingaltonSharedPreferannce {
    private val ID:String="id"

    fun setId(id:Int)=appInstance.getSharedPrefEditor().putInt("ID",id).commit()

    fun getId():Int = appInstance.getSharePref().getInt("ID",0)

}
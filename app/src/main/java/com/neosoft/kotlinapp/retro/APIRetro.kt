package com.neosoft.kotlinapp.retro

import com.neosoft.kotlinapp.data.AnnotationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Poonam Ilekar on 27/12/17.
 */
interface APIRetro {
    val GET_ANNOTATION_FILTERED: String
        get() = "api/GetannotationFilterdata"

    @FormUrlEncoded
    @POST("api/GetannotationFilterdata")
    fun getAnnotationFilteredData(@Field("user_id") user_id: Int, @Field("subcategories_ids") subcategories: String): Call<AnnotationResponse>

}
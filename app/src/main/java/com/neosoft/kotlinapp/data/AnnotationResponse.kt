package com.neosoft.kotlinapp.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Poonam Ilekar on 27/12/17.
 */
data class AnnotationResponse( @SerializedName("status") val status:Int, @SerializedName("message") val message:String, @SerializedName("result") var annotationDataArrayList:ArrayList<AnnotationData>)

data class AnnotationData(val id :Int,@SerializedName("user_id") val userId:Int,
                          @SerializedName("image_path") val imagePath:ArrayList<String>,
                          @SerializedName("alert_description") val alertDescription:String,
                          @SerializedName("alert_title") val alertTitle:String,
                          @SerializedName("category_type_id") val categoryTypeId:String,
                          @SerializedName("sub_category_id") val subCategoryId:String,
                          @SerializedName("latitude") val latitude:String,
                          @SerializedName("longitude") val longitude:String,
                          @SerializedName("status") val status:String,
                          @SerializedName("sub_category_image") val subCategoryImage :String,
                          @SerializedName("created_user_level") var createdUserLevel:Int,
                          @SerializedName("updated_by") var updatedBy:Int,
                          @SerializedName("created_user_id") var createdUserId:Int,
                          @SerializedName("created_at") var createdAt : String)


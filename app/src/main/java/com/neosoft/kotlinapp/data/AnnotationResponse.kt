package com.neosoft.kotlinapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Poonam Ilekar on 27/12/17.
 */
data class AnnotationResponse( @SerializedName("status") val status:Int, @SerializedName("message") val message:String, @SerializedName("result") var annotationDataArrayList:ArrayList<AnnotationData>)

data class AnnotationData(var id :Int,@SerializedName("user_id") var userId:Int,
                          @SerializedName("image_path") var imagePath:ArrayList<String>?,
                          @SerializedName("alert_description") var alertDescription:String?,
                          @SerializedName("alert_title") var alertTitle:String?,
                          @SerializedName("category_type_id") var categoryTypeId:String?,
                          @SerializedName("sub_category_id") var subCategoryId:String?,
                          @SerializedName("latitude") var latitude:String?,
                          @SerializedName("longitude") var longitude:String?,
                          @SerializedName("status") var status:String?,
                          @SerializedName("sub_category_image") var subCategoryImage :String?,
                          @SerializedName("created_user_level") var createdUserLevel:Int?,
                          @SerializedName("updated_by") var updatedBy:Int?,
                          @SerializedName("created_user_id") var createdUserId:Int?,
                          @SerializedName("created_at") var createdAt : String?):Serializable{
    constructor() : this(0,0,null,null,null,null,
            null,null,null,null,null,null,0,0,null)
}


package com.neosoft.kotlinapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.neosoft.kotlinapp.data.AnnotationData
import com.neosoft.kotlinapp.R
import kotlinx.android.synthetic.main.item_layout.view.*


/**
 * Created by Poonam Ilekar on 22/12/17.
 */
class MyAdapter(var context: Context,var list:ArrayList<AnnotationData> ) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var IMAGE_PATH : String="http://go.saesa.cl:81/public/upload"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.txtName.text=list.get(position).alertDescription
        Glide.with(context).load(IMAGE_PATH+list.get(position).subCategoryImage).into(holder.itemView.imgProfile)
        //holder.itemView.imgProfile.setImageResource(list.get(position).subCategoryImage)
    }
    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}
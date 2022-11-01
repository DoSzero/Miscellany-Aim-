package com.aim.miscellanyaim.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aim.miscellanyaim.R
import com.aim.miscellanyaim.activities.CategoriesActivity
import com.aim.miscellanyaim.models.SliderModel

class SliderAdapter(private var list:ArrayList<SliderModel>, private var context: Context)
    :RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val sliderImage:ImageView= itemView.findViewById(R.id.slider_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.slider_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.sliderImage.setImageResource(currentItem.sliderImage)

        holder.itemView.setOnClickListener {
            val intent= Intent(context, CategoriesActivity::class.java)
            intent.putExtra("categoryId",currentItem.sliderId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
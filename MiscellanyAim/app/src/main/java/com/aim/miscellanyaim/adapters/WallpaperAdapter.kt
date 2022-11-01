package com.aim.miscellanyaim.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aim.miscellanyaim.R
import com.aim.miscellanyaim.activities.DownloadActivity
import com.aim.miscellanyaim.models.WallPaperData


class WallpaperAdapter(
    private var wallImgLis:ArrayList<WallPaperData>,
    private var c: Context
) :RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
         val designImage:ImageView= itemView.findViewById(R.id.design_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newListWall = wallImgLis[position]
        holder.designImage.setImageResource(newListWall.wallImg)
        holder.designImage.setOnClickListener {
            val wallInt = Intent(c, DownloadActivity::class.java)
            wallInt.putExtra("wallImg",newListWall.wallImg)
            c.startActivity(wallInt)
        }
    }

    override fun getItemCount(): Int {
        return wallImgLis.size
    }
}
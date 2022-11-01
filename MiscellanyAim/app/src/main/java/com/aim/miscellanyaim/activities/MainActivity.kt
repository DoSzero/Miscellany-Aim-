package com.aim.miscellanyaim.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.aim.miscellanyaim.R
import com.aim.miscellanyaim.adapters.SliderAdapter
import com.aim.miscellanyaim.databinding.ActivityMainBinding
import com.aim.miscellanyaim.models.SliderModel
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sliderAdapter: SliderAdapter
    lateinit var isList:ArrayList<SliderModel>

    private var backPressedTime: Long = 0
    lateinit var sliderHandler: Handler

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        isList = ArrayList()
        sliderHandler = Handler()
        sliderAdapter = SliderAdapter(isList,this)

        binding.apply {
            isList.add(SliderModel("creative",R.drawable.creative))
            isList.add(SliderModel("trending",R.drawable.trending))
            isList.add(SliderModel("lifestyle",R.drawable.lifestyle))
            isList.add(SliderModel("calm",R.drawable.quiet_calm))
            sliderAdapter.notifyDataSetChanged()

            isViewImage.adapter = sliderAdapter
            isViewImage.clipChildren = false
            isViewImage.clipToPadding = false
            isViewImage.offscreenPageLimit = 3

            isViewImage.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer= CompositePageTransformer()

            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }

            isViewImage.setPageTransformer(compositePageTransformer)
            isViewImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable,5000)
                    if (position==isList.size-2){
                        isViewImage.post(runnable)
                    }
                }
            })
        }

        binding.nature.setOnClickListener {
            val intent= Intent(this, CategoriesActivity::class.java)
            intent.putExtra("categoryId","nature")
            startActivity(intent)
        }

        binding.wildlife.setOnClickListener {
            val intent= Intent(this, CategoriesActivity::class.java)
            intent.putExtra("categoryId","wildlife")
            startActivity(intent)
        }

        binding.neonArt.setOnClickListener {
            val intent= Intent(this, CategoriesActivity::class.java)
            intent.putExtra("categoryId","neonart")
            startActivity(intent)
        }

        binding.vehicles.setOnClickListener {
            val intent= Intent(this, CategoriesActivity::class.java)
            intent.putExtra("categoryId","vehicles")
            startActivity(intent)
        }
    }

    val sliderRunnable = Runnable {
        binding.isViewImage.currentItem = binding.isViewImage.currentItem +1
    }

    @SuppressLint("NotifyDataSetChanged")
    val runnable = Runnable {
        isList.addAll(isList)
        sliderAdapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)

    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable,5000)
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity()
        } else {
            Snackbar.make(this.findViewById(android.R.id.content),"Double Press To Exit",Snackbar.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}
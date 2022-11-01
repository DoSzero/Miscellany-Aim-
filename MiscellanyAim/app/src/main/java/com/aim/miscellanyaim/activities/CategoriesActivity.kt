package com.aim.miscellanyaim.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.aim.miscellanyaim.databinding.ActivityCategoriesBinding
import com.aim.miscellanyaim.R
import com.aim.miscellanyaim.adapters.WallpaperAdapter
import com.aim.miscellanyaim.models.WallPaperData

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var wallImgList: ArrayList<WallPaperData>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_categories)
        wallImgList = ArrayList()

        val categoryId = intent.getStringExtra("categoryId").toString()
        val layoutManager = GridLayoutManager(this,2)
        val adapter = WallpaperAdapter(wallImgList,this)

        binding.categoryRv.layoutManager = layoutManager
        binding.categoryRv.showShimmerAdapter()

        binding.apply {
            when(categoryId){

                "nature"-> {
                    categoryText.text = "Nature"
                    wallPaperNatureList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

                "wildlife"-> {
                    categoryText.text = "Wildlife"
                    wallPaperWildlifeList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

                "neonart"-> {
                    categoryText.text = "Neon & Art"
                    wallPaperFoodList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

                "vehicles"-> {
                    categoryText.text = "Vehicles"
                    wallPaperCarList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

                "creative"-> {
                    categoryText.text = "Creative"
                    wallPaperCreativList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

                "lifestyle"-> {
                    categoryText.text = "Lifestyle"
                    wallPaperCarList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

                "trending"-> {
                    categoryText.text = "Trending"
                    wallPaperTrendList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

                "topcharts"-> {
                    categoryText.text = "Top Charts"
                    wallPaperTrendList()
                    binding.categoryRv.adapter = adapter
                    binding.categoryRv.hideShimmerAdapter()
                }

            }
        }

    }
    private fun wallPaperTrendList() {
        wallImgList.add(WallPaperData(R.drawable.car1))
        wallImgList.add(WallPaperData(R.drawable.car2))
        wallImgList.add(WallPaperData(R.drawable.ic_food3))
        wallImgList.add(WallPaperData(R.drawable.jungle5))
        wallImgList.add(WallPaperData(R.drawable.car5))
        wallImgList.add(WallPaperData(R.drawable.ic_food4))
    }

    private fun wallPaperNatureList() {
        wallImgList.add(WallPaperData(R.drawable.ic_nature1))
        wallImgList.add(WallPaperData(R.drawable.ic_nature2))
        wallImgList.add(WallPaperData(R.drawable.ic_nature3))
        wallImgList.add(WallPaperData(R.drawable.ic_nature4))
        wallImgList.add(WallPaperData(R.drawable.ic_nature5))
        wallImgList.add(WallPaperData(R.drawable.ic_nature6))
    }

    private fun wallPaperFoodList() {
        wallImgList.add(WallPaperData(R.drawable.ic_food1))
        wallImgList.add(WallPaperData(R.drawable.ic_food2))
        wallImgList.add(WallPaperData(R.drawable.ic_food3))
        wallImgList.add(WallPaperData(R.drawable.ic_food4))
        wallImgList.add(WallPaperData(R.drawable.ic_food5))
        wallImgList.add(WallPaperData(R.drawable.ic_food6))
        wallImgList.add(WallPaperData(R.drawable.ic_food7))
    }

    private fun wallPaperWildlifeList() {
        wallImgList.add(WallPaperData(R.drawable.jungle1))
        wallImgList.add(WallPaperData(R.drawable.jungle2))
        wallImgList.add(WallPaperData(R.drawable.jungle3))
        wallImgList.add(WallPaperData(R.drawable.jungle4))
        wallImgList.add(WallPaperData(R.drawable.jungle5))
        wallImgList.add(WallPaperData(R.drawable.jungle6))
    }

    private fun wallPaperCarList() {
        wallImgList.add(WallPaperData(R.drawable.car1))
        wallImgList.add(WallPaperData(R.drawable.car2))
        wallImgList.add(WallPaperData(R.drawable.car3))
        wallImgList.add(WallPaperData(R.drawable.car4))
        wallImgList.add(WallPaperData(R.drawable.car5))
    }

    private fun wallPaperCreativList() {
        wallImgList.add(WallPaperData(R.drawable.creativ1))
        wallImgList.add(WallPaperData(R.drawable.creativ2))
        wallImgList.add(WallPaperData(R.drawable.creativ3))
        wallImgList.add(WallPaperData(R.drawable.creativ4))
        wallImgList.add(WallPaperData(R.drawable.creativ5))
        wallImgList.add(WallPaperData(R.drawable.creativ6))
    }

}
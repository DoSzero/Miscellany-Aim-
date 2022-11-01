package com.aim.miscellanyaim.activities

import android.app.AlertDialog
import android.app.WallpaperManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aim.miscellanyaim.R
import com.aim.miscellanyaim.databinding.ActivityDownloadBinding
import com.google.android.material.snackbar.Snackbar
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler


class DownloadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDownloadBinding
    private lateinit var decorView: View
    private lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_download)

        supportActionBar?.hide()
        decorView = window.decorView

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        url = intent.getStringExtra("photoUrl").toString()

        val dialogView = View.inflate(this, R.layout.loading_dialog, null)
        val builder = AlertDialog.Builder(this).setView(dialogView).create()

        builder.window?.setBackgroundDrawableResource(android.R.color.transparent)
        builder.setCancelable(false)

        binding.apply {

            // get current Item
            val newWallImg = intent.extras?.getInt("wallImg")
            if (newWallImg != null) {
                binding.setImage.setImageResource(newWallImg)
            }

            // set
            binding.setButton.setOnClickListener {
                try {
                    if (newWallImg != null) {
                        WallpaperManager.getInstance(applicationContext).setResource(newWallImg)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                Snackbar.make(it,"Loading...",Snackbar.LENGTH_LONG).show()
            }


            shareButton.setOnClickListener {
                OmegaIntentBuilder(this@DownloadActivity)
                    .share()
                    .filesUrls(url)
                    .download(object : DownloadCallback {
                        override fun onDownloaded(
                            success: Boolean,
                            contextIntentHandler: ContextIntentHandler
                        ) {

                            contextIntentHandler.startActivity()
                        }
                    }
                )
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
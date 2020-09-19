package com.dino.something.module.splash

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.dino.something.R
import com.dino.something.databinding.ActivitySplashScreenBinding
import com.dino.something.module.home.HomeActivity
import com.dino.something.utility.Constants.ConstantVariables.SPLASH_TIME_OUT

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var mDelayHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen)
        mDelayHandler = Handler()
        mDelayHandler.postDelayed(mRunnable, SPLASH_TIME_OUT)
    }

    private val mRunnable: Runnable = Runnable {
        val intent = Intent(applicationContext,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
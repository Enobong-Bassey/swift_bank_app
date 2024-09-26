package com.example.swiftbank

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftbank.databinding.ActivitySplashBinding
import android.view.animation.AnimationUtils

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.myLooper()!!).postDelayed(
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 4000
        )

        var bankLogoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        var bankNameOneAnim = AnimationUtils.loadAnimation(this, R.anim.namefirstpart_animation)
        var bankNameTwoAnim = AnimationUtils.loadAnimation(this, R.anim.namesecondpart_animation)

        binding.banklogo.animation = bankLogoAnim
        binding.banknamefirstpart.animation = bankNameOneAnim
        binding.banknamesecondpart.animation = bankNameTwoAnim

    }
}
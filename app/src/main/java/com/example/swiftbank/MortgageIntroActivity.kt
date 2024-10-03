package com.example.swiftbank

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.swiftbank.databinding.ActivityMortgageIntroBinding

class MortgageIntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMortgageIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMortgageIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMortgage.setOnClickListener {
            startActivity(Intent(this@MortgageIntroActivity, MortgageApplyFormActivity::class.java))
            finish()
        }
    }
}
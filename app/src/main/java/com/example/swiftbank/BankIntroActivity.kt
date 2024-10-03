package com.example.swiftbank

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftbank.databinding.ActivityBankIntroBinding

class BankIntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBankIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBankIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnmortgage.setOnClickListener {
            startActivity(Intent(this@BankIntroActivity, MortgageIntroActivity::class.java))
            finish()
        }

    }
}
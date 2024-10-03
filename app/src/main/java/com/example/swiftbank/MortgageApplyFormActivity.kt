package com.example.swiftbank

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.swiftbank.databinding.ActivityMortgageApplyFormBinding

class MortgageApplyFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMortgageApplyFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMortgageApplyFormBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_mortgage_apply_form)

    }
}
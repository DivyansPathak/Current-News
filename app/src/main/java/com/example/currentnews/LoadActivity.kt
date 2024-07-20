package com.example.currentnews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currentnews.databinding.ActivityLoadBinding

class LoadActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this,SpinnerActivity::class.java))
            finish()
        },4000)

    }
}
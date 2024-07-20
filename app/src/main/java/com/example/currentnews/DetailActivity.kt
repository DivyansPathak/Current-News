package com.example.currentnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.currentnews.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title : String? = intent.getStringExtra("title")
        val image : String? = intent.getStringExtra("image")
        val content : String? = intent.getStringExtra("content")
        val url : String? = intent.getStringExtra("url").toString()

        binding.txtDetTitle.text = title
        binding.txtDetContent.text = content
        Glide.with(this).load(image).into(binding.detailImg)


        binding.btnReadMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }




    }

//    fun Go_to_full_article(view: View) {
//        view.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            startActivity(intent)
//        }

}
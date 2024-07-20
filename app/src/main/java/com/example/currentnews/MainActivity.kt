package com.example.currentnews

import Article
import NewsApp
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.currentnews.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 9dc29efbb4784ee99d7ecfbc73055ab0
//
// https://newsapi.org/v2/

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: MyAdapter
    val newsInfo = mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myAdapter = MyAdapter(this,newsInfo)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = myAdapter


        getPara()

    }

    private fun getPara()  {

        val country : String? = intent.getStringExtra("country")
        val category = intent.getStringExtra("category")

        if (category != null && country != null) {
            fetchNews(country,category)
        }
    }

    private fun fetchNews(country : String,category : String) {


        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Pleas wait....")
        progressDialog.create()
        progressDialog.show()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build().create(ApiService::class.java)

        val response = retrofit.getHeadLine(country,category,"9dc29efbb4784ee99d7ecfbc73055ab0")
        response.enqueue(object : Callback<NewsApp>{
            override fun onResponse(call: Call<NewsApp>, response: Response<NewsApp>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    responseBody.articles.forEach {
//                        val author = it.author
//                        binding.textView3.text = author
//                        binding.textView3.text = it.title
//                        Glide.with(this@MainActivity).load(it.urlToImage).into(binding.imageView)

                        newsInfo.clear()
                        newsInfo.addAll(responseBody.articles)
                        myAdapter.notifyDataSetChanged()
                        progressDialog.dismiss()
                    }




                }
            }

            override fun onFailure(call: Call<NewsApp>, t: Throwable) {
                Toast.makeText(this@MainActivity,"${t.localizedMessage}",Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
        })
    }
}
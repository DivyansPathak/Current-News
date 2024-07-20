package com.example.currentnews

import Article
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.currentnews.databinding.SingleRowBinding

class MyAdapter(val context: Context,val newsInfo : List<Article>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: SingleRowBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(newsInfo: Article){
                binding.rvTxtHeadin.text = newsInfo.title
                binding.rvTxtAuth.text = newsInfo.author
                Glide.with(binding.root.context).load(newsInfo.urlToImage).into(binding.rvImage)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return newsInfo.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(newsInfo[position])
        val title = newsInfo[position].title
        val image = newsInfo[position].urlToImage
        val content = newsInfo[position].content
        val url = newsInfo[position].url
        holder.binding.root.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("title",title)
            intent.putExtra("image",image)
            intent.putExtra("content",content)
            intent.putExtra("url",url)
            context.startActivity(intent)

        }

    }
}
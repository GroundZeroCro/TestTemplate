package com.groundzero.testing_mvp.ui.post

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.groundzero.testing_mvp.R
import com.groundzero.testing_mvp.data.post.Post

class PostAdapter(private val context: Context, private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context), parent)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class CustomViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_post_recycler_view, parent, false)) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val text: TextView = itemView.findViewById(R.id.text)
        private val date: TextView = itemView.findViewById(R.id.date)
        fun bind(post: Post) {
            title.text = post.title
            text.text = post.text
            date.text = post.date.toString()
        }
    }
}

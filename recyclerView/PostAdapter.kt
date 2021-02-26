package com.example.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstKotlinApp.R

class PostAdapter(val dummyData: ArrayList<Post>, val myListener: OnPostClickListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(postView: View) : RecyclerView.ViewHolder(postView), View.OnClickListener{
        val iconImage: ImageView = postView.findViewById(R.id.icon_image_view)
        val title: TextView = postView.findViewById(R.id.title)
        val body: TextView = postView.findViewById(R.id.body)
        val deleteIcon: ImageView = postView.findViewById(R.id.delete_post_image)
        val editIcon: ImageView = postView.findViewById(R.id.edit_post_image)

        init {
            deleteIcon.setOnClickListener(this)
            editIcon.setOnClickListener(this)
        }

        override fun onClick(v: View?){
            val position = adapterPosition
            if(v?.id == editIcon.id){
                myListener.onEditPost(position)
            }else{
                myListener.onDeletePost(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView = LayoutInflater.from(parent.context).inflate(R.layout.post, parent, false)
        return PostViewHolder(postView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = dummyData[position]
        holder.iconImage.setImageResource(currentPost.image)
        holder.title.text = currentPost.title
        holder.body.text = currentPost.body
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }

    interface OnPostClickListener{
        fun onEditPost(position: Int)
        fun onDeletePost(position: Int)
    }
}
package com.example.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstKotlinApp.R
import com.example.firstKotlinApp.databinding.FragmentListBinding

class RecyclerViewFragment: Fragment(R.layout.fragment_list), PostAdapter.OnPostClickListener {
    private lateinit var binding: FragmentListBinding
    val dummyList = MockDatabase.createMockData()
    val adapter = PostAdapter(dummyList, this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycleViewMain).apply{
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@RecyclerViewFragment.adapter
        }
    }

    override fun onEditPost(position: Int){
        val clickedPost = dummyList[position]
        clickedPost.title = "Updated title"
        clickedPost.body = "Updated body"
        adapter.notifyItemChanged(position)
    }

    override fun onDeletePost(position: Int) {
        dummyList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}

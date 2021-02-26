package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.firstKotlinApp.R
import com.example.firstKotlinApp.databinding.ActivityMainBinding
import com.example.recyclerView.RecyclerViewFragment
import com.example.tempConverter.TempConverterFragment
import com.example.uploaderView.UploaderFragment

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "First Kotlin App"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = RecyclerViewFragment()
        val tempConverterView = TempConverterFragment()
        val uploaderView = UploaderFragment(this)
        setFragmentView(recyclerView)

        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.listView ->  setFragmentView(recyclerView)
                R.id.tempConverterView -> setFragmentView(tempConverterView)
                R.id.videoUploaderView -> setFragmentView(uploaderView)
            }
            true
        }
    }

    private fun setFragmentView(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_view, fragment)
            //Will return to previous page when tap "Back Button" on the phone
            addToBackStack(null)
            commit()
        }
    }
}
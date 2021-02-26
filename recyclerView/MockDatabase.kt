package com.example.recyclerView

import com.example.firstKotlinApp.R

class MockDatabase {
    companion object{
        fun createMockData(): ArrayList<Post>{
            val list = ArrayList<Post>()
            for(i in 0 until 20){
                val imageToSelect = when (i % 3){
                    0 -> R.drawable.ic_baseline_account_balance
                    1 -> R.drawable.ic_baseline_account_circle
                    2 ->R.drawable.ic_baseline_ac_unit
                    else -> R.drawable.ic_baseline_access_alarms
                }
                list.add(
                        Post(
                            imageToSelect,
                            title = "Title post of $i",
                            body = "Title post of $i"
                        )
                )
            }
            return list
        }
    }
}
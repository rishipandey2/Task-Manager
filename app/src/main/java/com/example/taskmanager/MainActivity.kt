package com.example.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       Thread.sleep(2000)
       installSplashScreen()
        setContentView(R.layout.activity_main)


        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        // Set up the ViewPager Adapter
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Link TabLayout with ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "All Tasks"
                1 -> "Completed"
                2 -> "Pending"
                else -> null
            }
        }.attach()

    }
}


// ViewPager Adapter class
class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int = 3 // Total number of fragments

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllTaskFragment() // First Tab
            1 -> CompletedTaskFragment() // Second Tab
            2 -> PendingTaskFragment() // Third Tab
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}

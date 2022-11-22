package com.jge.newsstack.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.jge.newsstack.R

/**The activity will be the container to hold the main fragment,
 * reason for this is in the case where we would want to implement more screens
 * and fragments we would be able to implement a navigation drawer or a BottomNavigationBar
 * to help navigate between those fragments. Scalability.
 *
 * Another idea would be to just have 2 activities and have the details activity launched when
 * a list item is clicked on.
 * **/
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.fragment_container, MainFragment.TAG)
                addToBackStack(null)
            }
        }
    }
}
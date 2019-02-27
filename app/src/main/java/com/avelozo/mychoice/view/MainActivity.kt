package com.avelozo.mychoice.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import androidx.fragment.app.Fragment
import com.avelozo.mychoice.R.*
import com.avelozo.mychoice.R.layout.activity_main


class MainActivity : AppCompatActivity(){

    val SHARED_PREF_FILE_NAME = "com.avelozo.mychoice"
    val KEY_FIRST_TIME_USER = "FirstTimeUser"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        val initialFragment = getInitialFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(id.fragmentContainer,initialFragment)
            .commit()
    }



    private  fun getInitialFragment() : Fragment{

        val sharedPref = this.getSharedPreferences(
            SHARED_PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
        val isFirstTimeUser = sharedPref.getBoolean(KEY_FIRST_TIME_USER, true)

        return if(isFirstTimeUser){
            val editor = sharedPref.edit()
            editor.putBoolean(KEY_FIRST_TIME_USER, false)
            editor.apply()

            CategoryListFragment()
        }else {
            WelcomeFragment()
        }
    }
}

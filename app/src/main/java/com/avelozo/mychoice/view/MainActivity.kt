package com.avelozo.mychoice.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avelozo.mychoice.R

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, FirstFragment())
            .commit()
    }
}

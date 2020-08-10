package com.prabitha.nuomtask.contactListApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Address Book"
        //set back button

        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
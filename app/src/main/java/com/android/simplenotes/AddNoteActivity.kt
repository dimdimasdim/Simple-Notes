package com.android.simplenotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat.startActivityForResult

class AddNoteActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE_ADD = 1000

        fun launchAddNotePage(activity: Activity){
            val intent = Intent(activity, AddNoteActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CODE_ADD)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }
}
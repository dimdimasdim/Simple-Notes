package com.android.simplenotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.core.app.ActivityCompat.startActivityForResult
import com.android.simplenotes.model.Notes
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE_ADD = 1000
        const val INTENT_NOTE = "intent_note"

        fun launchAddNotePage(activity: Activity){
            val intent = Intent(activity, AddNoteActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CODE_ADD)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        listener()
    }

    private fun listener() {
        buttonSave.setOnClickListener {
            val data = Intent()
            val titleNotes = editTextTitle.text.toString().trim()
            val descNotes = editTextDescription.text.toString().trim()
            val createdAt = System.currentTimeMillis()
            if (TextUtils.isEmpty(titleNotes)){
                setResult(Activity.RESULT_CANCELED, data)
            } else {
                val note = Notes(title = titleNotes, description = descNotes, createdAt = createdAt)
                data.putExtra(INTENT_NOTE, note)
                setResult(Activity.RESULT_OK, data)
            }

            finish()
        }
    }
}
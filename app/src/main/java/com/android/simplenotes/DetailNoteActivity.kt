package com.android.simplenotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.simplenotes.model.Notes
import com.android.simplenotes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_detail_note.*

class DetailNoteActivity : AppCompatActivity() {

    private var notes: Notes? = null
    private lateinit var notesViewModel: NotesViewModel

    companion object{

        const val INTENT_NOTE_ADD = "intent_note_add"

        fun launchDetailNote(activity: AppCompatActivity, notes: Notes){
            val intent = Intent(activity, DetailNoteActivity::class.java)
            intent.putExtra(INTENT_NOTE_ADD, notes)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        handleIntent()
        initUI()
        listener()
    }

    private fun handleIntent() {
        notes = intent.getParcelableExtra(INTENT_NOTE_ADD)
    }

    private fun initUI() {
        updateUI(notes)
    }

    private fun updateUI(notes: Notes?) {
        textTitle.text = notes?.title
        textDescNotes.text = notes?.description
    }

    private fun listener() {
        buttonDelete.setOnClickListener {
            notesViewModel.delete(notes?.id ?: 0)
            finish()
        }

        buttonUpdate.setOnClickListener {
            AddNoteActivity.launchAddNotePage(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddNoteActivity.REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK){
            data?.getParcelableExtra<Notes>(AddNoteActivity.INTENT_NOTE)?.let { notes ->
                this.notes?.let { oldNote ->
                    oldNote.title = notes.title
                    oldNote.description = notes.description
                    notesViewModel.updateNotes(oldNote)
                    updateUI(oldNote)
                }
            }
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
        }
    }

}
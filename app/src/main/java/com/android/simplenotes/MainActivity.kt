package com.android.simplenotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.simplenotes.adapter.NotesAdapter
import com.android.simplenotes.model.Notes
import com.android.simplenotes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var notesAdapter: NotesAdapter? = null
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        initUI()
        listener()
        observer()
    }

    private fun observer() {
        notesViewModel.listAllNotes.observe(this, Observer { notes ->
            if (notes != null){
                notesAdapter?.setNotes(notes)
            }
        })
    }

    private fun listener() {
        fabAddNote.setOnClickListener {
            AddNoteActivity.launchAddNotePage(this@MainActivity)
        }
    }

    private fun initUI(){
        if (notesAdapter == null){
            notesAdapter = NotesAdapter(this, seeMoreDetails = { notes ->
                DetailNoteActivity.launchDetailNote(this, notes)
            })
            with(listNotes){
                layoutManager = LinearLayoutManager(this@MainActivity)
                addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
                setHasFixedSize(true)
                adapter = notesAdapter
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddNoteActivity.REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK){
            data?.getParcelableExtra<Notes>(AddNoteActivity.INTENT_NOTE)?.let { notes ->
                notesViewModel.insertNotes(notes)
            }
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
        }
    }

}
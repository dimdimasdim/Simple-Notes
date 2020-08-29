package com.android.simplenotes.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.simplenotes.R
import com.android.simplenotes.model.Notes

class NotesAdapter(
    context: Context,
    private val seeMoreDetails: ((Notes) -> Unit)? = null
): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var notes = emptyList<Notes>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val itemView = inflater.inflate(R.layout.item_notes, parent, false)
        return NotesViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        val currentNote = notes[position]
        with(holder){
            titleNote.text = currentNote.title
            descNotes.text = currentNote.description
            dateNotes.text = DateUtils.getRelativeTimeSpanString(currentNote.createdAt)
            containerItemNotes.setOnClickListener {
                seeMoreDetails?.invoke(currentNote)
            }
        }
    }

    inner class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleNote: AppCompatTextView = itemView.findViewById(R.id.textTitleNotes)
        val descNotes: AppCompatTextView = itemView.findViewById(R.id.textDescNotes)
        val dateNotes: AppCompatTextView = itemView.findViewById(R.id.textDateNotes)
        val containerItemNotes: ConstraintLayout = itemView.findViewById(R.id.containerItemNotes)
    }

    internal fun setNotes(notes: List<Notes>){
        this.notes = notes
        notifyDataSetChanged()
    }
}
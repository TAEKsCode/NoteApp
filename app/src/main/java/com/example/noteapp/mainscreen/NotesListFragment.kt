package com.example.noteapp.mainscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.database.Note
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment() {

    private lateinit var viewModel: NotesListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentNotesListBinding.inflate(inflater, container, false)
        val application = requireNotNull(activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao

        val viewModelFactory = NotesListViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NotesListViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = NotesAdapter(NoteClickListener { noteId ->
            viewModel.onNavigateToChangeNote(noteId)
        },
        NoteDeleteListener { noteId ->
            viewModel.onDelete(noteId)
        })
        binding.notesList.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToChangeNote.observe(viewLifecycleOwner, { note ->
            note?.let {
                findNavController().navigate(
                    NotesListFragmentDirections.actionNotesListFragmentToEditNoteFragment(note)
                )
                //Toast.makeText(activity, dataSource.getNoteById(note).value?.noteText, Toast.LENGTH_LONG).show()
                viewModel.onChangeNoteNavigated()

            }
        })
        return binding.root
    }
}
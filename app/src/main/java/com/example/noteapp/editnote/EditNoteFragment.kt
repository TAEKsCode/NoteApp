package com.example.noteapp.editnote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.databinding.FragmentEditNoteBinding
import com.example.noteapp.mainscreen.NotesListViewModel

class EditNoteFragment : Fragment() {

    private lateinit var viewModel: EditNoteViewModel
    private lateinit var binding: FragmentEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        val application = requireNotNull(activity).application
        val arguments = EditNoteFragmentArgs.fromBundle(requireArguments())

        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao

        val viewModelFactory = EditNoteViewModelFactory(dataSource, arguments.noteId)

        viewModel = ViewModelProvider(this, viewModelFactory).get(EditNoteViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToNotesList.observe(viewLifecycleOwner, {
            if (it == true) {
                findNavController().navigate(
                    EditNoteFragmentDirections.actionEditNoteFragmentToNotesListFragment()
                )
                viewModel.onDoneNavigating()
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_changes -> {
                viewModel.onChangeNoteText(binding.noteTextChange.text.toString())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
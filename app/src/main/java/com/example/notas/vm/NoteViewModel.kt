package com.example.notas.vm

import androidx.lifecycle.ViewModel


import com.example.notas.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.atomic.AtomicLong
class NoteViewModel : ViewModel(){
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes


    private val seq = AtomicLong(0)


    fun addNote(text: String) {
        val t = text.trim()
        if (t.isEmpty()) return
        val newNote = Note(id = seq.incrementAndGet(), text = t)
        _notes.value = _notes.value + newNote
    }

    fun removeNote(id: Long) {
        _notes.value = _notes.value.filterNot { it.id == id }
    }
}



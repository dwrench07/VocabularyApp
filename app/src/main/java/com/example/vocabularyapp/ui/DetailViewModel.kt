package com.example.vocabularyapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabularyapp.data.model.Flashcard
import com.example.vocabularyapp.data.repository.FlashcardRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: FlashcardRepository) : ViewModel() {
    var flashcard by mutableStateOf<Flashcard?>(null)
    var deleted by mutableStateOf(false)

    fun loadFlashcard(id: Int) {
        viewModelScope.launch {
            flashcard = repository.getAll().find { it.id == id }
        }
    }

    fun toggleFavorite() {
        flashcard?.let {
            val updated = it.copy(isFavorite = !it.isFavorite)
            viewModelScope.launch {
                repository.update(updated)
                flashcard = updated
            }
        }
    }

    fun delete() {
        flashcard?.let {
            viewModelScope.launch {
                repository.delete(it)
                deleted = true
            }
        }
    }
} 
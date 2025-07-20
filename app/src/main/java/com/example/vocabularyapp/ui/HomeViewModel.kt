package com.example.vocabularyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabularyapp.data.model.Flashcard
import com.example.vocabularyapp.data.repository.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FlashcardRepository) : ViewModel() {
    private val _flashcards = MutableStateFlow<List<Flashcard>>(emptyList())
    val flashcards: StateFlow<List<Flashcard>> = _flashcards.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadFlashcards()
    }

    fun loadFlashcards() {
        viewModelScope.launch {
            _flashcards.value = repository.getAll()
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _flashcards.value = if (query.isBlank()) {
                repository.getAll()
            } else {
                repository.search(query)
            }
        }
    }
} 
package com.example.vocabularyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabularyapp.data.model.Flashcard
import com.example.vocabularyapp.data.repository.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: FlashcardRepository) : ViewModel() {
    private val _favorites = MutableStateFlow<List<Flashcard>>(emptyList())
    val favorites: StateFlow<List<Flashcard>> = _favorites.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getFavorites()
        }
    }
} 
package com.example.vocabularyapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabularyapp.data.model.Flashcard
import com.example.vocabularyapp.data.repository.FlashcardRepository
import kotlinx.coroutines.launch

class AddEditViewModel(private val repository: FlashcardRepository) : ViewModel() {
    var word by mutableStateOf("")
    var definition by mutableStateOf("")
    var example by mutableStateOf("")
    var tags by mutableStateOf("") // comma-separated
    var isFavorite by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var saved by mutableStateOf(false)

    fun onSave() {
        if (word.isBlank() || definition.isBlank()) {
            error = "Word and definition cannot be empty."
            return
        }
        viewModelScope.launch {
            repository.insert(
                Flashcard(
                    word = word,
                    definition = definition,
                    example = example.takeIf { it.isNotBlank() },
                    isFavorite = isFavorite,
                    tags = tags.split(",").map { it.trim() }.filter { it.isNotBlank() }
                )
            )
            saved = true
        }
    }
} 
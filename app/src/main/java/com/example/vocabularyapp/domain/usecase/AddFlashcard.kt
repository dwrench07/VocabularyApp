package com.example.vocabularyapp.domain.usecase

import com.example.vocabularyapp.data.model.Flashcard
import com.example.vocabularyapp.data.repository.FlashcardRepository

class AddFlashcard(private val repository: FlashcardRepository) {
    suspend operator fun invoke(flashcard: Flashcard) {
        if (flashcard.word.isBlank() || flashcard.definition.isBlank()) {
            throw IllegalArgumentException("Word and definition cannot be empty.")
        }
        repository.insert(flashcard)
    }
} 
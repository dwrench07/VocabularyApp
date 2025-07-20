package com.example.vocabularyapp.domain.usecase

import com.example.vocabularyapp.data.model.Flashcard
import com.example.vocabularyapp.data.repository.FlashcardRepository

class EditFlashcard(private val repository: FlashcardRepository) {
    suspend operator fun invoke(flashcard: Flashcard) {
        repository.update(flashcard)
    }
} 
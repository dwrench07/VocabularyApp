package com.example.vocabularyapp.domain.usecase

import com.example.vocabularyapp.data.model.Flashcard
import com.example.vocabularyapp.data.repository.FlashcardRepository

class DeleteFlashcard(private val repository: FlashcardRepository) {
    suspend operator fun invoke(flashcard: Flashcard) {
        repository.delete(flashcard)
    }
} 
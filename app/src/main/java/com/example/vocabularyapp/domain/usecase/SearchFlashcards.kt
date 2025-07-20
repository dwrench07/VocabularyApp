package com.example.vocabularyapp.domain.usecase

import com.example.vocabularyapp.data.repository.FlashcardRepository

class SearchFlashcards(private val repository: FlashcardRepository) {
    suspend operator fun invoke(query: String) = repository.search(query)
} 
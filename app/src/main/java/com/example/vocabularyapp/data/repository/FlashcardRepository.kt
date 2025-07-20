package com.example.vocabularyapp.data.repository

import com.example.vocabularyapp.data.local.FlashcardDao
import com.example.vocabularyapp.data.model.Flashcard

class FlashcardRepository(private val dao: FlashcardDao) {
    suspend fun getAll() = dao.getAll()
    suspend fun insert(flashcard: Flashcard) = dao.insert(flashcard)
    suspend fun update(flashcard: Flashcard) = dao.update(flashcard)
    suspend fun delete(flashcard: Flashcard) = dao.delete(flashcard)
    suspend fun getFavorites() = dao.getFavorites()
    suspend fun search(query: String) = dao.search("%$query%")
} 
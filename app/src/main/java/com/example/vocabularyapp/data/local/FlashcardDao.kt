package com.example.vocabularyapp.data.local

import androidx.room.*
import com.example.vocabularyapp.data.model.Flashcard

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcards")
    suspend fun getAll(): List<Flashcard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flashcard: Flashcard)

    @Update
    suspend fun update(flashcard: Flashcard)

    @Delete
    suspend fun delete(flashcard: Flashcard)

    @Query("SELECT * FROM flashcards WHERE isFavorite = 1")
    suspend fun getFavorites(): List<Flashcard>

    @Query("SELECT * FROM flashcards WHERE word LIKE :query OR definition LIKE :query")
    suspend fun search(query: String): List<Flashcard>
} 
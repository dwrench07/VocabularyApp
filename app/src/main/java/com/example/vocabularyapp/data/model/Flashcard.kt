package com.example.vocabularyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class Flashcard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val definition: String,
    val example: String? = null,
    val isFavorite: Boolean = false,
    val tags: List<String> = emptyList()
) 
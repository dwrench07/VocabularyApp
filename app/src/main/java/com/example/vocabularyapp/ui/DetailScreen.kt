package com.example.vocabularyapp.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, cardId: Int, viewModel: DetailViewModel) {
    var flipped by remember { mutableStateOf(false) }
    val flashcard = viewModel.flashcard
    val deleted = viewModel.deleted

    LaunchedEffect(cardId) { viewModel.loadFlashcard(cardId) }
    if (deleted) {
        LaunchedEffect(Unit) { navController.popBackStack() }
    }
    if (flashcard == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...")
        }
        return
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable { flipped = !flipped },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val rotation by animateFloatAsState(if (flipped) 180f else 0f)
                if (!flipped) {
                    Text(flashcard.word, style = MaterialTheme.typography.headlineMedium)
                } else {
                    Text(flashcard.definition, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (!flashcard.example.isNullOrBlank()) {
            Text("Example: ${flashcard.example}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (flashcard.tags.isNotEmpty()) {
            Text("Tags: ${flashcard.tags.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { viewModel.toggleFavorite() }) {
                Icon(
                    imageVector = if (flashcard.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
            Text(if (flashcard.isFavorite) "Favorite" else "Not Favorite")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.navigate(Screen.AddEdit.route) }) {
                Text("Edit")
            }
            Button(onClick = { viewModel.delete() }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                Text("Delete")
            }
        }
    }
} 
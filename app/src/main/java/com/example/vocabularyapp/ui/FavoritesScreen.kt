package com.example.vocabularyapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable

@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel) {
    val favorites by viewModel.favorites.collectAsState()
    LaunchedEffect(Unit) { viewModel.loadFavorites() }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Favorites", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        if (favorites.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No favorite flashcards.")
            }
        } else {
            Column(modifier = Modifier.fillMaxWidth()) {
                favorites.forEach { card ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { navController.navigate(Screen.Detail.createRoute(card.id)) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(card.word, style = MaterialTheme.typography.titleMedium)
                            Text(card.definition, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
} 
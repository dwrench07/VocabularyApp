package com.example.vocabularyapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vocabularyapp.data.model.Flashcard

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val flashcards by viewModel.flashcards.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddEdit.route) }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                label = { Text("Search") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (flashcards.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No flashcards found.")
                }
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    flashcards.forEach { card ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = { navController.navigate(Screen.Detail.createRoute(card.id)) }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(card.word, style = MaterialTheme.typography.titleMedium)
                                Text(card.definition, style = MaterialTheme.typography.bodyMedium)
                                if (!card.example.isNullOrBlank()) {
                                    Text("Example: ${card.example}", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
} 
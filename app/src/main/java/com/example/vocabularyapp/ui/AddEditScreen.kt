package com.example.vocabularyapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddEditScreen(navController: NavController, viewModel: AddEditViewModel) {
    val saved by remember { derivedStateOf { viewModel.saved } }
    if (saved) {
        LaunchedEffect(Unit) { navController.popBackStack() }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        OutlinedTextField(
            value = viewModel.word,
            onValueChange = { viewModel.word = it },
            label = { Text("Word") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.definition,
            onValueChange = { viewModel.definition = it },
            label = { Text("Definition") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.example,
            onValueChange = { viewModel.example = it },
            label = { Text("Example (optional)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.tags,
            onValueChange = { viewModel.tags = it },
            label = { Text("Tags (comma separated)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(
                checked = viewModel.isFavorite,
                onCheckedChange = { viewModel.isFavorite = it }
            )
            Text("Favorite")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.onSave() }, modifier = Modifier.fillMaxWidth()) {
            Text("Save")
        }
        viewModel.error?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
} 
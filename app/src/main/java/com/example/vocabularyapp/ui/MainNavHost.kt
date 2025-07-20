package com.example.vocabularyapp.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vocabularyapp.data.local.AppDatabase
import com.example.vocabularyapp.data.repository.FlashcardRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Composable
fun MainNavHost(navController: NavHostController) {
    val context = LocalContext.current.applicationContext
    val db = AppDatabase.getDatabase(context)
    val repository = FlashcardRepository(db.flashcardDao())
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(repository))
    val addEditViewModel: AddEditViewModel = viewModel(factory = AddEditViewModelFactory(repository))
    val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(repository))
    val favoritesViewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModelFactory(repository))

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController, homeViewModel) }
        composable(Screen.AddEdit.route) { AddEditScreen(navController, addEditViewModel) }
        composable(Screen.Favorites.route) { FavoritesScreen(navController, favoritesViewModel) }
        composable(Screen.Detail.route) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()
            if (cardId != null) {
                DetailScreen(navController, cardId, detailViewModel)
            }
        }
    }
}

class HomeViewModelFactory(private val repository: FlashcardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AddEditViewModelFactory(private val repository: FlashcardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DetailViewModelFactory(private val repository: FlashcardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class FavoritesViewModelFactory(private val repository: FlashcardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 
package com.example.retrofitrx.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitrx.domain.ProjectsRepo

// урок 5 01:12:56

// метод для передачи аргумента
class ReposViewModelFactory(private val repo: ProjectsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReposViewModel(repo) as T
    }
}
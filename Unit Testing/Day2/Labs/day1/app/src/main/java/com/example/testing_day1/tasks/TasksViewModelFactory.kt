package com.example.testing_day1.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testing_day1.data.source.IDefaultTasksRepository

@Suppress("UnChecked_Cast")
class TasksViewModelFactory (var repo : IDefaultTasksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(repo) as T
    }



}
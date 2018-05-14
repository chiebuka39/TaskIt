package com.harrric.chiebuka.taskit

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.harrric.chiebuka.taskit.ViewModel.TaskViewModel
import com.harrric.chiebuka.taskit.data.database.repository.TaskRepository

/**
 * Created by chiebuka on 5/11/18.
 */
class TaskViewModelFactory(val repository : TaskRepository)
    : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(repository) as T
    }
}
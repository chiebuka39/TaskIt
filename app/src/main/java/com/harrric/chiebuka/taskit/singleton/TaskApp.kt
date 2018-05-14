package com.harrric.chiebuka.taskit.singleton

import android.app.Application
import com.harrric.chiebuka.taskit.ViewModel.TaskViewModel
import com.harrric.chiebuka.taskit.data.database.TaskDatabase
import com.harrric.chiebuka.taskit.data.database.repository.TaskRepository
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

//Koin modules will fetch & inject the dependencies for each class
val koinModule : Module = applicationContext {
    viewModel { TaskViewModel(get()) }
    bean { TaskDatabase.getInstance(get()) }
    bean { TaskRepository(get()) }
}

class TaskApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(koinModule))
    }
}
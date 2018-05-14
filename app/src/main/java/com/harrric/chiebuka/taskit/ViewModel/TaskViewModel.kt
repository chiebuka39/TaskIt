package com.harrric.chiebuka.taskit.ViewModel

import android.arch.lifecycle.ViewModel
import com.harrric.chiebuka.taskit.data.database.models.TaskEntry
import com.harrric.chiebuka.taskit.data.database.repository.TaskRepository

import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * Created by chiebuka on 5/11/18.
 */
class TaskViewModel(val repository: TaskRepository) : ViewModel() {



    /**
     * Get the user name of the user.

     * @return a [Flowable] that will emit every time the user name has been updated.
     */
    // for every emission of the user, get the user name
    fun tasks(): Flowable<List<TaskEntry>> {
        return repository.getTasks()
    }

    /**
     * Update the user name.
     * @param userName the new user name
     * *
     * @return a [Completable] that completes when the user name is updated
     */
    fun addTask(task: TaskEntry): Completable {
        return Completable.fromAction {
            repository.addTask(task)
        }
    }


}
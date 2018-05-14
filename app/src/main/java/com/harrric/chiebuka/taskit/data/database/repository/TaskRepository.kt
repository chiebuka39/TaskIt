package com.harrric.chiebuka.taskit.data.database.repository

import android.arch.lifecycle.LiveData
import com.harrric.chiebuka.taskit.AppExecutors
import com.harrric.chiebuka.taskit.data.database.TaskDatabase
import com.harrric.chiebuka.taskit.data.database.dao.TaskDao
import com.harrric.chiebuka.taskit.data.database.models.TaskEntry
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by chiebuka on 5/11/18.
 */
class TaskRepository(val db: TaskDatabase){


    fun getTasks(): Flowable<List<TaskEntry>> {
        return db.taskDao().getAllTasks()
    }


    fun addTask(task: TaskEntry) {
            db.taskDao().insert(task)
    }
}


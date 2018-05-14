package com.harrric.chiebuka.taskit.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.harrric.chiebuka.taskit.data.database.models.TaskEntry
import io.reactivex.Flowable

/**
 * Created by chiebuka on 5/4/18.
 */

@Dao
interface TaskDao {

    @Query("SELECT * FROM taskData")
    fun getAllTasks() : Flowable<List<TaskEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taskEntry: TaskEntry)

    @Query("SELECT * FROM taskData WHERE id = :id")
    fun getTaskById(id : Long) : Flowable<TaskEntry>
}
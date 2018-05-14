package com.harrric.chiebuka.taskit.data.database.dao

import android.arch.persistence.room.*
import com.harrric.chiebuka.taskit.data.database.models.SubTaskEntry
import io.reactivex.Flowable

/**
 * Created by chiebuka on 5/9/18.
 */


@Dao
interface SubTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subTaskEntry: SubTaskEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update (vararg subTaskEntry: SubTaskEntry)

    @Delete
    fun delete(vararg subTaskEntry: SubTaskEntry)

    @Query("SELECT * FROM subTaskData WHERE taskId = :taskId")
    fun getSubTaskForTask(taskId :Long) : Flowable<List<SubTaskEntry>>

    @Query("SELECT * FROM subTaskData WHERE id = :id")
    fun getSubTaskById(id :Long) : Flowable<SubTaskEntry>

    @Query("DELETE FROM subTaskData")
    fun deleteAllSubTasksForATask()


}
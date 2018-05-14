package com.harrric.chiebuka.taskit.data.database.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by chiebuka on 5/9/18.
 */

@Entity(tableName = "subTaskData", foreignKeys = arrayOf(ForeignKey(entity = TaskEntry::class,
        parentColumns = arrayOf("id"), childColumns = arrayOf("taskId"))))
class SubTaskEntry(@PrimaryKey(autoGenerate = true) var id: Long?,
                   @ColumnInfo(name = "taskId") var taskId: Long?,
                   @ColumnInfo(name = "taskTitle") var taskTitle: String,
                   @ColumnInfo(name="dateCreated") var dateCreated: Date,
                   @ColumnInfo(name = "dateDue") var dateDue: Date,
                   @ColumnInfo(name = "taskCompleted") var taskCompleted: Boolean,
                   @ColumnInfo(name = "taskPriority") var taskPriority: Int) {
}
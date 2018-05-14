package com.harrric.chiebuka.taskit.data.database.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by chiebuka on 5/4/18.
 */

@Entity(tableName = "taskData")
data class TaskEntry(@PrimaryKey(autoGenerate = true) var id: Long?,
                     @ColumnInfo(name = "taskTitle") var taskTitle: String,
                     @ColumnInfo(name="dateCreated") var dateCreated: Date,
                     @ColumnInfo(name = "dateDue") var dateDue: Date,
                     @ColumnInfo(name = "taskCompleted") var taskCompleted: Boolean,
                     @ColumnInfo(name = "taskPriority") var taskPriority: Int) {
    @Ignore
    constructor():this(null,"", Date(), Date(),false,0)
}
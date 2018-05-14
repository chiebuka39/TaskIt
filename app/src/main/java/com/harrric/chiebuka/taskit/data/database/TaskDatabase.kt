package com.harrric.chiebuka.taskit.data.database

import android.arch.persistence.room.*
import android.content.Context
import com.harrric.chiebuka.taskit.data.database.dao.SubTaskDao
import com.harrric.chiebuka.taskit.data.database.dao.TaskDao
import com.harrric.chiebuka.taskit.data.database.models.SubTaskEntry
import com.harrric.chiebuka.taskit.data.database.models.TaskEntry

/**
 * Created by chiebuka on 5/4/18.
 */


@Database(entities = arrayOf(TaskEntry::class, SubTaskEntry::class),version = 1)
@TypeConverters(DateConverter::class)
abstract class TaskDatabase: RoomDatabase() {



    abstract fun taskDao() : TaskDao
    abstract fun subTaskDao() : SubTaskDao

    companion object {
        var DATABASE_NAME = "tasks.db"

        @Volatile private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        TaskDatabase::class.java, "Sample.db")
                        .build()

    }
}
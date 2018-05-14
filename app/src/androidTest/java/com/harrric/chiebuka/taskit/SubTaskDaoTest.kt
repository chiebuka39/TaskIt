package com.harrric.chiebuka.taskit

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.harrric.chiebuka.taskit.data.database.models.SubTaskEntry
import com.harrric.chiebuka.taskit.data.database.TaskDatabase
import com.harrric.chiebuka.taskit.data.database.models.TaskEntry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by chiebuka on 5/4/18.
 */

@RunWith(AndroidJUnit4::class)
class SubTaskDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mDatabase : TaskDatabase


    @Before
    @Throws(Exception::class)
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TaskDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    fun onFetchingSubTasks_shouldGetEmptyList_IfTable_IsEmpty() {
        mDatabase.subTaskDao().getSubTaskById(1)
                .test()
                .assertNoValues()
        //Assert.
    }

    @Test
    fun insertAndSubTask() {
        // When inserting a new user in the data source
        mDatabase.taskDao().insert(TASK)
        mDatabase.subTaskDao().insert(SUBTASK)

        // When subscribing to the emissions of the user
        SUBTASK.id?.let {
            mDatabase.subTaskDao().getSubTaskById(1)
                .test()
                // assertValue asserts that there was only one emission of the user
                .assertValue { it.id == SUBTASK.id && it.taskTitle == "regiser at the school" }
        }
    }

    @Test
    fun insertAndCountTask() {
        // When inserting a new user in the data source
        mDatabase.taskDao().insert(TASK)
        mDatabase.subTaskDao().insert(SUBTASK)
        mDatabase.subTaskDao().insert(SUBTASK_2)

        // When subscribing to the emissions of the user

        mDatabase.subTaskDao().getSubTaskForTask(1)
                .test().assertValue{ it.size == 2 }
                // assertValue asserts that there was only one emission of the user

    }

    @Test fun updateAndGetUser() {
        // Given that we have a SubTask in the data source
        mDatabase.taskDao().insert(TASK)
        mDatabase.subTaskDao().insert(SUBTASK)

        // When we are updating the name of the user
        val updatedSubTaskEntry = SubTaskEntry(SUBTASK.id, 1, "meet the principal", Date(), Date(), false, 1)
        mDatabase.subTaskDao().insert(updatedSubTaskEntry)

        // When subscribing to the emissions of the user
        mDatabase.subTaskDao().getSubTaskById(SUBTASK.id!!)
                .test()
                // assertValue asserts that there was only one emission of the user
                .assertValue { it.id == SUBTASK.id && it.taskTitle == "meet the principal" }
    }

    @Test fun deleteAndGetUser() {
        // Given that we have a user in the data source
        mDatabase.taskDao().insert(TASK)
        mDatabase.subTaskDao().insert(SUBTASK)
        mDatabase.subTaskDao().insert(SUBTASK_2)

        //When we are deleting all users
        mDatabase.subTaskDao().deleteAllSubTasksForATask()
        // When subscribing to the emissions of the user
        mDatabase.subTaskDao().getSubTaskForTask(1)
                .test().assertValue{it.size == 0}
                // check that there's no user emitted

    }

    companion object {
        private val TASK = TaskEntry(1, "Go to school", Date(), Date(), false, 1)

        private val SUBTASK = SubTaskEntry(1, 1, "regiser at the school", Date(), Date(), false, 1)
        private val SUBTASK_2 = SubTaskEntry(2, 1, "pay school fees", Date(), Date(), false, 1)
    }

}
package com.harrric.chiebuka.taskit

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.harrric.chiebuka.taskit.data.database.TaskDatabase
import com.harrric.chiebuka.taskit.data.database.models.TaskEntry
import org.junit.*
import org.junit.runner.RunWith
import java.util.*


/**
 * Created by chiebuka on 5/4/18.
 */

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

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
    fun onFetchingTasks_shouldGetEmptyList_IfTable_IsEmpty() {
        mDatabase.taskDao().getTaskById(1)
                .test()
                .assertNoValues()
        //Assert.
    }

    @Test fun insertAndGetTask() {
        // When inserting a new user in the data source
        mDatabase.taskDao().insert(USER)

        // When subscribing to the emissions of the user
        USER.id?.let {
            mDatabase.taskDao().getTaskById(it)
                .test()
                // assertValue asserts that there was only one emission of the user
                .assertValue { it.id == USER.id && it.taskTitle == "harry" }
        }
    }

    companion object {
        private val USER = TaskEntry(1, "Go to school", Date(), Date(), false, 1)
    }

}
package com.harrric.chiebuka.taskit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.harrric.chiebuka.taskit.ViewModel.TaskViewModel
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.harrric.chiebuka.taskit.data.database.TaskDatabase

import kotlinx.android.synthetic.main.activity_main.*
import com.harrric.chiebuka.taskit.data.database.models.TaskEntry
import com.harrric.chiebuka.taskit.data.database.repository.TaskRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.architecture.ext.viewModel

import java.util.*


class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: TaskViewModel by viewModel()

    lateinit var repository : TaskRepository

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        //addTask()
        getTasks()
    }


    private fun addTask() {

        val task = TaskEntry(1,"Go to harmony", Date(), Date(),false, 1)
        disposable.add(mainActivityViewModel.addTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.v("Harry", "task added")  },
                        { error -> Log.e("Harry", "Unable to update username", error) }))
    }

    private fun getTasks(){
        disposable.add(mainActivityViewModel.tasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    harry.text = it.first().taskTitle
                    Log.v("Harry", it.get(0).dateCreated.toString()) },
                        { error -> Log.e("Harry_error", "Unable to get username", error) }))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

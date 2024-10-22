package com.example.testing_day1.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing_day1.MyTestRule
import com.example.testing_day1.data.Result
import com.example.testing_day1.data.Task
import com.example.testing_day1.data.succeeded
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)


class TasksLocalDataSourceTest {


    @get:Rule
    val myInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var myTestRule = MyTestRule()
    lateinit var DB : ToDoDatabase
    lateinit var mydao : TasksDao
    lateinit var mylocalsrc : TasksLocalDataSource


    @Before
    fun setup()
    {
        DB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ToDoDatabase::class.java

        ).allowMainThreadQueries() //never use in production
            .build()

        mydao = DB.taskDao()
        mylocalsrc = TasksLocalDataSource(DB.taskDao(),myTestRule.getDispatcher())
    }

    @Test
    fun saveTask_retrievesTaskById() =runTest {
        val task = Task("title", "desc", false)
        mylocalsrc.saveTask(task)

        val result = mylocalsrc.getTask(task.id)

        assertThat(result.succeeded, `is`(true))
        result as Result.Success

        assertThat(result.data.title, `is`("title"))
        assertThat(result.data.description, `is`("desc"))
        assertThat(result.data.isCompleted, `is`(false))
        //assertThat(result as Task, notNullValue())
        //assertThat(result, IsEqual(result))

    }

    @Test
    fun updateTaskAndGetById() = runTest {

        val task1 = Task(id = "1", title = "Original Task", description = "Original Description", isCompleted = false)
        mylocalsrc.saveTask(task1)


        val task2 = Task(id = task1.id, title = "Updated Task", description = "Updated Description", isCompleted = true)
        mylocalsrc.saveTask(task2)


        val result = mylocalsrc.getTask(task2.id)


        if (result is Result.Success) {
            val task = result.data
            assertThat(task.id, IsEqual(task2.id))
            assertThat(task.title, IsEqual(task2.title))
            assertThat(task.description, IsEqual(task2.description))
            assertThat(task.isCompleted, IsEqual(task2.isCompleted))
        }
    }

    @Test
    fun completeTask_retrievedTaskIsComplete() = runTest {
        // Step 1: Create and insert a task
        val task = Task(id = "1", title = "Task to Complete", description = "Description of the task", isCompleted = false)
        mylocalsrc.saveTask(task)

        // Step 2: Complete the task
        mylocalsrc.completeTask(task.id)

        val result = mylocalsrc.getTask(task.id)


        if (result is Result.Success) {
            val task = result.data
            //assertThat(task.id, IsEqual(task2.id))
            //assertThat(task.title, IsEqual(task2.title))
            //assertThat(task.description, IsEqual(task2.description))
            assertThat(task.isCompleted, IsEqual(true))
        }
    }


}


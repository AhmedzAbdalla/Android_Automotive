package com.example.testing_day1.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing_day1.data.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.matchers.Equals


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class testdaolocal {


    @get:Rule
    val myInstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var DB : ToDoDatabase
    lateinit var mydao : TasksDao

    @Before
    fun setup()
    {
        DB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ToDoDatabase::class.java

        ).allowMainThreadQueries() //never use in production
            .build()

        mydao = DB.taskDao()
    }

    @Test
    fun getTaskByID_task1_returnSameTask()= runTest {
        //given
        val task = Task("test task")
        mydao.insertTask(task)
        //when
        val rreturn = mydao.getTaskById(task.id)
        //then
        assertThat(rreturn as Task, notNullValue())
        assertThat(rreturn, IsEqual(task))

    }

    @Test
    fun updateTaskAndGetById() = runTest {

        val task1 = Task(id = "1", title = "Original Task", description = "Original Description", isCompleted = false)
        mydao.insertTask(task1)


        val task2 = Task(id = task1.id, title = "Updated Task", description = "Updated Description", isCompleted = true)
        mydao.updateTask(task2)


        val result = mydao.getTaskById(task2.id)


        assertThat(result, IsEqual(task2))
    }



    @After
    fun teardown() = DB.close()
}
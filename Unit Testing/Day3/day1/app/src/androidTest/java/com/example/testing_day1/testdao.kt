package com.example.testing_day1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.testing_day1.data.Task
import com.example.testing_day1.data.source.local.TasksDao
import com.example.testing_day1.data.source.local.ToDoDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.jetbrains.annotations.ApiStatus.Experimental
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest




class testdao {


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
        assertThat(rreturn, IsEqual(rreturn))

    }



    @After
    fun teardown() = DB.close()

}
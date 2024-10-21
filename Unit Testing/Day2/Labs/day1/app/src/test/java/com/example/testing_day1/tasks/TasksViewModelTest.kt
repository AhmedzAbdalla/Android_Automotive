package com.example.testing_day1.tasks

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import getOrAwaitValue
import com.example.testing_day1.R
import com.example.testing_day1.data.source.IDefaultTasksRepository
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    lateinit var tasksViewModel : TasksViewModel
    lateinit var repo : IDefaultTasksRepository

     fun setUp(){
        repo =  FakeTakasRepository()
        tasksViewModel = TasksViewModel(repo)
    }

    @Test
    fun addNewTask_newTaskEventIsNotNull() {

        setUp()

        tasksViewModel.addNewTask()

        val result = tasksViewModel.newTaskEvent.getOrAwaitValue()

        assertThat(result, not(nullValue()))
    }

    @Test
    fun setFiltering_allTasks_setsAllTasksFilter() {

        setUp()

        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        assertThat(tasksViewModel.currentFilteringLabel.getOrAwaitValue(), `is`(R.string.label_all))
        assertThat(tasksViewModel.noTasksLabel.getOrAwaitValue(), `is`(R.string.no_tasks_all))
        assertThat(tasksViewModel.noTaskIconRes.getOrAwaitValue(), `is`(R.drawable.logo_no_fill))
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

    @Test
    fun setFiltering_activeTasks_setsActiveTasksFilter() {

        setUp()

        tasksViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS)

        assertThat(tasksViewModel.currentFilteringLabel.getOrAwaitValue(), `is`(R.string.label_active))
        assertThat(tasksViewModel.noTasksLabel.getOrAwaitValue(), `is`(R.string.no_tasks_active))
        assertThat(tasksViewModel.noTaskIconRes.getOrAwaitValue(), `is`(R.drawable.ic_check_circle_96dp))
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(false))
    }

    @Test
    fun setFiltering_completedTasks_setsCompletedTasksFilter() {

        setUp()

        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)

        assertThat(tasksViewModel.currentFilteringLabel.getOrAwaitValue(), `is`(R.string.label_completed))
        assertThat(tasksViewModel.noTasksLabel.getOrAwaitValue(), `is`(R.string.no_tasks_completed))
        assertThat(tasksViewModel.noTaskIconRes.getOrAwaitValue(), `is`(R.drawable.ic_verified_user_96dp))
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(false))
    }
}

package com.example.testing_day1.statistics

import com.example.testing_day1.data.Task
import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


class StatisticsUtilsTest{

    @Test
    fun getActiveAndCompletedStatus_noCompletedList_100Active0Completed() {

        val tasks = listOf(
            Task("task1", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(100f, result.activeTasksPercent)
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStatus_listWith2CompletedAnd3Active_60Active40Completed() {

        val tasks = listOf(
            Task("task1", isCompleted = false),
            Task("task2", isCompleted = false),
            Task("task3", isCompleted = false),
            Task("task4", isCompleted = true),
            Task("task5", isCompleted = true)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(60f, result.activeTasksPercent)
        assertThat(result.completedTasksPercent, `is`(40f))
    }

    @Test
    fun getActiveAndCompletedStatus_emptyList_0Active0Completed() {
        val tasks = emptyList<Task>()

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.activeTasksPercent)  // JUnit
        assertThat(result.completedTasksPercent, `is`(0f))  // Hamcrest
    }

    @Test
    fun getActiveAndCompletedStatus_nullList_0Active0Completed() {
        val tasks: List<Task>? = null

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.activeTasksPercent)  // JUnit
        assertThat(result.completedTasksPercent, `is`(0f))  // Hamcrest
    }


}
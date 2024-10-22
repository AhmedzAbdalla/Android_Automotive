package com.example.testing_day1


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.runner.Description
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher

class MyTestRule(
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    val scope = TestCoroutineScope(dispatcher)

    // Getter for the dispatcher
    fun getDispatcher(): TestCoroutineDispatcher {
        return dispatcher
    }

    override fun starting(description: Description?) {
        super.starting(description)
        // Set the dispatcher to be used as the main dispatcher
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        // Clean up after test execution
        scope.cleanupTestCoroutines()
       Dispatchers.resetMain()
    }
}

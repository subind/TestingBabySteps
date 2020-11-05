package com.bridgetree.testingbabysteps

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * If we don't use this class, it will result in the foll exception -
 * IllegalStateException: Module with main dispatcher had failed to initialize
 *
 * Reason is that suspend calls use 'main-dispatcher' which is not available in tests,
 * because coroutine main-dispatcher relies on main-looper which is only available in real app scenario
 *
 * Here we are in the test source-set which runs in JVM
 */

@ExperimentalCoroutinesApi
class MainCoroutineRule(
        private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}
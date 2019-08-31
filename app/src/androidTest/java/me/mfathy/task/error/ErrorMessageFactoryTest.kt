package me.mfathy.task.error

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import me.mfathy.task.R
import me.mfathy.task.data.exceptions.NetworkException
import me.mfathy.task.data.exceptions.NotFoundException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Mohammed Fathy on 22/12/2018.
 * dev.mfathy@gmail.com
 *
 * Instrumentation test for ErrorMessageFactoryTest.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class ErrorMessageFactoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testNetworkConnectionErrorMessage() {
        val expectedMessage = context.resources.getString(R.string.exception_message_no_connection)
        val actualMessage = ErrorMessageFactory.create(context,
            NetworkException()
        )

        assertEquals(actualMessage, expectedMessage)
    }

    @Test
    fun testNotFoundExceptionErrorMessage() {
        val expectedMessage = context.resources.getString(R.string.exception_message_no_items)
        val actualMessage = ErrorMessageFactory.create(context,
            NotFoundException()
        )

        assertEquals(actualMessage, expectedMessage)
    }
}
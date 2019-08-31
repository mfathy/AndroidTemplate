package me.mfathy.task.error

import android.content.Context
import me.mfathy.task.R
import me.mfathy.task.data.exceptions.NetworkException
import me.mfathy.task.data.exceptions.NotFoundException
import retrofit2.HttpException

/**
 * Created by Mohammed Fathy on 31/08/2018.
 * dev.mfathy@gmail.com
 *
 * Factory used to create error messages from an Exception as a condition.
 */
object ErrorMessageFactory {

    /**
     * Creates a String representing an error message.
     *
     * @param context Android context.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return [String] an error message.
     */
    fun create(context: Context, exception: Throwable): String {
        var message = context.resources.getString(R.string.exception_message_generic)

        when (exception) {
            is NetworkException -> message = context.resources.getString(R.string.exception_message_no_connection)
            is NotFoundException -> message = context.resources.getString(R.string.exception_message_no_items)
            is HttpException -> when (exception.code()) {
                401 -> context.resources.getString(R.string.exception_message_auth_error)
                500 -> context.resources.getString(R.string.exception_message_generic)
                else -> context.resources.getString(R.string.exception_message_generic)
            }
        }
        return message
    }
}

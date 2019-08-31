package me.mfathy.task.features.state

import me.mfathy.task.data.models.Page

sealed class PageResult {

    data class OnSuccess(val data: Page) : PageResult()
    data class OnFailure(val error: Throwable) : PageResult()
    object OnLoading : PageResult()

}

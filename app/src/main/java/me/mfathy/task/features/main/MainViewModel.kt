package me.mfathy.task.features.main

import androidx.lifecycle.MutableLiveData
import me.mfathy.task.features.base.BaseViewModel
import me.mfathy.task.features.state.PageResult
import me.mfathy.task.idlingResource.SimpleIdlingResource
import me.mfathy.task.interactors.login.LoginUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    //  Save activity state
    var nextPageKey: String = ""
    var searchKeyword = ""

    private val doctorsLiveData = MutableLiveData<PageResult>()

    fun getLiveData(): MutableLiveData<PageResult> = doctorsLiveData

    /**
     * Idling resource for espresso testing.
     */
    private lateinit var mIdlingResource: SimpleIdlingResource

    fun setIdlingResource(idlingResource: SimpleIdlingResource) {
        this.mIdlingResource = idlingResource
    }
}

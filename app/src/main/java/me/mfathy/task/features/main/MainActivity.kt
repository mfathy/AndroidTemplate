package me.mfathy.task.features.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import me.mfathy.task.BuildConfig
import me.mfathy.task.R
import me.mfathy.task.data.models.Page
import me.mfathy.task.error.ErrorMessageFactory
import me.mfathy.task.extensions.hideSoftKeyboard
import me.mfathy.task.features.base.widgets.AbstractPagination
import me.mfathy.task.features.state.PageResult
import me.mfathy.task.idlingResource.SimpleIdlingResource
import me.mfathy.task.injection.factory.ViewModelFactory
import javax.inject.Inject


class MainActivity : BaseLocationActivity() {

    private var mListState: Parcelable? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var scrollListener: AbstractPagination

    private var requestOnWay = false

    // The Idling Resource which will be null in production.
    private lateinit var mIdlingResource: SimpleIdlingResource

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureSearchInputListeners()

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        if (BuildConfig.DEBUG) {
            viewModel.setIdlingResource(getIdlingResource())
        }

        initViews()

        observeDoctorsLiveData()
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        // Save list state
        mListState = layoutManager.onSaveInstanceState()
        state.putParcelable(LIST_STATE_KEY, mListState)
    }

    override fun onRestoreInstanceState(state: Bundle?) {
        super.onRestoreInstanceState(state)
        // Retrieve list state and list/item positions
        if (state != null)
            mListState = state.getParcelable(LIST_STATE_KEY)
    }

    private fun observeDoctorsLiveData() {
        viewModel.getLiveData().observe(this, Observer {
            it?.let { result -> handleDoctorsList(result) }
        })
    }

    private fun handleDoctorsList(result: PageResult) {
        swipe_refresh.isRefreshing = false
        requestOnWay = false

        when (result) {
            is PageResult.OnSuccess -> {
                handleSuccess(result.data)
            }
            is PageResult.OnFailure -> {
                handleFailure(result.error)
            }

            is PageResult.OnLoading -> {
                updateViewVisibility(
                    showLoading = true
                )
                requestOnWay = true
            }
        }
    }

    private fun handleSuccess(data: Page) {
        data.let { page ->
            if (scrollListener.getCurrentPageNumber() == 0 && page.pageNumber == 0) {
                textView_callToAction.text =
                    getString(R.string.exception_message_no_items)
                updateViewVisibility(
                    showError = true
                )

                return
            }

            recyclerAdapter.appendItems(emptyList())
            updateViewVisibility(
                showData = true
            )
        }

    }

    private fun handleFailure(error: Throwable) {
        textView_callToAction.text = ErrorMessageFactory.create(this, error)

        updateViewVisibility(
            showError = true
        )
    }

    private fun initViews() {

        layoutManager = LinearLayoutManager(this)
        recyclerAdapter =
            RecyclerAdapter(this, mutableListOf())

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerAdapter

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        swipe_refresh.setOnRefreshListener {
            recyclerAdapter.resetItems()
//            viewModel.getPagination().onNext(makeRequestParams(viewModel.searchKeyword))
        }

        scrollListener = object : AbstractPagination(layoutManager) {
            override fun onLoadMore(currentPage: Int, totalItemCount: Int, view: View) {
                updateViewVisibility(showData = true, showLoading = true)
                if (!requestOnWay) {
//                    viewModel.getPagination().onNext(makeRequestParams(viewModel.searchKeyword))
                }
            }
        }

        recyclerView.addOnScrollListener(scrollListener)
        recyclerView.setHasFixedSize(true)


        //  Subscribe to pagination to receive updates
//        viewModel.startPagination()

    }

    private fun updateViewVisibility(
        showData: Boolean = false,
        showError: Boolean = false,
        showLoading: Boolean = false
    ) {

        swipe_refresh.visibility = if (showData) View.VISIBLE else View.INVISIBLE
        recyclerView.visibility = if (showData) View.VISIBLE else View.INVISIBLE

        textView_callToAction.visibility =
            if (showError) View.VISIBLE else View.INVISIBLE
        progressBar_loading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun configureSearchInputListeners() {
        val searchEditText = findViewById<EditText>(R.id.editTextSearch)
        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchKeyword = v?.text.toString()

                if (viewModel.searchKeyword.isBlank()) {
                    searchEditText.error = getString(R.string.error_please_enter_search_keywork)
                } else {
                    searchEditText.error = null
//                    viewModel.getPagination().onNext(makeRequestParams(viewModel.searchKeyword))
                    recyclerAdapter.resetItems()
                    hideSoftKeyboard()
                }
            }
            false
        }

        findViewById<Button>(R.id.buttonSearch).setOnClickListener {
            searchEditText.text?.toString()?.let {
                viewModel.searchKeyword = it

                if (viewModel.searchKeyword.isBlank()) {
                    searchEditText.error = getString(R.string.error_please_enter_search_keywork)
                } else {
                    searchEditText.error = null
//                    viewModel.getPagination().onNext(makeRequestParams(viewModel.searchKeyword))
                    recyclerAdapter.resetItems()
                    hideSoftKeyboard()
                }
            }
        }
    }

    private fun makeRequestParams(searchKeyword: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val LIST_STATE_KEY = "LIST_STATE_KEY"
    }

    /**
     * Only called from test, creates and returns a new [SimpleIdlingResource].
     */
    @VisibleForTesting
    fun getIdlingResource(): SimpleIdlingResource {
        if (!::mIdlingResource.isInitialized) {
            mIdlingResource = SimpleIdlingResource()
        }
        return mIdlingResource
    }
}

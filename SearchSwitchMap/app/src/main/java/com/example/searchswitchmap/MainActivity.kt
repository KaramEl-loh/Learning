package com.example.searchswitchmap

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchswitchmap.Providers.GithubRepositoryProvider
import com.karam.searchswitchmap.GithubRepositoryAdapter
import com.karam.searchswitchmap.Providers.ObservablesProvider
import com.karam.searchswitchmap.model.GithubResponse
import com.karam.searchswitchmap.model.SearchResult
import com.karam.searchswitchmap.utils.Emoji
import com.karam.searchswitchmap.utils.log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var repoList: MutableList<SearchResult>
    private lateinit var searchField: EditText
    private lateinit var searchFieldObservable: Observable<GithubResponse>
    private lateinit var banner: Banner

    private val compositeDisposable = CompositeDisposable()
    private val githubRepositoryProvider = GithubRepositoryProvider()
    private val observablesProvider = ObservablesProvider(githubRepositoryProvider)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repoList = mutableListOf()
        searchField = findViewById(R.id.search_bar)
        banner = Banner(this, banner_container)

        searchFieldObservable = searchField.editTextObservable
            .filter { !it.isNullOrBlank() }
            .switchMap { queryString ->
                observablesProvider.getSearchObservableOffline(queryString)
                    .observeOn(AndroidSchedulers.mainThread())
                    .log(Emoji.BaseBall)
            }
    }

    override fun onResume() {
        super.onResume()
        recyclerView = findViewById(R.id.githubrepo_recyclerview)

        compositeDisposable.add(
            searchFieldObservable.subscribeBy(onNext = this::handleSuccess, onError = this::handleError)
        )
    }

    private fun handleSuccess(response: GithubResponse) {
        repoList = response.items
        val adapter = GithubRepositoryAdapter(repoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        banner.showBanner()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


}



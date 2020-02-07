package com.karam.searchswitchmap

import androidx.lifecycle.ViewModel
import com.karam.searchswitchmap.model.GithubResponse
import com.karam.searchswitchmap.Providers.GithubRepositoryProvider
import com.karam.searchswitchmap.Providers.ObservablesProvider
import com.karam.searchswitchmap.model.SearchResult
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class GithubSearchViewModel : ViewModel()  {


    //todo:Dependency injection using dagger
    private val githubRepositoryProvider = GithubRepositoryProvider()
    private val observablesProvider = ObservablesProvider(githubRepositoryProvider)
    private var repoList = mutableListOf<SearchResult>()
    private val compositeDisposable = CompositeDisposable()

    var adapter = GithubRepositoryAdapter(repoList)

    fun getRepositoryList(text: CharSequence) {

        compositeDisposable.add(Observable.just(text).filter { !it.isNullOrBlank() }
            .switchMap { observablesProvider.getSearchObservable(it) }
            .subscribeBy(onNext = ::handleSuccess, onError = ::handleError))
    }


    private fun handleSuccess(response: GithubResponse) {

        adapter.addItems(response.items)
    }

    private fun handleError(throwable: Throwable) {

        throwable.printStackTrace()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}













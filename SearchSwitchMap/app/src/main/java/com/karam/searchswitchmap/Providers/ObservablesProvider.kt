package com.karam.searchswitchmap.Providers

import com.karam.searchswitchmap.model.GithubResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ObservablesProvider(private var githubRepositoryProvider: GithubRepositoryProvider) {


    fun getSearchObservable(repoName: CharSequence): Observable<GithubResponse> {


        return githubRepositoryProvider.getRepositories(repoName)
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }


}
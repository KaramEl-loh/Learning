package com.example.searchswitchmap.Providers

import com.karam.searchswitchmap.model.GithubResponse
import com.karam.searchswitchmap.Modules.RetrofitModule
import com.karam.searchswitchmap.Services.GithubRepositoryService
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@Module
class GithubRepositoryProvider {

    private val retrofit = RetrofitModule().getRetrofitClient().create(GithubRepositoryService::class.java)

    @Provides
    fun getRepositories(repoName: CharSequence): Observable<GithubResponse> {
        return retrofit.getRepositories(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
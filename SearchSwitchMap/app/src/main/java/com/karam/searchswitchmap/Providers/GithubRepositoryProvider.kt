package com.karam.searchswitchmap.Providers

import com.karam.searchswitchmap.model.GithubResponse
import com.karam.searchswitchmap.Modules.RetrofitModule
import com.karam.searchswitchmap.Services.GithubRepositoryService
import io.reactivex.Observable


class GithubRepositoryProvider {

    private val retrofit = RetrofitModule().getRetrofitClient().create(GithubRepositoryService::class.java)

    fun getRepositories(repoName:CharSequence): Observable<GithubResponse> {
        return retrofit.getRepositories(repoName)
    }







}
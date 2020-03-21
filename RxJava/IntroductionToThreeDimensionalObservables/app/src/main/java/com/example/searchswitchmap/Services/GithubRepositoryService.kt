package com.karam.searchswitchmap.Services

import com.karam.searchswitchmap.model.GithubResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubRepositoryService {


    @GET("repositories")
    fun getRepositories(@Query("q") repositoryName: CharSequence): Observable<GithubResponse>


}
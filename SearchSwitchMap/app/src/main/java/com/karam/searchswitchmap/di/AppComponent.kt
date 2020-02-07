package com.karam.searchswitchmap.di

import com.karam.searchswitchmap.Providers.GithubRepositoryProvider
import dagger.Component


@Component(modules = [GithubRepositoryProvider::class])
interface AppComponent  {


}
package com.karam.searchswitchmap

import androidx.lifecycle.ViewModel
import com.karam.searchswitchmap.model.GithubResponse
import com.karam.searchswitchmap.Providers.GithubRepositoryProvider
import com.karam.searchswitchmap.Providers.ObservablesProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy


class GithubSearchViewModel  : ViewModel() {

        private val githubRepositoryProvider = GithubRepositoryProvider()
        private val observablesProvider = ObservablesProvider(githubRepositoryProvider)

        fun getRepositoryList(text:CharSequence,start:Int,count:Int,after:Int) {


                Observable.just(text).filter { !it.isNullOrBlank() }
                        .switchMap { observablesProvider.getSearchObservable(it) }
                        .subscribeBy(onNext = ::handleSuccess,onError = ::handleError)
        }


        private fun handleSuccess(response: GithubResponse){



                println(response.items)

//                Log.i("RESPONSE","$response")
//

//                adapter = GithubRepositoryAdapter(this,response.items)
//                recyclerView?.layoutManager = LinearLayoutManager(this)
//                recyclerView?.adapter = adapter


        }

        private fun handleError(throwable: Throwable) {

                throwable.printStackTrace()
//                Toast.makeText(this,"An error has occurred", Toast.LENGTH_LONG).show()
//                Log.d("POTATO","${throwable}")
//
//                throwable.printStackTrace()
//        }


        }







}













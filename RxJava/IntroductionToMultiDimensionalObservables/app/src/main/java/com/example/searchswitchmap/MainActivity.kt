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

// If you see anything weird, inconsistent, or flat out wrong , please send me an email at kelloh@ford.com



/*

This is sample program of the Autocomplete functionality that you traditionally see in many apps, such as Google Maps and others.

In this example I am getting a list of Github Repositories.

The basic idea is as follows, we type text into the search bar. For each letter, we make a network call, get the results, and display
them onto the screen. For example, if we type "r", we are gonna make a network call to the Github API, and then get a list of results
of repositories that start with the letter "r".

So for example, if I type rxjava, I will make 5 network calls, the first network call is sent to the server where the query string is "r".
The second network call is sent where the query string is "rx". The third network call is sent where the query string is "rxj" and so on.

In order to get the list of repositories, I am using two observables. The first one actually makes a network call, and the second one
is an offline version that is hard coded to accept only "rxjava" as a string. I recommend using the offline version first because the results
show up instantly. You can use the network observable to try other strings. However, keep in mind that you have to wait for a little bit
after each keystroke to see the results update.

First try using flatMap.  (Line 103 in this file)

Do the following:

1. Type r
2. Notice that the UI is not being updated with results. This is because the first network call is delayed by 10 seconds.
3. Type in the rest of the string (xjava).
4. You will notice that the list will start being populated with repositories beginning with the string that you wrote. For example,
when you write "rx", you will see a list of results where the repository begins with the letter "rx". So you will see RxSwift,RxAndroid,
RxJS, and so on. If you write rxja , all of the repositories will start with rxja.
5. You will see after 10 seconds, the UI will change. It will now show you repositories that start with the letter r only. ( The first
repository is dmpe/R . This is for the offline version).
6. What happened is the following: we entered the string rxjava, and we expect to see repositories that start with the string rxjava. However,
since the first network call is delayed by 10 seconds, it will arrive after 10 seconds, and the UI will then be updated to show repositories
that start with the letter R. This is a problem because this may cause us to have inconsistent UI if we have delay, and will be a bad
user experience. In order to combat that, we use SwitchMap


Using SwitchMap:

Now, comment out flatMap in line 103, and uncomment switchMap in line 104. Try the example again.
Now, you should not see the same thing that happened previously i.e. you will not see the results for the first network call "r" show up
after 10 seconds. This is because if we use SwitchMap, we are going to cancel the first network call that was delayed. In other words,
we are going to only have 1 network call at a time, and if we enter rx, the first network call will be canceled.




In order to take a closer look, check the logcat. paste ⚾ into the logcat, and run the example first using flatMap (type in rxjava),
and then using switchMap. In switchMap, the second line should say "Dispose ❌  ❌  ❌ 	 query string is:  r ". This means that we disposed of
the network call where the query string is r. You will not see this in flatMap. If you use flatMap, at the end of the logcat, you will see
this line: "onNext: ▶  ▶  ▶ 	 query string is:  r ". This onNext came in after "onNext: ▶  ▶  ▶ 	 query string is:  rxjava ", which
means that we got the results for the query string "r" after the results for "rxjava'

This code is the demo from the presentation titled "Multidimensional Observables"

 */
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
            .flatMap { queryString ->
//            .switchMap { queryString ->
                observablesProvider.getSearchObservableOffline(queryString)
                    .observeOn(AndroidSchedulers.mainThread())
                    .log(Emoji.BaseBall,queryString.toString())
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



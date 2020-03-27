package com.example.searchswitchmap

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var repoList: MutableList<SearchResult>
    private lateinit var searchField: EditText
    private lateinit var threeDimensionalObservable: Observable<GithubResponse>
    private lateinit var editTextObservable: Observable<CharSequence>

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


        recyclerView = findViewById(R.id.githubrepo_recyclerview)
        editTextObservable = searchField.editTextObservable.filter { !it.isNullOrBlank() }


        threeDimensionalObservable =
            wifiConnectionObservable.doOnNext { isConnected ->
                handleConnectivityState(isConnected)
            }
                .switchMap { isConnected ->
                    if (isConnected)
                        editTextObservable.switchMap { observablesProvider.getSearchObservable(it) }
                    else
                        Observable.empty()
                }

        compositeDisposable.add(
            threeDimensionalObservable.subscribeBy(onNext = this::handleSuccess, onError = this::handleError)
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


    val connectionObservable = Observable.create<Boolean>
    { emitter ->

        val connectivityManager =
            this@MainActivity.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        val networkRequest = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()

        val networkCallBack = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                emitter.onNext(true)
            }

            override fun onLost(network: Network) {
                emitter.onNext(false)
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallBack)

        emitter.setCancellable { connectivityManager.unregisterNetworkCallback(networkCallBack) }

    }.observeOn(AndroidSchedulers.mainThread())

    val wifiConnectionObservable = connectionObservable.doOnNext { isConnected ->
        if (!isConnected)
            banner.showBanner()
    }


    fun enableEditText() {
        searchField.isEnabled = true
        searchField.setHintTextColor(Color.rgb(170, 170, 170))
        searchField.backgroundTintList = this.resources.getColorStateList(R.color.enabledEditText)
        searchField.setTextColor(Color.BLACK)
    }

    fun disableEditText() {
        searchField.isEnabled = false
        searchField.setHintTextColor(Color.RED)
        searchField.backgroundTintList = this.resources.getColorStateList(R.color.disabledEditTExt)
        searchField.setTextColor(Color.RED)


    }

    fun handleConnectivityState(isConnected: Boolean) {

        if (!isConnected) {
            banner.showBanner()
            disableEditText()
        } else {
            banner.hideBanner()
            enableEditText()
        }

    }

}



package com.karam.searchswitchmap

import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.karam.searchswitchmap.Providers.GithubRepositoryProvider
import com.karam.searchswitchmap.Providers.ObservablesProvider
import com.karam.searchswitchmap.databinding.ActivityMainBinding
import com.karam.searchswitchmap.model.SearchResult


class MainActivity : FragmentActivity() {


    private val githubRepositoryProvider = GithubRepositoryProvider()
    private val observablesProvider = ObservablesProvider(githubRepositoryProvider)
    private lateinit var adapter:GithubRepositoryAdapter
    private lateinit var recyclerView:RecyclerView
    private lateinit var searchField:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.employee_recyclerview)
        searchField = findViewById(R.id.search_field)


        val viewModel = ViewModelProviders.of(this).get(GithubSearchViewModel::class.java)

        val binding:ActivityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.viewModel = viewModel



//
//        searchField.doOnTextChanged { text, start, count, after ->
//
//
//        }
//
//       val asd =  RxTextView.textChanges(searchField).skipInitialValue()
//           .filter { !it.isNullOrBlank() }
//           .switchMap { observablesProvider.getSearchObservable(it) }
//           .subscribeBy(onNext = ::handleSuccess,onError = ::handleError)
    }

//    private fun handleSuccess(response: GithubResponse ){
//
//
//        Log.i("RESPONSE","$response")
//
//            adapter = GithubRepositoryAdapter(this,response.items)
//            recyclerView?.layoutManager = LinearLayoutManager(this)
//            recyclerView?.adapter = adapter
//
//
//    }
//
//    private fun handleError(throwable: Throwable) {
//        Toast.makeText(this,"An error has occurred",Toast.LENGTH_LONG).show()
//        Log.d("POTATO","${throwable}")
//
//        throwable.printStackTrace()
//    }


}

















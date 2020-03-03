package com.karam.searchswitchmap.Providers

import com.example.searchswitchmap.Providers.GithubRepositoryProvider
import com.karam.searchswitchmap.model.GithubResponse
import com.karam.searchswitchmap.model.SearchResult
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ObservablesProvider(private var githubRepositoryProvider: GithubRepositoryProvider) {


    fun getSearchObservable(repoName: CharSequence): Observable<GithubResponse> {


        if(repoName.toString() == "rxq") {

            return githubRepositoryProvider.getRepositories(repoName).concatWith(Observable.error(Throwable()))
        }

            return githubRepositoryProvider.getRepositories(repoName)
    }

    fun getSearchObservableOffline(repoName: CharSequence): Observable<GithubResponse> {

        return when (repoName.toString().toLowerCase()) {
            "r" -> {
                var response = GithubResponse(
                    items = mutableListOf(
                        SearchResult(full_name = "dmpe/R"),
                        SearchResult(full_name = "binder-examples/r"),
                        SearchResult(full_name = "datadolphyn/R"),
                        SearchResult(full_name = "TheAlgorithms/R"),
                        SearchResult(full_name = "swirldev/swirl"),
                        SearchResult(full_name = "Twitter-Sentiment-Analysis/R"),
                        SearchResult(full_name = "rbgirshick/fast-rcnn"),
                        SearchResult(full_name = "rdpeng/ProgrammingAssignment2"),
                        SearchResult(full_name = "hadley/adv-r"),
                        SearchResult(full_name = "SignalR/SignalR"),
                        SearchResult(full_name = "ShaoqingRen/faster_rcnn"),
                        SearchResult(full_name = "twitter/AnomalyDetection"),
                        SearchResult(full_name = "rstudio/rmarkdown"),
                        SearchResult(full_name = "qinwf/awesome-R"),
                        SearchResult(full_name = "hadley/r4ds"),
                        SearchResult(full_name = "rstudio/shiny"),
                        SearchResult(full_name = "panschk/r"),
                        SearchResult(full_name = "ncornwell/R"),
                        SearchResult(full_name = "mlr-org/mlr"),
                        SearchResult(full_name = "jbogard/MediatR"),
                        SearchResult(full_name = "mac-cain13/R.swift"),
                        SearchResult(full_name = "josephpconley/R"),
                        SearchResult(full_name = "ramnathv/rCharts"),
                        SearchResult(full_name = "requirejs/r.js"),
                        SearchResult(full_name = "r-lib/httr"),
                        SearchResult(full_name = "rstudio/tensorflow"),
                        SearchResult(full_name = "facebook/prophet"),
                        SearchResult(full_name = "hadley/r-pkgs"),
                        SearchResult(full_name = "amplab-extras/SparkR-pkg"),
                        SearchResult(full_name = "IRkernel/IRkernel")
                    )
                )
                Observable.just(response)
            }
            "rx" -> {


                var response = GithubResponse(
                    items = mutableListOf(
                        SearchResult(full_name = "ReactiveX / RxJava"),
                        SearchResult(full_name = "ReactiveX / RxSwift"),
                        SearchResult(full_name = "ReactiveX / RxAndroid"),
                        SearchResult(full_name = "Reactive - Extensions / RxJS"),
                        SearchResult(full_name = "Tamsiree / RxTool"),
                        SearchResult(full_name = "tbruyelle / RxPermissions"),
                        SearchResult(full_name = "JakeWharton / RxBinding"),
                        SearchResult(full_name = "ReactiveX / rxjs"),
                        SearchResult(full_name = "kaushikgopal / RxJava - Android - Samples"),
                        SearchResult(full_name = "staltz / rxmarbles"),
                        SearchResult(full_name = "trello / RxLifecycle"),
                        SearchResult(full_name = "lzyzsd / Awesome - RxJava"),
                        SearchResult(full_name = "rengwuxian / RxJavaSamples"),
                        SearchResult(full_name = "ssseasonnn / RxDownload"),
                        SearchResult(full_name = "mcxiaoke / RxDocs"),
                        SearchResult(full_name = "amitshekhariitbhu / RxJava2 - Android - Samples"),
                        SearchResult(full_name = "moroshko / rxviz"),
                        SearchResult(full_name = "ReactiveX / RxKotlin"),
                        SearchResult(full_name = "cloudhead / rx"),
                        SearchResult(full_name = "nanchen2251 / RxJava2Examples"),
                        SearchResult(full_name = "zhou - you / RxEasyHttp"),
                        SearchResult(full_name = "pubkey / rxdb"),
                        SearchResult(full_name = "FinalTeam / RxGalleryFinal"),
                        SearchResult(full_name = "Polidea / RxAndroidBle"),
                        SearchResult(full_name = "ReactiveX / RxPY"),
                        SearchResult(full_name = "Froussios / Intro - To - RxJava"),
                        SearchResult(full_name = "ReactiveX / RxCpp"),
                        SearchResult(full_name = "RxSwiftCommunity / RxDataSources"),
                        SearchResult(full_name = "rjbs / Rx"),
                        SearchResult(full_name = "microshow / RxFFmpeg")
                    )
                )
                Observable.just(response)
            }
            "rxj" -> {

                var response = GithubResponse(
                    items = mutableListOf(
                        SearchResult(full_name = "ReactiveX / RxJava"),
                        SearchResult(full_name = "ReactiveX / rxjs"),
                        SearchResult(full_name = "Reactive - Extensions / RxJS"),
                        SearchResult(full_name = "kaushikgopal / RxJava - Android - Samples"),
                        SearchResult(full_name = "tough1985 / RxjavaRetrofitDemo"),
                        SearchResult(full_name = "wzgiceman / RxjavaRetrofitDemo - master"),
                        SearchResult(full_name = "lzyzsd / Awesome - RxJava"),
                        SearchResult(full_name = "rengwuxian / RxJavaSamples"),
                        SearchResult(full_name = "vuejs / vue - rx"),
                        SearchResult(full_name = "androidmalin / RxjavaSample"),
                        SearchResult(full_name = "amitshekhariitbhu / RxJava2 - Android - Samples"),
                        SearchResult(full_name = "Reactive - Extensions / RxJSKoans"),
                        SearchResult(full_name = "btroncone / learn - rxjs"),
                        SearchResult(full_name = "nanchen2251 / RxJava2Examples"),
                        SearchResult(full_name = "Reactive - Extensions / rx.angular.js"),
                        SearchResult(full_name = "ng - book / angular2 - rxjs - chat"),
                        SearchResult(full_name = "redux - observable / redux - observable"),
                        SearchResult(full_name = "Froussios / Intro - To - RxJava"),
                        SearchResult(full_name = "ngrx / store"),
                        SearchResult(full_name = "LeetCode - OpenSource / rxjs - hooks"),
                        SearchResult(full_name = "acdlite / redux - rx"),
                        SearchResult(full_name = "cn - ljb / rxjava_for_android"),
                        SearchResult(full_name = "JakeWharton / retrofit2 - rxjava2 - adapter"),
                        SearchResult(full_name = "yuxingxin / RxJava - Essentials - CN"),
                        SearchResult(full_name = "youxin11544 / MVP - RxJava - Hybride"),
                        SearchResult(full_name = "jiang111 / RxJavaApp"),
                        SearchResult(full_name = "THEONE10211024 / RxJavaSamples"),
                        SearchResult(full_name = "Brooooooklyn / learning - rxjs"),
                        SearchResult(full_name = "angular - university / rxjs - course"),
                        SearchResult(full_name = "fdecampredon / rx - react")
                    )
                )
                Observable.just(response)
            }


            "rxja" -> {

                var response = GithubResponse(
                    items = mutableListOf(
                        SearchResult(full_name = " ReactiveX / RxJava"),
                        SearchResult(full_name = " kaushikgopal / RxJava - Android - Samples"),
                        SearchResult(full_name = " tough1985 / RxjavaRetrofitDemo"),
                        SearchResult(full_name = " wzgiceman / RxjavaRetrofitDemo - master"),
                        SearchResult(full_name = " lzyzsd / Awesome - RxJava"),
                        SearchResult(full_name = " rengwuxian / RxJavaSamples"),
                        SearchResult(full_name = " androidmalin / RxjavaSample"),
                        SearchResult(full_name = " amitshekhariitbhu / RxJava2 - Android - Samples"),
                        SearchResult(full_name = " nanchen2251 / RxJava2Examples"),
                        SearchResult(full_name = " Froussios / Intro - To - RxJava"),
                        SearchResult(full_name = " cn - ljb / rxjava_for_android"),
                        SearchResult(full_name = " JakeWharton / retrofit2 - rxjava2 - adapter"),
                        SearchResult(full_name = " yuxingxin / RxJava - Essentials - CN"),
                        SearchResult(full_name = " youxin11544 / MVP - RxJava - Hybride"),
                        SearchResult(full_name = " jiang111 / RxJavaApp"),
                        SearchResult(full_name = " THEONE10211024 / RxJavaSamples"),
                        SearchResult(full_name = " davidmoten / rxjava - jdbc"),
                        SearchResult(full_name = " leeowenowen / rxjava - examples"),
                        SearchResult(full_name = " meddle0x53 / learning - rxjava"),
                        SearchResult(full_name = " wzgiceman / RxjavaRetrofitDemo - string - master"),
                        SearchResult(full_name = " davidmoten / rxjava2 - jdbc"),
                        SearchResult(full_name = " Carson - Ho / RxJavaLearningMaterial"),
                        SearchResult(full_name = " JoaoMotondon / RxJavaDemoApp"),
                        SearchResult(full_name = " xinghongfei / Hello - RxJava"),
                        SearchResult(full_name = " bigeyechou / Rxjava2Retrofit2NetFrame"),
                        SearchResult(full_name = " AxeChen / retrofit2_rxjava2"),
                        SearchResult(full_name = " akarnokd / RxJavaInterop"),
                        SearchResult(full_name = " dlew / rxjava - multiple - sources - sample"),
                        SearchResult(full_name = " ssseasonnn / RxJava2Demo"),
                        SearchResult(full_name = " mgp / effective - rxjava")
                    )
                )

                Observable.just(response)

            }

            "rxjav" -> {

                var response = GithubResponse(
                    items = mutableListOf(
                        SearchResult(full_name = " ReactiveX / RxJava"),
                        SearchResult(full_name = " kaushikgopal / RxJava - Android - Samples"),
                        SearchResult(full_name = " tough1985 / RxjavaRetrofitDemo"),
                        SearchResult(full_name = " wzgiceman / RxjavaRetrofitDemo - master"),
                        SearchResult(full_name = " lzyzsd / Awesome - RxJava"),
                        SearchResult(full_name = " rengwuxian / RxJavaSamples"),
                        SearchResult(full_name = " androidmalin / RxjavaSample"),
                        SearchResult(full_name = " amitshekhariitbhu / RxJava2 - Android - Samples"),
                        SearchResult(full_name = " nanchen2251 / RxJava2Examples"),
                        SearchResult(full_name = " Froussios / Intro - To - RxJava"),
                        SearchResult(full_name = " cn - ljb / rxjava_for_android"),
                        SearchResult(full_name = " JakeWharton / retrofit2 - rxjava2 - adapter"),
                        SearchResult(full_name = " yuxingxin / RxJava - Essentials - CN"),
                        SearchResult(full_name = " youxin11544 / MVP - RxJava - Hybride"),
                        SearchResult(full_name = " jiang111 / RxJavaApp"),
                        SearchResult(full_name = " THEONE10211024 / RxJavaSamples"),
                        SearchResult(full_name = " davidmoten / rxjava - jdbc"),
                        SearchResult(full_name = " leeowenowen / rxjava - examples"),
                        SearchResult(full_name = " meddle0x53 / learning - rxjava"),
                        SearchResult(full_name = " wzgiceman / RxjavaRetrofitDemo - string - master"),
                        SearchResult(full_name = " davidmoten / rxjava2 - jdbc"),
                        SearchResult(full_name = " Carson - Ho / RxJavaLearningMaterial"),
                        SearchResult(full_name = " JoaoMotondon / RxJavaDemoApp"),
                        SearchResult(full_name = " xinghongfei / Hello - RxJava"),
                        SearchResult(full_name = " bigeyechou / Rxjava2Retrofit2NetFrame"),
                        SearchResult(full_name = " AxeChen / retrofit2_rxjava2"),
                        SearchResult(full_name = " akarnokd / RxJavaInterop"),
                        SearchResult(full_name = " dlew / rxjava - multiple - sources - sample"),
                        SearchResult(full_name = " ssseasonnn / RxJava2Demo"),
                        SearchResult(full_name = " mgp / effective - rxjava")
                    )
                )

                Observable.just(response)
            }


            "rxjava" -> {


                var response = GithubResponse(
                    items = mutableListOf(
                        SearchResult(full_name = " ReactiveX / RxJava"),
                        SearchResult(full_name = " ReactiveX / RxAndroid"),
                        SearchResult(full_name = " amitshekhariitbhu / RxJava2 - Android - Samples"),
                        SearchResult(full_name = " lzyzsd / Awesome - RxJava"),
                        SearchResult(full_name = " kaushikgopal / RxJava - Android - Samples"),
                        SearchResult(full_name = " tough1985 / RxjavaRetrofitDemo"),
                        SearchResult(full_name = " wzgiceman / RxjavaRetrofitDemo - master"),
                        SearchResult(full_name = " rengwuxian / RxJavaSamples"),
                        SearchResult(full_name = " androidmalin / RxjavaSample"),
                        SearchResult(full_name = " nanchen2251 / RxJava2Examples"),
                        SearchResult(full_name = " ReactiveX / RxKotlin"),
                        SearchResult(full_name = " Froussios / Intro - To - RxJava"),
                        SearchResult(full_name = " cn - ljb / rxjava_for_android"),
                        SearchResult(full_name = " JakeWharton / retrofit2 - rxjava2 - adapter"),
                        SearchResult(full_name = " yuxingxin / RxJava - Essentials - CN"),
                        SearchResult(full_name = " youxin11544 / MVP - RxJava - Hybride"),
                        SearchResult(full_name = " AndroidKnife / RxBus"),
                        SearchResult(full_name = " jiang111 / RxJavaApp"),
                        SearchResult(full_name = " THEONE10211024 / RxJavaSamples"),
                        SearchResult(full_name = " davidmoten / rxjava - jdbc"),
                        SearchResult(full_name = " reark / reark"),
                        SearchResult(full_name = " JakeWharton / RxBinding"),
                        SearchResult(full_name = " leeowenowen / rxjava - examples"),
                        SearchResult(full_name = " meddle0x53 / learning - rxjava"),
                        SearchResult(full_name = " wzgiceman / RxjavaRetrofitDemo - string - master"),
                        SearchResult(full_name = " davidmoten / rxjava2 - jdbc"),
                        SearchResult(full_name = " hitherejoe / Android - Boilerplate"),
                        SearchResult(full_name = " SmartDengg / RxWeather"),
                        SearchResult(full_name = " tbruyelle / RxPermissions"),
                        SearchResult(full_name = " trello / RxLifecycle)")
                    )
                )

                Observable.just(response)

            }


            else -> {
//                Observable.just(GithubResponse(items = mutableListOf(SearchResult("invalid String"))))
                Observable.error(Throwable())
            }

        }


    }


}
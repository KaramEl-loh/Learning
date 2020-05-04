package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main() {


//    mappingObservableAToObservableBUsingSwitchMap()
    mappingObservableAToObservableBUsingMapAndSwitchOnNext()

}


fun asd() {

    val obsA = Observable.interval(2,TimeUnit.SECONDS)
    val obsB = Observable.just("Hello")
    val obsC = obsA.flatMap { obsB }


    obsC

}

fun mappingObservableAToObservableBUsingSwitchMap() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)

    obsA.switchMap { obsB }.blockingSubscribe { print("$it\t") }

}

fun mappingObservableAToObservableBUsingMapAndSwitchOnNext() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)


    Observable.switchOnNext(obsA.map { obsB }).blockingSubscribe { print("$it\t") }

}




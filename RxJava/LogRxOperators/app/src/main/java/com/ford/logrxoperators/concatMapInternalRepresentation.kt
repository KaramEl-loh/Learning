package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main() {

//    mappingObservableAToObservableBUsingConcatMap()
    mappingObservableAToObservableBUsingMapAndConcat()
}

fun mappingObservableAToObservableBUsingConcatMap() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)

    obsA.concatMap { obsB }.blockingSubscribe { print("$it\t") }

}

fun mappingObservableAToObservableBUsingMapAndConcat() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)


    Observable.concat(obsA.map { obsB }).blockingSubscribe {print("$it\t") }

}
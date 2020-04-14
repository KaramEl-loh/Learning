package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main() {

    mappingObservableAToObservableBUsingFlatMap()
//    mappingObservableAToObservableBUsingMapAndMerge()
//    mappingObservableAToObservableBUsingConcatMap()
//    mappingObservableAToObservableBUsingMapAndConcat()
//    mappingObservableAToObservableBUsingSwitchMap()
//    mappingObservableAToObservableBUsingMapAndSwitchOnNext()

}

fun mappingObservableAToObservableBUsingFlatMap() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)

    obsA.flatMap { obsB }.blockingSubscribe { print("$it\t") }

}

fun mappingObservableAToObservableBUsingMapAndMerge() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)


    Observable.merge(obsA.map { obsB }).blockingSubscribe { print("$it\t") }

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




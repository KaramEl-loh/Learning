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

    obsA.flatMap { obsB }.blockingSubscribe { println(it) }

}

fun mappingObservableAToObservableBUsingMapAndMerge() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)


    Observable.merge(obsA.map { obsB }).blockingSubscribe { println(it) }

}

fun mappingObservableAToObservableBUsingConcatMap() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)

    obsA.concatMap { obsB }.blockingSubscribe { println(it) }

}

fun mappingObservableAToObservableBUsingMapAndConcat() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)


    Observable.concat(obsA.map { obsB }).blockingSubscribe { println(it) }

}

fun mappingObservableAToObservableBUsingSwitchMap() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)

    obsA.switchMap { obsB }.blockingSubscribe { println(it) }

}

fun mappingObservableAToObservableBUsingMapAndSwitchOnNext() {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)


    Observable.switchOnNext(obsA.map { obsB }).blockingSubscribe { println(it) }

}

fun qwe(): Observable<Long> {

    val obsA = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2)
    val obsB = Observable.interval(500, TimeUnit.MILLISECONDS).take(5)

    return obsA.flatMap { obsB }


}



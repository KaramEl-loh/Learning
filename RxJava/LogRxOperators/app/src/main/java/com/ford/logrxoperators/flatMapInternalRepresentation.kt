package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main() {

//    mappingObservableAToObservableBUsingFlatMap()
    mappingObservableAToObservableBUsingMapAndMerge()
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






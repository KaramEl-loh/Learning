package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit



//TODO: FIX DOCUMENTATION

/*

For information on how flatMap works, check out http://reactivex.io/documentation/operators/flatmap.html

This file contains in depth examples of flatMap.

For explanation purposes, the outer observable is referred to as the parent observable, and the inner observable is
referred to as the child observable.

For example, in ObsA.flatMap{ObsB} , ObsA is the parent, while obsB is the child


If you have any other interesting examples/behaviour of flatMap, feel free to include them here so that we can all learn :)

 */







/*

An example of flatMap. In this example, the parent observable (obsA) emits every 10 seconds, and only emits 2 items.

The child observable (obsB) emits every 5 seconds, and only emits 5 items.

The parent observable needs 20 seconds to complete, while the child observable needs 25 seconds to complete. This means
that the parent observable will complete before the child observable, assuming no errors occur.

What happens is the following:

We first subscribe to A, and then wait until A emits an item. Once A emits an item, we subscribe to B, and wait for B to emit all of its items.

However, after t = 10 s, A emits again, which means we have to create another subscription to B. A emits only 2 items, which means it has now
completed. A will fire an onComplete event, and will wait for B to emit all its items (the two subscriptions to B are not disposed).


Key takeaways from this example:
1. In flatMap, we may have more than one subscription to the child observable(s)
2. If the parent observable onCompletes, it will wait for the child observable to finish emitting its items (i.e. it will wait for a terminal
event, either an onComplete or an onError).

 */


fun parentObservableCompletesBeforeChildObservableIsDoneEmitting():Observable<Long> {


    val obsA = Observable.interval(1000,TimeUnit.MILLISECONDS).take(2).log(Emoji.BaseBall)
    val obsB = Observable.interval(500,TimeUnit.MILLISECONDS).take(5).log(Emoji.Building)

    return obsA.flatMap { obsB }


}

/*

An example of flatMap. In this example, the parent observable (obsA) emits every 5 seconds, and only emits 4 items.

The child observable (obsB) emits every 2 seconds, and only emits 3 items.

The parent observable needs 20 seconds to complete, while the child observable needs 6 seconds to complete. This means
that the child observable will complete before the parent  observable, assuming no errors occur.

What happens is the following:


When A emits its first item, we subscribe to B, and wait for B to emit all of its items.

Between the first and second emissions of A, B is still under the process of emitting (i.e. hasn't completed).
It is evident when you see that B emits 0 and 1, but still hasn't emitted 2 yet. Once A emits the second item, another
subscription to B occurs. During that time, the last item from B emits, and then B onCompletes.

If you look closely at the console, once the A emits the second item, and the second subscription to B is made, we do not emit
the first item from B. Instead, we emit the last item of B, since that item has arrived at that point in time.

Key takeaway from this example:
In flatMap, we do not wait for the previous subscription(s) to finish before subscribing again. Instead, items are emitted as they
come , and we may have more than one subscription at a time.


 */

fun childObservableCompletesBeforeParentObservableisDoneEmitting():Observable<Long> {


    val obsA = Observable.interval(1000,TimeUnit.MILLISECONDS).take(5).log(Emoji.BaseBall)
    val obsB = Observable.interval(500,TimeUnit.MILLISECONDS).take(2).log(Emoji.Building)

    return obsA.flatMap { obsB }


}

/*

An example of flatMap. In this example, the parent observable (obsA) emits every 5 seconds, and only emits 4 items.

The child observable (obsB) emits every 2 seconds, and emits 7 items. The final emission is an error.

The parent observable needs 20 seconds to complete, while the child observable needs 12 seconds to complete.

What happens is the following:

A emits every 5 seconds, and B takes 12 seconds to finish. This means that when we reach the error, two subscriptions
to B will have been made, because A has emitted 2 times. However, since an error occurred in the child observable, we will
first dispose of the subscription to the parent observable, and then we will dispose of any subscriptions made to the child observable.

In this case, We dispose of subscription to A first, and then of the two subscriptions made to B.


Key takeaway from this example:
Whenever an error occurs in the child observable and the parent hasn't completed yet, the subscription to the parent will be disposed + any
subscriptions made to the child that did not complete






 */

fun childObservableErrorsBeforeParentObservableStopsEmitting():Observable<Long> {


    val obsA = Observable.interval(1,TimeUnit.SECONDS).take(4).log(Emoji.BaseBall)
    val obsB = Observable.interval(500,TimeUnit.MILLISECONDS).take(4)
        .concatWith(Observable.error(Throwable()))
        .log(Emoji.Building)

    return obsA.flatMap { obsB }

}

fun parentObservableErrorsBeforeChildObservableStopsEmitting():Observable<Long> {


    val obsA = Observable.interval(1,TimeUnit.SECONDS).take(4)
        .concatWith(Observable.error(Throwable())).log(Emoji.BaseBall)
    val obsB = Observable.interval(500,TimeUnit.MILLISECONDS).take(5)
        .log(Emoji.Building)

    return obsA.flatMap { obsB }

}

/*

An example of flatMap. In this example, we have three observables.

Observable A emits every 5 seconds, and emits only 5 items.
Observable A  is the grandparent.

Observable B emits every 3 seconds, and emits only 4 items.
Observable B  is the parent.

Observable C emits every 1 second, and emits only 2 items. The second item is an error.
Observable C  is the child.



The error occurs in C. Once C errors out, the subscription to B will be disposed, and then the subscription to A will be disposed.

B is disposed first because B is the parent of C, and A is the parent of B flatMap C. This means that errors propagate upwards, and the
way flatMaps are nested are crucial in order to handle errors effectively

 */


fun childObservableErrorBeforeParentAndGrandParent():Observable<Long> {


    val obsA = Observable.interval(5,TimeUnit.SECONDS).take(5).log(Emoji.BaseBall)
    val obsB = Observable.interval(3,TimeUnit.SECONDS).take(4).log(Emoji.b_emoji)

    val obsC = Observable.interval(1,TimeUnit.SECONDS).take(1).concatWith(Observable.error(Throwable()))
        .log(Emoji.c_emoji)

    return obsA.flatMap { obsB.flatMap { obsC } }

}

/*

An example of flatMap. This example is similar to the previous example (childObservableErrorBeforeParentAndGrandParent).

However, in this example, we are disposing of multiple subscriptions , because by the time we reach the error on observable C,
A and B would have emitted multiple items, meaning that we will have multiple subscriptions

 */

fun childObservableErrorBeforeParentAndGrandParentMultipleSubscriptions():Observable<Long> {


    val obsA = Observable.interval(3,TimeUnit.SECONDS).take(15).log(Emoji.a_emoji)
    val obsB = Observable.interval(2,TimeUnit.SECONDS).take(4).log(Emoji.b_emoji)

    val obsC = Observable.interval(1,TimeUnit.SECONDS).take(7).concatWith(Observable.error(Throwable()))
        .log(Emoji.c_emoji)

    return obsA.flatMap { obsB.flatMap { obsC } }

}


/*

An example of flatMap. In this example, we have three observables.

Observable A emits every 5 seconds, and emits only 5 items.
Observable A  is the grandparent.

Observable B emits every 3 seconds, and emits only 4 items.
Observable B  is the parent.

Observable C emits every 1 second, and emits only 2 items. The second item is an error.
Observable C  is the child.


This example is similar to (childObservableErrorBeforeParentAndGrandParent). However, the order in which we flatMap is different.

In this example, we flatMap observable A into observable B. After that, we flatMap the result into observable C.

-- obsA.flatMap { obsB.flatMap { obsC } } --

In the previous example (childObservableErrorBeforeParentAndGrandParent), we flatMap observable B into observable C. After that, we
flatMap observable A into the previous result.

-- obsA.flatMap { obsB }.flatMap { obsC } --


In this example, when the error occurs in observable C, A is disposed first, not B. This is because A is considered the parent of
C , since the result of A flatMap B will be flatMapped to C. In that case , the parent of C is A, not B.


 */

fun childObservableErrorBeforeParentAndGrandParentDifferentOrderOfFlatMap():Observable<Long> {


    val obsA = Observable.interval(5,TimeUnit.SECONDS).take(5).log(Emoji.a_emoji)
    val obsB = Observable.interval(3,TimeUnit.SECONDS).take(4).log(Emoji.b_emoji)

    val obsC = Observable.interval(1,TimeUnit.SECONDS).take(1).concatWith(Observable.error(Throwable()))
        .log(Emoji.c_emoji)

    return obsA.flatMap { obsB }.flatMap { obsC }

}
package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


/*

Examples of the takeUntil operator. For more information, go to https://rxmarbles.com/#takeUntil


If you have any other interesting examples/behaviour of takeUntil, feel free to include them here so that we can all learn :)


 */


/*

An example of takeUntil. In this example, the first observable emits an item every 2 seconds, and the second observable
emits every 10 seconds.

Once we reach 10 seconds, the second observable will emit. After the second observable emits, the second
observable will be disposed, and subsequently the first observable will then be disposed.

 */


fun firstObservableFiresFirstTakeUntil(): Observable<Long> {

    val obsA = Observable.interval(2, TimeUnit.SECONDS).log(Emoji.completed)
    val obsB = Observable.interval(10, TimeUnit.SECONDS).log(Emoji.b_emoji)

    return obsA.takeUntil(obsB)

}


/*

Another example of takeUntil. In this example, the first observable emits an item every 6 seconds, and the second observable
emits every 3 seconds.

Since the second observable fires before the first, the second observable will fire at t = 3, then dispose. After that, the first
observable will then dispose.

 */

fun secondObservableFiresFirstTakeUntil(): Observable<Long> {

    val obsA = Observable.interval(6, TimeUnit.SECONDS).log(Emoji.completed)
    val obsB = Observable.interval(3, TimeUnit.SECONDS).log(Emoji.b_emoji)

    return obsA.takeUntil(obsB)

}

/*

Another example of takeUntil. The first observable will emit after 5 seconds, and the second observable will emit after 10 seconds

When the first observable emits, it will then finish (call onComplete). Because the first observable completed, the second observable will be disposed,
because the second observable never emitted an item.

 */

fun firstObservableCompletesBeforeSecondStartsTakeUntil(): Observable<Long> {

    val obsA = Observable.timer(5, TimeUnit.SECONDS).log(Emoji.completed)
    val obsB = Observable.timer(10, TimeUnit.SECONDS).log(Emoji.b_emoji)


    return obsA.takeUntil(obsB)
}


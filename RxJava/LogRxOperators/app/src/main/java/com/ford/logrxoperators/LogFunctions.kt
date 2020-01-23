@file:Suppress("NOTHING_TO_INLINE")

package com.ford.logrxoperators

import io.reactivex.*

inline fun <reified T> printEvent(tag: String, success: T?, error: Throwable?) =
    when {
        success == null && error == null -> println("$tag Complete") /* Only with Maybe */
        success != null -> println("$tag Success $success")
        error != null -> println("$tag Error $error")
        else -> -1 /* Cannot happen*/
    }

inline fun printEvent(tag: String, error: Throwable?) =
    when {
        error != null -> println("$tag Error $error")
        else -> println("$tag Complete")
    }

/**
 * Example usage of [log]:
Single.timer(1, TimeUnit.SECONDS)
.log(Emoji.Warning)
.subscribe({ }, { })
 */
inline fun tag() =
    Thread.currentThread().stackTrace
        .first { it.fileName.endsWith(".kt") }
        .let { stack -> "⚠️ ⚠️ ⚠️ PhotographerRx: " + "${stack.fileName.removeSuffix(".kt")}::${stack.methodName}:${stack.lineNumber}" }

inline fun <reified T> Single<T>.log(tag: String? = null): Single<T> {
    val line = tag ?: tag()
    return doOnEvent { success, error -> printEvent(line, success, error) }
        .doOnSubscribe { println("$line Subscribe ${Emoji.Plane} ") }
        .doOnDispose { println("$line Dispose ${Emoji.disposed}") }
}

inline fun <reified T> Maybe<T>.log(tag: String? = null): Maybe<T> {
    val line = tag ?: tag()
    return doOnEvent { success, error -> printEvent(line, success, error) }
        .doOnSubscribe { println("$line Subscribe ${Emoji.Plane} ") }
        .doOnDispose { println("$line Dispose ${Emoji.disposed}") }
}

inline fun Completable.log(tag: String? = null): Completable {
    val line = tag ?: tag()
    return doOnEvent { printEvent(line, it) }
        .doOnSubscribe { println("$line Subscribe") }
        .doOnDispose { println("$line Dispose") }
}


inline fun <reified T> Observable<T>.log(tag: String? = null): Observable<T> {
    val line = tag ?: tag()
    return doOnEach { println("$line Each $it") }
        .doOnError { println("$line OnError ${Emoji.error}")}
        .doOnSubscribe { println("$line Subscribe ${Emoji.Plane} ") }
        .doOnDispose { println("$line Dispose ${Emoji.disposed}") }
        .doOnNext { println("$line onNext: ${Emoji.next}") }
        .doOnComplete { println("$line onComplete ${Emoji.completed}") }
}

inline fun <reified T> Flowable<T>.log(tag: String? = null): Flowable<T> {
    val line = tag ?: tag()
    return doOnEach { println("$line Each $it") }
        .doOnSubscribe { println("$line Subscribe ${Emoji.Plane} ") }
        .doOnCancel { println("$line Cancel") }

}


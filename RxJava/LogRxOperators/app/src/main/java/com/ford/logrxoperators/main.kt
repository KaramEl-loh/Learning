package com.ford.logrxoperators

/*

 Test the behaviour of observables, operators, singles, and so on by using the
 log function to print the status of an observable at each point of its "lifecycle"

 The log function uses Emoji's in order to make logging easier. You can change the emoji being
 displayed by passing your Emoji of choice into the log function.


 */


fun main() {

    //takeUntil

//    demoUntilComplete(firstObservableCompletesBeforeSecondStartsTakeUntil(),"TakeUntil: First observable completes before second starts:")
//    demoUntilComplete(firstObservableFiresFirstTakeUntil(),"TakeUntil: First observable fires before the second observable:")
//    demoUntilComplete(secondObservableFiresFirstTakeUntil(),"TakeUntil: Second observable fires before the first observable:")

    // enumerated (custom operator)

//    demoUntilComplete(stringListEnumerated(),"Enumerating a list of strings: ",printResults = true)


    //flatMap - use one example at a time for now




    try {

//                demoUntilComplete(
//            parentObservableCompletesBeforeChildObservableIsDoneEmitting()
//            , "flatMap: Parent Observable Completes Before Child Observable Is Done Emitting "
//        )
//        demoUntilComplete(
//            parentObservableCompletesBeforeChildObservableEmits()
//            , "flatMap: Parent Observable Completes Before Child Observable Emits "
//        )

        //Every example underneath this comment must be run on its own, since they halt program execution after throwing an error

//        demoUntilComplete(
//            childObservableErrorsBeforeParentObservableStopsEmitting()
//            , "flatMap: Child Observable Errors Before Parent Observable Stops Emitting "
//        )

//                demoUntilComplete(
//            childObservableErrorBeforeParentAndGrandParent(),
//            "flatMap: Child Observable Error Before Parent And Grandparent "
//        )
//
//
//        demoUntilComplete(
//            childObservableErrorBeforeParentAndGrandParentMultipleSubscriptions()
//            , "flatMap: Child Observable Error Before Parent And GrandParent Multiple Subscriptions "
//        )
//        demoUntilComplete(
//            childObservableErrorBeforeParentAndGrandParentDifferentOrderOfFlatMap()
//            , "flatMap: Child Observable Error Before Parent And GrandParent Different Order Of FlatMap "
//        )
    } catch (throwable:Throwable) {



    }


}









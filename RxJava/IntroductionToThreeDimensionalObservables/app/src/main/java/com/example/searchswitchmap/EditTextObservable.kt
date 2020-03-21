package com.example.searchswitchmap

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import io.reactivex.Observable


val EditText.editTextObservable: Observable<CharSequence>
    get() = Observable.create<CharSequence> { emitter ->

        doOnTextChanged { text, _, _, _ ->
            text?.let {
                emitter.onNext(it)
            }
        }



    }
package com.example.searchswitchmap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class Banner(context: Context, parentView: ViewGroup) {

    private val banner: View? = LayoutInflater.from(context).inflate(R.layout.banner, parentView)
    private var bannerText = banner?.findViewById(R.id.banner_text) as? TextView
    private var bannerHeight = 200

    init {

        banner?.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, bannerHeight)
        banner?.visibility = View.GONE

    }

    fun showBanner() {

        bannerText?.text = "An error has occurred."

        banner?.visibility = View.VISIBLE

    }


    fun hideBanner() {
        banner?.visibility = View.GONE
    }


}



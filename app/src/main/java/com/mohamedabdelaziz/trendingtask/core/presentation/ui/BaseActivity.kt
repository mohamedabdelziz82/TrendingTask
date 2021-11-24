package com.mohamedabdelaziz.trendingtask.core.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mohamedabdelaziz.trendingtask.core.data.InternetConnectionLiveData

abstract class BaseActivity : AppCompatActivity() {
   private var connectionLiveData: InternetConnectionLiveData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = InternetConnectionLiveData(this)
        observeNetworkState()
    }

    private fun observeNetworkState() {
        connectionLiveData!!.observe(this, Observer {
            if (it!!) {
                onConnectionSuccess()
            } else {
                onConnectionFailed()
            }
        })
    }

    protected abstract fun onConnectionSuccess()
    protected abstract fun onConnectionFailed()
}
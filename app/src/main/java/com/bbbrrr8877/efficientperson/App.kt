package com.bbbrrr8877.efficientperson

import android.app.Application
import com.bbbrrr8877.efficientperson.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
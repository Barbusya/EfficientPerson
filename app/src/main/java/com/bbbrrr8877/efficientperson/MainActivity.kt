package com.bbbrrr8877.efficientperson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bbbrrr8877.efficientperson.habits.presentation.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
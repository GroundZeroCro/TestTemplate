package com.groundzero.testing_mvp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.groundzero.testing_mvp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

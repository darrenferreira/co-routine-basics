package com.example.co_routine_basics.basic.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.co_routine_basics.R
import com.example.co_routine_basics.basic.viewmodel.BasicViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewmodel by viewModels<BasicViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loading.isVisible = false

        button3.setOnClickListener{
            viewmodel.getMeSomeData()
            viewmodel.loading.observe(this) {
                loading.isVisible = it
            }
            viewmodel.result.observe(this) {
                text_result.isVisible = true
                loading.isVisible = false
                text_result.text = it
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
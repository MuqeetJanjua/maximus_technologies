package com.example.my_app_for_test

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.my_app_for_test.databinding.ActivityMainBinding
import com.example.my_app_for_test.model.Fact
import com.example.my_app_for_test.viewModelTask.viewModelTask


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: viewModelTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_My_APP_for_test)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(viewModelTask::class.java)

        viewModel.loadFact()

        viewModel.progress.observe(this, Observer {
            if (it){
                binding.fact = Fact("Loading ... ", "Loading")
                binding.progressCircle.visibility = VISIBLE
                binding.reload.isEnabled = false
            }else{
                binding.progressCircle.visibility = GONE
                binding.reload.isEnabled = true
            }
        })


        viewModel.fact.observe(this, Observer {
            binding.fact = it
        })

        binding.reload.setOnClickListener {
            viewModel.loadFact()
        }
    }

    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Are Sure you want to Exit?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            super.onBackPressed()
        })
        builder.setNegativeButton("No"
        ) { dialog, id -> dialog.dismiss() }
        builder.show()
    }




}


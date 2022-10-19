package com.example.my_app_for_test.viewModelTask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.my_app_for_test.model.Fact
import com.example.my_app_for_test.repositoryTask.repositoryTask

class viewModelTask(application: Application): AndroidViewModel(application) {

    private val repository = repositoryTask(application)
    val progress : LiveData<Boolean>
    val fact : LiveData<Fact>

    init {
        this.fact = repository.fact
        this.progress = repository.progress
    }


    fun changeVisibility(){
        repository.changeVisibility()
    }

    fun loadFact(){
        repository.loadFact()
    }

}
package com.example.my_app_for_test.repositoryTask

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.my_app_for_test.model.Fact
import com.example.my_app_for_test.retrofit.factNetwork
import com.example.my_app_for_test.retrofit.helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class repositoryTask (val application: Application) {

    val progress = MutableLiveData<Boolean>()
    val fact = MutableLiveData<Fact>()


    fun changeVisibility(){
        progress.value = !(progress.value !=null && progress.value!!)
    }

    fun loadFact(){
        progress.value = true
        val service = helper.getInstance().create(factNetwork::class.java)
        service.getfact().enqueue(object : Callback<Fact> {

            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                fact.value = Fact(response.body()!!.fact, response.body()!!.length)
                progress.value = false
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                fact.value = Fact("Error!", "Error!")
                Toast.makeText(application, "Try Again ... Check Network Connection", Toast.LENGTH_LONG).show()
                progress.value = false
            }

        })
    }
}
package com.eylulcan.carmap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eylulcan.carmap.service.CarAPI
import com.eylulcan.carmap.Util
import com.eylulcan.carmap.model.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarListViewModel : ViewModel() {

    private var retrofit: CarAPI? = null
    private var carList = MutableLiveData<ArrayList<Car>>()
    val cars: LiveData<ArrayList<Car>> get() = carList

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarAPI::class.java)
    }

    fun getCarList() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit?.getCarList()
            withContext(Dispatchers.Main) {
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            carList.postValue(it)
                        }
                    }
                }
            }
        }
    }
}
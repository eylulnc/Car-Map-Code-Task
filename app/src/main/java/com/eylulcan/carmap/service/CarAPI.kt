package com.eylulcan.carmap.service

import com.eylulcan.carmap.model.Car
import retrofit2.Response
import retrofit2.http.GET

interface CarAPI {

    @GET("codingtask/cars")
    suspend fun getCarList(): Response<ArrayList<Car>>

}
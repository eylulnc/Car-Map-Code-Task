package com.eylulcan.carmap.model

import com.eylulcan.carmap.Util.Companion.DEF_DOUBLE
import com.eylulcan.carmap.Util.Companion.EMPTY_STR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class Car : Serializable {

    @SerializedName("id")
    @Expose
    private var id: String = EMPTY_STR

    @SerializedName("modelIdentifier")
    @Expose
    private var modelIdentifier: String = EMPTY_STR

    @SerializedName("modelName")
    @Expose
    private var modelName: String = EMPTY_STR

    @SerializedName("name")
    @Expose
    private var name: String = EMPTY_STR

    @SerializedName("make")
    @Expose
    private var make: String = EMPTY_STR

    @SerializedName("group")
    @Expose
    private var group: String = EMPTY_STR

    @SerializedName("color")
    @Expose
    private var color: String = EMPTY_STR

    @SerializedName("series")
    @Expose
    private var series: String = EMPTY_STR

    @SerializedName("fuelType")
    @Expose
    private var fuelType: String = EMPTY_STR

    @SerializedName("fuelLevel")
    @Expose
    private var fuelLevel: Double = DEF_DOUBLE

    @SerializedName("transmission")
    @Expose
    private var transmission: String = EMPTY_STR

    @SerializedName("licensePlate")
    @Expose
    private var licensePlate: String = EMPTY_STR

    @SerializedName("latitude")
    @Expose
    private var latitude: Double = DEF_DOUBLE

    @SerializedName("longitude")
    @Expose
    private var longitude: Double = DEF_DOUBLE

    @SerializedName("innerCleanliness")
    @Expose
    private var innerCleanliness: String = EMPTY_STR

    @SerializedName("carImageUrl")
    @Expose
    private var carImageUrl: String? = null

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getModelIdentifier(): String {
        return modelIdentifier
    }

    fun setModelIdentifier(modelIdentifier: String) {
        this.modelIdentifier = modelIdentifier
    }

    fun getModelName(): String {
        return modelName
    }

    fun setModelName(modelName: String) {
        this.modelName = modelName
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getMake(): String {
        return make
    }

    fun setMake(make: String) {
        this.make = make
    }

    fun getGroup(): String {
        return group
    }

    fun setGroup(group: String) {
        this.group = group
    }

    fun getColor(): String {
        return color
    }

    fun setColor(color: String) {
        this.color = color
    }

    fun getSeries(): String {
        return series
    }

    fun setSeries(series: String) {
        this.series = series
    }

    fun getFuelType(): String {
        return fuelType
    }

    fun setFuelType(fuelType: String) {
        this.fuelType = fuelType
    }

    fun getFuelLevel(): Double {
        return fuelLevel
    }

    fun setFuelLevel(fuelLevel: Double) {
        this.fuelLevel = fuelLevel
    }

    fun getTransmission(): String {
        return transmission
    }

    fun setTransmission(transmission: String) {
        this.transmission = transmission
    }

    fun getLicensePlate(): String {
        return licensePlate
    }

    fun setLicensePlate(licensePlate: String) {
        this.licensePlate = licensePlate
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun setLatitude(latitude: Double) {
        this.latitude = latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun setLongitude(longitude: Double) {
        this.longitude = longitude
    }

    fun getInnerCleanliness(): String {
        return innerCleanliness
    }

    fun setInnerCleanliness(innerCleanliness: String) {
        this.innerCleanliness = innerCleanliness
    }

    fun getCarImageUrl(): String? {
        return carImageUrl
    }

    fun setCarImageUrl(carImageUrl: String) {
        this.carImageUrl = carImageUrl
    }

    fun editData() {
        setInnerCleanliness(editText(innerCleanliness.replace("_", " ")))
        setColor(editText(color.replace("_", " ")))
    }

    private fun editText(word: String): String {
        return word.lowercase().replaceFirstChar { it.uppercase() }
    }
}
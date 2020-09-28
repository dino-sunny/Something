package com.dino.something.module.home

import timber.log.Timber

data class Employee(
    val id: Int = 0,
    val name: String?,
    val username: String?,
    val email: String?,
    val profile_image: String?,
    val company: CompanyDetails?,
    val address: Address?,
    val phone: String?,
    val website: String?
)
data class CompanyDetails(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?,
    ){
    fun companyDetails(): String {
        try {
            return "${name}, ${catchPhrase}, $bs"

        } catch (e: NullPointerException) {
            Timber.d("KotlinNullPointerException")
        }
        return ""
    }
}

data class Address(
    val street: String?,
    val suit: String?,
    val city: String?,
    val zipcode: String?,
    val geo: Geo?,
){
    fun employeeAddress(): String {
        try {
            return "${street}, ${suit}, ${city}, $zipcode "

        } catch (e: NullPointerException) {
            Timber.d("KotlinNullPointerException")
        }
        return ""
    }
}
data class Geo(
    val lat: Double?,
    val lng: Double?
)
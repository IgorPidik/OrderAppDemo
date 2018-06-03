package com.example.igor.myapplication.models

data class Address(val country: String, val city: String, val street: String, val houseNumber: Int, val extension: String = "") {
    override fun toString(): String {
        return "$street $houseNumber $extension,\n" +
                "$city,\n" +
                "$country"
    }
}
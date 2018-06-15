package com.example.igor.myapplication.models

data class FoodItem(val name: String, val description: String, val price: Double) {
    var extraOptions = emptyList<String>()
}
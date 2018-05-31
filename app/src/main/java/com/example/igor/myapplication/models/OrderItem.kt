package com.example.igor.myapplication.models

data class OrderItem(val foodItem: FoodItem, var count: Int)

fun OrderItem.sum(): Double {
    return this.count * this.foodItem.price
}
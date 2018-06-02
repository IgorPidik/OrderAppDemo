package com.example.igor.myapplication.controllers

import com.example.igor.myapplication.models.Address
import com.example.igor.myapplication.models.Customer

class AuthManager {
    companion object {
        val instance: AuthManager by lazy {
            AuthManager()
        }
    }

    fun getTestCustomer(): Customer {
        val address = Address("UK", "London", "Test Street", 46, null)

        return Customer("John", "Doe", address, "1234567894654")
    }
}
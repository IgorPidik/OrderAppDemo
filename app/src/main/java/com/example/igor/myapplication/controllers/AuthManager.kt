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
        return Customer("John", "Doe", "1234567894654")
    }

    fun getCustomer(): Customer {
        return getTestCustomer()
    }
}
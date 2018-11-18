package com.example.igor.myapplication.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.igor.myapplication.R
import kotlinx.android.synthetic.main.stripe_credit_card_input.*
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import java.lang.Exception

class PayActivity : AppCompatActivity() {
    private lateinit var stripe: Stripe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stripe_credit_card_input)


        val stripeApiKey = resources.getString(R.string.stripe_api_key)

        stripe = Stripe(this, stripeApiKey)

        submit.setOnClickListener { submitPayment() }
    }


    private fun submitPayment() {
        val cardNumber = cardNo.text.toString()
        val cardCVV = cvv.text.toString()
        val expMonth = month.text.toString().toInt()
        val expYear = year.text.toString().toInt()

        val card = Card(cardNumber, expMonth, expYear, cardCVV)

        if(!card.validateCard()) {
            Toast.makeText(this, "Invalid Card", Toast.LENGTH_LONG).show()
        }

        stripe.createToken(card, object : TokenCallback {
            override fun onSuccess(token: Token?) {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: Exception?) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

        })
    }


}

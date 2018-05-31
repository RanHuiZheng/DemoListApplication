package com.example.administrator.demolistapplication.demomoduls.kotlinTest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

import com.example.administrator.demolistapplication.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findView()

    }


    fun findView(){
        var usernameET = findViewById<EditText>(R.id.username)
        var passwardET = findViewById<EditText>(R.id.passward)
        var emailET = findViewById<EditText>(R.id.email)


    }



}

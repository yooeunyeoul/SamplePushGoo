package com.test.mysamplepush

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    private var target_url: String? = "https://www.daum.net/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = 0

//        if(!TextUtils.isEmpty("aaaa")){
//            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
//                val newToken = instanceIdResult.token
//                Log.e("onCreate",newToken)
//            }
//        }

        val intent = intent
        val bunldle = intent.extras
        target_url = bunldle?.getString("url")



    }
}

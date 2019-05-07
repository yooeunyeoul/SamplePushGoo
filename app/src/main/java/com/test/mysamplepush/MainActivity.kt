package com.test.mysamplepush

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
            val newToken = instanceIdResult.token
            Log.e("onCreate",newToken)
        }
    }
}

package com.test.mysamplepush

import android.util.Log
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult

class FbLoginCallback : FacebookCallback<LoginResult> {

    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
    override fun onSuccess(result: LoginResult?) {
        requestMe(result?.accessToken)
    }

    private fun requestMe(accessToken: AccessToken?) {
        val graphRequest = GraphRequest.newMeRequest(
            accessToken
        ) { jsonOb, response ->
            Log.e("result", jsonOb.toString())
//            listener.success(jsonOb)
        }


    }

    // 로그인 창을 닫을 경우, 호출됩니다.
    override fun onCancel() {
        Log.e("Callback", "onCancel")
    }

    // 로그인 실패 시에 호출됩니다.
    override fun onError(error: FacebookException?) {
//        error?.let { listener.onFailure(it) }
        Log.e("result", error?.message)
    }

}
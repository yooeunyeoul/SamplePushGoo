package com.test.mysamplepush

import android.app.Application
import android.content.Context
import com.kakao.auth.KakaoSDK
import com.nhn.android.naverlogin.OAuthLogin


class GlobalApplication : Application() {

    init {
        instance2 = this
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSDK.init(KakaoSDKAdapter())
        mOAuthLoginModule.init(applicationContext,"RVRtA1mlYtgGMg6TpBTm","ZDiY4sWJyy","이름뭐지")


    }

    companion object {
        private var instance2: Application? = null
         val mOAuthLoginModule: OAuthLogin = OAuthLogin.getInstance()

        fun context(): Context {
            return instance2!!.applicationContext
        }
    }
}




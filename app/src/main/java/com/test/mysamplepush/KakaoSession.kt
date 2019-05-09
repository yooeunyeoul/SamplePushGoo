package com.test.mysamplepush

import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException

class KakaoSession : ISessionCallback {

    private var instance: KakaoSession? = null

    override fun onSessionOpenFailed(exception: KakaoException?) {

    }

    override fun onSessionOpened() {

    }

    companion object{
        val instance = KakaoSession()

    }

    object private fun init(): KakaoSession? {

        if (instance == null) {
            val KakaoSession = KakaoSession()
            Session.getCurrentSession().addCallback(KakaoSession)
            Session.getCurrentSession().checkAndImplicitOpen()

            instance = KakaoSession
        } else {
            return instance as KakaoSession
        }
        return null
    }
}
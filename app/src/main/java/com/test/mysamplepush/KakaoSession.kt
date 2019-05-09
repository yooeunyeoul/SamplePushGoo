package com.test.mysamplepush

import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException

class KakaoSession : ISessionCallback {
    init {
        Session.getCurrentSession().addCallback(instance)
        Session.getCurrentSession().checkAndImplicitOpen()
    }


    override fun onSessionOpenFailed(exception: KakaoException?) {

    }

    override fun onSessionOpened() {

    }

    companion object{
        val instance = KakaoSession()
    }
}
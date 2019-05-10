package com.test.mysamplepush

import android.util.Log
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException

//https://kgh940525.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%86%A1Kakaotalk-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0
class KakaoSession : ISessionCallback {
    private lateinit var listener: KakaoListener

    companion object {
        //TODO:: Lazy by lazy로 하면 안됨 왜 그런지는 모르겠음
        //        val instance by lazy { KakaoSession() }
        val instance = KakaoSession()
    }

    init {

        Log.e(KakaoSession.javaClass.simpleName, "in Init###################################")
        Session.getCurrentSession().addCallback(instance)
//        Session.getCurrentSession().checkAndImplicitOpen()

    }


    override fun onSessionOpenFailed(exception: KakaoException?) {
        Log.e("Session", "${exception?.printStackTrace()}")
    }

    override fun onSessionOpened() {
        UserManagement.getInstance().requestMe(object : MeResponseCallback() {
            override fun onSuccess(userProfile: UserProfile?) {

                userProfile?.let { listener.success(it) }
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.e("onSessionClosed", "${errorResult.toString()}")
            }

            override fun onNotSignedUp() {
                Log.e("onNotSignedUp", "onNotSignedUp")
            }

            override fun onFailure(errorResult: ErrorResult?) {
                errorResult?.let { listener.onFailure(errorResult) }

            }

        })
    }

    fun setListener(listener: KakaoListener) {
        this.listener = listener
    }
}
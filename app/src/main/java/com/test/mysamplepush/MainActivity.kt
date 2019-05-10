package com.test.mysamplepush

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginResult
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.Utility.getPackageInfo
import com.kakao.util.helper.log.Logger
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    private var hashKey: String = ""
    private var target_url: String? = "https://www.daum.net/"
    private lateinit var callback: Sessioncallback
    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FacebookSdk.sdkInitialize(GlobalApplication.context())
        FacebookSdk.setApplicationId("869795810030493")
        callback = Sessioncallback()
        Session.getCurrentSession().addCallback(callback)

        btn_naverlogin.setOAuthLoginHandler(@SuppressLint("HandlerLeak")
        object : OAuthLoginHandler(){
            override fun run(success: Boolean) = if (success) {
                val accessToken = GlobalApplication.mOAuthLoginModule.getAccessToken(activity)
                val refreshToken = GlobalApplication.mOAuthLoginModule.getRefreshToken(activity)
                val expiresAt = GlobalApplication.mOAuthLoginModule.getExpiresAt(activity)
                val tokenType = GlobalApplication.mOAuthLoginModule.getTokenType(activity)
            } else {
                val errorCode = GlobalApplication.mOAuthLoginModule.getLastErrorCode(activity).code
                val errorDest = GlobalApplication.mOAuthLoginModule.getLastErrorDesc(activity)
            }
        })

        val mCallbackManager = CallbackManager.Factory.create()
//        val mLoginCallback = FbLoginCallback(object : FaceBookListener{
//            override fun success(jsonObj: JSONObject) {
//
//            }
//
//            override fun onFailure(error: FacebookException) {
//
//            }
//
//        })

        btn_facebook_login.setOnClickListener {
            btn_facebook_login.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.e("Success","onSuccess")
                }

                override fun onCancel() {
                    Log.e("Success","onCancel ")
                }

                override fun onError(error: FacebookException?) {
                    Log.e("Success","onError ")
                }

            })
        }





        /*KakaoSession.instance.setListener(object : KakaoListener {
            override fun success(userProfile: UserProfile) {
                userProfile.id
                Log.e("Success","Success $userProfile")
            }

            override fun onFailure(errorResult: ErrorResult) {
                Log.e("onFailure","onFailure $errorResult")
            }
        })

        KakaoSession.instance.requestMe(object : KakaoListener{
            override fun success(userProfile: UserProfile) {
                userProfile.id
                Log.e("Success","Success $userProfile")
            }

            override fun onFailure(errorResult: ErrorResult) {
                Log.e("onFailure","onFailure $errorResult")
            }

        })*/



        hashKey = getKeyHash(this)

        Log.e("hashKey ", "HashKey : $hashKey")


    }

    private fun getKeyHash(context: Context): String {
        val packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return ""

        for (signature in packageInfo!!.signatures) {
            try {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
            } catch (e: NoSuchAlgorithmException) {
                Log.e("Exceptoin", e.printStackTrace().toString())
            }
        }
        return ""
    }

    private inner class Sessioncallback : ISessionCallback {
        override fun onSessionOpened() {
            //            redirectSignupActivity();
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                override fun onSessionClosed(errorResult: ErrorResult) {
                    Log.e("##Sessioncallback##", "errorResult:$errorResult")
                }

                override fun onSuccess(response: MeV2Response) {
                    Log.e("##Sessioncallback##", "id:" + response.id + ", name:" + response.nickname)
                    val kakao_id = java.lang.Long.toString(response.id)
                    val kakao_nickname = response.nickname
//                    val kakaoLogin_success =
//                        Application.application.getLptvDomain() + "/m/login/SocialIdChk.aspx?ref=app&SocialTypeVal=ka&myName=" + kakao_nickname + "&myId=" + kakao_id


//                    webview.loadUrl(kakaoLogin_success)
                }
            })
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) {
                Logger.e(exception)
            }
        }
    }
}
